package com.dwk.service.message;

import java.text.MessageFormat;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dwk.dao.MongodbDao;
import com.dwk.model.message.Message;
import com.dwk.model.message.MessageConfig;
import com.dwk.model.message.ProductProcess;
import com.dwk.model.message.Template;

/**
 * Message factory for send.
 * 
 * @author: xp
 * @data : 2014-9-1
 * @since : 1.5
 */
public class MessageFactory {

  private static final Logger logger = LoggerFactory.getLogger(MessageFactory.class);

  private MongodbDao dao;
  private Map<Integer, Template> templates;

  public void send(Integer template, String sender, String receiver, Object... data) {
    Template temp = templates.get(template);
    if (temp == null) {
      logger.error("Send message has exception, message template code is '" + template + "'.");
      return;
    }

    for(MessageConfig config : temp.getConfigs()) {
      Message message = new Message();
      message.setContent(getFormat(config.getContent(), data));
      
      send(message);
    }
    
  }

  public String send(Message message) {
    return dao.insert("sendMessage", message); 
  }
  
  public String process(ProductProcess process) {
    return dao.insert("sendProductProcessMessage", process);
  }
  
  private String getFormat(String format, Object... data) {
    return MessageFormat.format(format, data);
  }

  public void setTemplates(Map<Integer, Template> templates) {
    this.templates = templates;
  }

  public void setDao(MongodbDao dao) {
    this.dao = dao;
  }
  
}
