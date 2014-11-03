package com.dwk.common.sms;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.dwk.util.HttpUtils;
import com.dwk.util.HttpUtils.Method;

public class EmaySmsManagerment {

  private static final String REGIST_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/regist.action";
  private static final String REGIST_INFO_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/registdetailinfo.action";
  private static final String LOGOUT_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/logout.action";
  private static final String BALANCE_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/querybalance.action";
  private static final String CHARGEUP_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/chargeup.action";
  private static final String CHANGEPWD_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/changepassword.action";
  
  private EmaySmsManagerment() {}
  
  public static void main(String[] args) {
    System.out.println(registInfo());
  }
  
  /**
   * 亿美短信平台注册
   */
  public static boolean regist() {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", EmaySmsPlatform.cdKey);
      param.put("password", EmaySmsPlatform.password);

      String response = HttpUtils.call(Method.get, REGIST_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsResponseModel rm = EmaySmsPlatform.transferNormal(response);
      return rm.success();
    } catch (Exception t) {
      SmsSender.logger.error("Emay sms platform: Regist error.", t);
    }
    return false;
  }

  /**
   * 亿美短信平台注册 (用户详细信息)
   */
  public static boolean registInfo() {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", EmaySmsPlatform.cdKey);
      param.put("password", EmaySmsPlatform.password);
      param.put("ename", "lenovo");
      param.put("linkman", "Chris Huang");
      param.put("phonenum", "18121025090");
      param.put("mobile", "18121025090");
      param.put("email", "huangwei11@lenovo.com");
      param.put("fax", "021-99999");
      param.put("address", "Shang Hai");
      param.put("postcode", "201203");

      String response = HttpUtils.call(Method.get, REGIST_INFO_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsResponseModel rm = EmaySmsPlatform.transferNormal(response);
      return rm.success();
    } catch (Exception t) {
      SmsSender.logger.error("Emay sms platform: Regist info error.", t);
    }
    return false;
  }

  /**
   * 注销帐号
   */
  public static boolean logOut(EmaySmsConfig config) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", config.getCdKey());
      param.put("password", config.getPassWord());

      String response = HttpUtils.call(Method.post, LOGOUT_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsPlatform.transferNormal(response);
    } catch (Exception t) {
      SmsSender.logger.error("Emay sms platform: Logout error.", t);
    }
    return false;
  }
  
  /**
   * 查询余额
   */
  public static boolean balance(EmaySmsConfig sms) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", sms.getCdKey());
      param.put("password", sms.getPassWord());

      String response = HttpUtils.call(Method.post, BALANCE_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsPlatform.transferNormal(response);
    } catch (Exception t) {
      SmsSender.logger.error("Emay sms platform: Query balance error.", t);
    }
    return false;
  }

  /**
   * 充值 // TODO 参数
   */
  public static boolean chargeUp(EmaySmsConfig sms) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", sms.getCdKey());
      param.put("password", sms.getPassWord());
      param.put("cardno", "");
      param.put("cardpass", "");

      String response = HttpUtils.call(Method.post, CHARGEUP_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsPlatform.transferNormal(response);
    } catch (Exception t) {
      SmsSender.logger.error("Emay sms platform: Charge up error.", t);
    }
    return false;
  }

  /**
   * 修改密码
   */
  public static boolean changePWD(EmaySmsConfig config, String newPassWord) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", config.getCdKey());
      param.put("password", config.getPassWord());
      param.put("newPassword", newPassWord);

      String response = HttpUtils.call(Method.post, CHANGEPWD_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsPlatform.transferNormal(response);
    } catch (Exception t) {
      SmsSender.logger.error("Emay sms platform: Change pass word error.", t);
    }
    return false;
  }
  
}
