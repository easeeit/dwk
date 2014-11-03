package com.dwk.util;

import net.sf.json.JSONObject;

public class JsonSet2Bean {

  public static void json2bean(JSONObject job,String fieldName,Object targetClass){
    if(job.containsKey(fieldName)){
      ClassReflect.invokeSet(targetClass, fieldName,job.get(fieldName));
    }
  }
  
  
  public static void json2bean(JSONObject job,String[] fieldNames,Object targetClass){
    
    for(String fieldName:fieldNames){
      if(job.containsKey(fieldName)){
        String objString = job.getString(fieldName);
        ClassReflect.invokeSet(targetClass, fieldName,job.get(fieldName));
      }
    }
  }

}
