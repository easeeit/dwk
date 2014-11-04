package com.dwk.service.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.dwk.constant.APIConstant;
import com.dwk.dao.MongodbDao;
import com.dwk.model.BasicResponse;
import com.dwk.model.message.Message;
import com.dwk.model.message.MessageInfo;
import com.dwk.model.message.ProductProcess;
import com.dwk.model.message.ProductProcessInfo;
import com.dwk.model.message.request.DeleteMessageRequest;
import com.dwk.model.message.request.GetMessageListRequest;
import com.dwk.model.message.request.GetProductProcessRequest;
import com.dwk.model.message.request.ProductProcessRequest;
import com.dwk.model.message.request.SendMessageRequest;
import com.dwk.model.message.response.MessageListResponse;
import com.dwk.model.message.response.ProductProcessListResponse;
import com.dwk.model.product.ProductInfo;
import com.dwk.model.user.BasicUserInfo;
import com.dwk.model.user.LoginUser;
import com.dwk.service.user.UserService;

/**
 * Message service.
 * <ol>
 * 'system message' or 'user message' or 'product trade message'
 * </ol>
 * 
 * @author: xiangping_yu
 * @data : 2014-8-28
 * @since : 1.5
 */
public class MessageService {

  private MessageFactory factory;
  private MongodbDao dao;
//  private ProductService productService;
  private UserService userService;

  public BasicResponse sendMessageToUser(LoginUser user, SendMessageRequest request) {
    BasicResponse response = new BasicResponse();

    Message message = new Message();
    message.setFromUserID(user.getId());
    message.setTargetUserID(request.getTargetUserID());
    message.setTitle(request.getTitle());
    message.setSubtitle(request.getSubtitle());
    message.setContent(request.getMessage());
    message.setSendTime(System.currentTimeMillis());

    String upperMsgID = request.getUpperMsgID();
    message.setUpperID(upperMsgID);
    if (!StringUtils.isBlank(upperMsgID) && !"-1".equals(upperMsgID)) {
      Message upperMessage = getMessageByID(upperMsgID);
      if (upperMessage == null) {
        response.setCode(APIConstant.RETURN_CODE_DATA_NOT_FOUND);
        return response;
      }
      message.setRootID(upperMessage.getRootID() == null ? upperMessage.getId() : upperMessage.getRootID());
      message.setDeepth(upperMessage.getDeepth()+1);
    }

    factory.send(message);

    return response;
  }

  public MessageListResponse getMsgList(LoginUser user, GetMessageListRequest request) {
    MessageListResponse response = new MessageListResponse();

    Map<String, Object> parameter = new HashMap<String, Object>();
    parameter.put("userID", user.getId());

    List<Message> messages = dao.selectList("getMessage", parameter, request.getLimit(), request.getSkip());
    if (!CollectionUtils.isEmpty(messages)) {
      List<MessageInfo> list = new ArrayList<MessageInfo>(messages.size());
      for (Message message : messages) {
        MessageInfo mi = new MessageInfo();
        mi.setId(message.getId());
        mi.setMessage(message.getContent());
        mi.setMsgFromUserID(message.getFromUserID());
        mi.setTitle(message.getTitle());
        mi.setSubtitle(message.getSubtitle());
        mi.setRootMessageID(message.getRootID());
        mi.setUpperMessageID(message.getUpperID());
        mi.setSendTime(message.getSendTime());
        
        BasicUserInfo basicUser = userService.findBasicUserByID(message.getFromUserID());
        mi.setMsgFromUserName(basicUser.getNickName());
        list.add(mi);
      }
      response.setMessageList(list);
    }
    return response;
  }

  public BasicResponse sendMsgInExchangCircle(LoginUser user, ProductProcessRequest request) {
    BasicResponse response = new BasicResponse();
    
    ProductProcess process = new ProductProcess();
    process.setFromUserID(user.getId());
    process.setTargetUserID(request.getTargetUserID());
    process.setTitle(request.getTitle());
    process.setSubtitle(request.getSubtitle());
    process.setContent(request.getMessage());
    process.setSendTime(System.currentTimeMillis());

    String upperMsgID = request.getUpperMsgID();
    process.setUpperID(upperMsgID);
    if (!StringUtils.isBlank(upperMsgID) && !"-1".equals(upperMsgID)) {
      ProductProcess upperMessage = getProductProcessByID(upperMsgID);
      if (upperMessage == null) {
        response.setCode(APIConstant.RETURN_CODE_DATA_NOT_FOUND);
        return response;
      }
      process.setRootID(upperMessage.getRootID() == null ? upperMessage.getId() : upperMessage.getRootID());
      process.setDeepth(upperMessage.getDeepth()+1);
    }
    
    process.setSignal(request.getSignal());
    
    String productID = request.getTargetProductID();
//    ProductInfo product = productService.getProductInfoByID(productID);
    process.setProductID(productID);
    
    factory.process(process);
    
    return response;
  }
  

  public ProductProcessListResponse getExchangCircleMsg(LoginUser user, GetProductProcessRequest request) {
    ProductProcessListResponse response = new ProductProcessListResponse();
    
    Map<String, Object> parameter = new HashMap<String, Object>();
    parameter.put("userID", user.getId());

    List<ProductProcess> processList = dao.selectList("getProductProcess", parameter, request.getLimit(), request.getSkip());
    if (!CollectionUtils.isEmpty(processList)) {
      List<ProductProcessInfo> list = new ArrayList<ProductProcessInfo>(processList.size());
      for (ProductProcess process : processList) {
        ProductProcessInfo ppi = new ProductProcessInfo();
        ppi.setId(process.getId());
        ppi.setMessage(process.getContent());
        ppi.setMsgFromUserID(process.getFromUserID());
        ppi.setTitle(process.getTitle());
        ppi.setSubtitle(process.getSubtitle());
        ppi.setRootMessageID(process.getRootID());
        ppi.setUpperMessageID(process.getUpperID());
        ppi.setSendTime(process.getSendTime());
        ppi.setSignal(process.getSignal());
        ppi.setProductID(process.getProductID());
        ppi.setProductName(process.getProductName());
        
        BasicUserInfo basicUser = userService.findBasicUserByID(process.getFromUserID());
        ppi.setMsgFromUserName(basicUser.getNickName());
        list.add(ppi);
      }
      response.setProcessList(list);
    }
    
    return response;
  }
  
  public BasicResponse deleteMessage(LoginUser user, DeleteMessageRequest request) {
    BasicResponse response = new BasicResponse();
    if (CollectionUtils.isEmpty(request.getMsgIDs())) {
      return response;
    }

    Map<String, Object> parameter = new HashMap<String, Object>();
    parameter.put("userID", user.getId());
    parameter.put("ids", request.getMsgIDs());
    dao.update("deleteMessage", parameter);
    return response;
  }

  public Message getMessageByID(String messageID) {
    return dao.selectOne("getMessageByID", messageID);
  }

  public ProductProcess getProductProcessByID(String processID) {
    return dao.selectOne("getProductProcessByID", processID);
  }
  
  public void setFactory(MessageFactory factory) {
    this.factory = factory;
  }

  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }

//  public void setProductService(ProductService productService) {
//    this.productService = productService;
//  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

}
