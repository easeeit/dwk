package com.dwk.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.locale.converters.FloatLocaleConverter;

public class ClassReflect {

       
    
  @SuppressWarnings("unchecked")       
    
  public static Method getGetMethod(Class objectClass, String fieldName) {       
    
      StringBuffer sb = new StringBuffer();       
    
      sb.append("get");       
    
      sb.append(fieldName.substring(0, 1).toUpperCase());       
    
      sb.append(fieldName.substring(1));       
    
      try {       
    
          return objectClass.getMethod(sb.toString());       
    
      } catch (Exception e) {       
    
      }       
    
      return null;       
    
  }       
    
         
    

    
  @SuppressWarnings("unchecked")       
    
  public static Method getSetMethod(Class targetClassObject, String fieldName) {       
    
      try {       
    
          Class[] parameterTypes = new Class[1];       
    
          Field field = targetClassObject.getDeclaredField(fieldName);       
    
          parameterTypes[0] = field.getType();       
    
          StringBuffer sb = new StringBuffer();       
    
          sb.append("set");       
    
          sb.append(fieldName.substring(0, 1).toUpperCase());       
    
          sb.append(fieldName.substring(1));       
    
          Method method = targetClassObject.getMethod(sb.toString(), parameterTypes);       
    
          return method;       
    
      } catch (Exception e) {       
    
          e.printStackTrace();       
    
      }       
    
      return null;       
    
  }       
    
         
    

    
  public static void invokeSet(Object targetClassObject, String fieldName, Object setValue) {       
      //testObjectType(setValue, fieldName);
      Method method = getSetMethod(targetClassObject.getClass(), fieldName);       
      
      try {       
          Class p = method.getParameterTypes()[0];
          String name = p.getName();
          if(name.endsWith("Long") || name.endsWith("Integer")){
             String value = setValue.toString().substring(0,setValue.toString().indexOf("."));
            if(p.getName().endsWith("Long")){
              method.invoke(targetClassObject, Long.parseLong(value));   
            }else if(p.getName().endsWith("Integer")){
              method.invoke(targetClassObject, Integer.parseInt(value));   
            }
          }else if(name.endsWith("Double") || name.endsWith("Float")){
            if(p.getName().endsWith("Double")){
              method.invoke(targetClassObject, Double.parseDouble(setValue.toString()));   
            }else if(p.getName().endsWith("Float")){
              method.invoke(targetClassObject, Float.parseFloat(setValue.toString()));   
            }
          }else if(p.getName().endsWith("Character")){
            method.invoke(targetClassObject, setValue.toString().charAt(0));   
          }else{
            method.invoke(targetClassObject, new Object[] { setValue });       
          }
      } catch (Exception e) {       
    
          e.printStackTrace();       
    
      }       
    
  }       
    
         
    
  /**    
   
   * ִ��get����    
   
   *     
   
   * @param oִ�ж���    
   
   * @param fieldName����    
   
   */       
    
  public static Object invokeGet(Object o, String fieldName) {  
    

      Method method = getGetMethod(o.getClass(), fieldName);       
    
      try {       
    
          return method.invoke(o, new Object[0]);       
    
      } catch (Exception e) {       
    
          e.printStackTrace();       
    
      }       
    
      return null;       
    
  }  

  
  
  public static void testObjectType(Object obj,String fieldName){
    System.out.println("field Name ->"+fieldName);
    System.out.println("obj is Integer  ->"+(obj instanceof Integer));
    System.out.println("obj is Double  ->"+(obj instanceof Double));
    System.out.println("obj is Float  ->"+(obj instanceof Float));
    System.out.println("obj is Long  ->"+(obj instanceof Long));
    System.out.println("obj is String  ->"+(obj instanceof String));
    System.out.println("obj is Char  ->"+(obj instanceof Character));
    System.out.println();
  }
}
