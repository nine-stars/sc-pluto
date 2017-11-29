package com.iyb.ak.constants;

/**
 * Created by yc096 on 2017/2/17.
 */
public interface SecurityConstants {
    String GLOBAL_PID = "global_pid";
    String ACCESS_TOKEN = "access_token";
    String USER_UUID = "userUuid";

    String COMPUUID = "CompUuid";
    String COMPNAME = "compName";
    //系统 非公司
    String COMPUUID_GLOBAL = "GLOBAL";
    //是否是多公司
    String IS_MULTI_COMPANY = "isMultiCompany";
    //登陆clientId
    String CLIENT_ID = "clientId";
    //web登陆clientId
    String CLIENT_ID_WEB = "banbu_web";
    //isp登陆clientId
    String CLIENT_ID_ISP = "banbu_isp";
    //ios登陆clientId
    String CLIENT_ID_IOS = "banbu_ios";
    //android登陆clientId
    String CLIENT_ID_ANDROID = "banbu_android";
    //登陆员工id
    String EMPL_UUID = "EmplUuid";
    String ROLES = "Roles";
    String PERMISSIONS = "Permissions";
    String DEPARTMENTS = "Departments";

    //登录是否需要验证码
    String CODE_JCAPTCHA_NEED = "jcaptchaCodeNeed";
    //验证码
    String CAPTCHA = "captcha";

    //短信验证码
    String SMSCODE = "smsCode";

    //版本ID
    String VERSIONUUID = "versionUuid";
    //版本名称
    String VERSIONNAME = "versionName";
}