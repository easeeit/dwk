package com.dwk.common.sms;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.dwk.common.sms.EmaySmsResponseModel.EmaySmsReceiveResponseModel;
import com.dwk.util.HttpUtils;
import com.dwk.util.HttpUtils.Method;

/**
 * Emay sms platform
 * 
 * @author: xiangping_yu
 * @data : 2014-4-3
 * @since : 1.5
 */
public class EmaySmsPlatform {
  
  static final String EMAY_HOST = "http://sdk229ws.eucp.b2m.cn:8080";
  static final String cdKey = "9SDK-EMY-0229-JDQUO";
  static final String password = "096336";
  
  private static final String SEND_URL = EMAY_HOST + "/sdkproxy/sendsms.action";
  private static final String SEND_TIME_URL = EMAY_HOST + "/sdkproxy/sendtimesms.action";
  private static final String RECEIVE_URL = EMAY_HOST + "/sdkproxy/getmo.action";
  
  private EmaySmsPlatform() {}
  
  public static void main(String[] args) {
    System.out.println(sendSms("15692120366", "【lenovo】有没有收到，我人有的和主产不为这。"));
  }
  
  /**
   * 发送短信 
   * @param phone 多个手机号用","隔开
   */
  public static boolean sendSms(String phone, String message) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", cdKey);
      param.put("password", password);
      param.put("phone", phone);
      param.put("message", message);
      param.put("addserial", null);
      param.put("seqid", "10000000002");
      param.put("smspriority", "5");
      //param.put("sendtime", null);
      
      String response = HttpUtils.call(Method.get, SEND_URL, null, null, param).toString();
      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsResponseModel rm = transferNormal(response);
      return rm.success();
    } catch (Exception t) {
      SmsSender.logger.error("Emay sms platform: Send sms error.", t);
    }
    return false;
  }

  /**
   * 发送定时短信 TODO 参数定义
   */
  public static boolean sendTimeSms(EmaySmsConfig sms) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", sms.getCdKey());
      param.put("password", sms.getPassWord());
      param.put("phone", "");
      param.put("message", "");
      param.put("sendtime", "");
      param.put("addserial", "");

      String response = HttpUtils.call(Method.post, SEND_TIME_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      transferNormal(response);
    } catch (Exception t) {
      SmsSender.logger.error("Emay sms platform: Send time sms error.", t);
    }
    return false;
  }

  /**
   * 接收短信
   */
  public static boolean receive(EmaySmsConfig sms) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", sms.getCdKey());
      param.put("password", sms.getPassWord());

      String response = HttpUtils.call(Method.post, RECEIVE_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      transferReceive(response);
    } catch (Exception t) {
      SmsSender.logger.error("Emay sms platform: Receive sms error.", t);
    }
    return false;
  }

  protected static EmaySmsResponseModel transferNormal(String response) throws Exception {
    SAXReader reader = new SAXReader();
    Document doc = reader.read(new ByteArrayInputStream(response.trim().getBytes()));
    
    Element root = doc.getRootElement();
    Element code = root.element("error");
    Element message = root.element("message");
    
    EmaySmsResponseModel model = new EmaySmsResponseModel();
    model.setCode(code==null ? -1 : Integer.parseInt(code.getText()));
    model.setMsg(message==null ? null : message.getText());
    return model;
  }

  @SuppressWarnings("unchecked")
  private static EmaySmsReceiveResponseModel transferReceive(String response) throws Exception {
    SAXReader reader = new SAXReader();
    Document doc = reader.read(new ByteArrayInputStream(response.trim().getBytes()));
    Element rootElement = doc.getRootElement();
    for (Element element : (List<Element>) rootElement.elements()) {
      element.getName();
    }

    EmaySmsReceiveResponseModel model = new EmaySmsReceiveResponseModel();
    return model;
  }

  public enum ResponseCode {
    
    ok(0, "成功"), 
    error(1, "失败"), 
    r304(304, "客户端发送三次失败"), 
    r305(305, "服务器返回了错误的数据，原因可能是通讯过程中有数据丢失"), 
    r307(307, "发送短信目标号码不符合规则，手机号码必须是以0、1开头"), 
    r308(308, "非数字错误，修改密码时如果新密码不是数字那么会报308错误"), 
    r3(3, "连接过多，指单个节点要求同时建立的连接数过多"), 
    r911005(911005, "客户端注册失败,序列号或者密码有误"), 
    r911003(911003, "客户端注册失败,序列号已注册且与当前客户赋值的password不同"), 
    r11(11, "企业信息注册失败"), 
    r12(12, "查询余额失败"), 
    r13(13, "充值失败"), 
    r14(14, "手机转移失败"), 
    r15(15, "手机扩展转移失败"), 
    r16(16, "取消转移失败"),
    r17(17, "发送信息失败"), 
    r18(18, "发送定时信息失败"), 
    r22(22, "注销失败"), 
    r27(27, "查询单条短信费用错误码");

    int code;
    String msg;

    ResponseCode(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    public static ResponseCode fromCode(int code) {
      for (ResponseCode rcode : values()) {
        if (rcode.getCode() == code) {
          return rcode;
        }
      }
      return error;
    }

    public int getCode() {
      return code;
    }

    public String getMsg() {
      return msg;
    }
  }

}
