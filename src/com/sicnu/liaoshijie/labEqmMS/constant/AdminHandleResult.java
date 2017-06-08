/*
 * 文件名：AdminHandleResult.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： AdminHandleResult.java
 * 修改人：tianzhong
 * 修改时间：2016年4月8日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.constant;

/**
 * TODO 管理员相关处理的结果常量集.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码 
 * 
 * @author tianzhong
 */
public class AdminHandleResult {
	/**
	 * 添加字段注释.
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * 邮箱电话不匹配.
	 */
	public static final String FINDPWD_EMAIL_TEL_NOT_MATCH = "email_tel_not_match";
	
	/**
	 * 验证码错误.
	 */
	public static final String LOGIN_VALIDCODE_ERROR = "ValidCode_Error";
	
	/**
	 * 账号或密码错误，登录失败.
	 */
	public static final String LOGIN_ID_OR_PWD_ERROR = "id_or_pwd_error";
	
	/**
	 * 参数错误.
	 */
	public static final String REGIST_PARAM_ERROR = "param_error";
	
	/**
	 * 该工号已被注册.
	 */
	public static final String REGIST_EXISTED_ID = "existed_id";
	
	/**
	 * 非普通教职工.
	 */
	public static final String NOT_NORMAL_STAFF = "not_normal_staff";
	
	/**
	 * 注册未审核.
	 */
	public static final String NO_PASS = "no_pass";
	
	/**
	 * 添加字段注释.
	 */
	public static final String WRONG_PASSWORD = "wrong_pwd";
	
	/**
	 * 添加字段注释.
	 */
	public static final String FAILED = "failed";
	
}
