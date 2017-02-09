/*
 * 文件名：SendNotifyVo.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： SendNotifyVo.java
 * 修改人：KOBE
 * 修改时间：2016年4月11日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.model;

/**
 * TODO 发送通知model（email + 短信）. 
 * 
 * @author     KOBE
 */
public class SendNotifyVo {
	/**
	 * 工号.
	 */
	private String adminID;
	
	/**
	 * 电话号码.
	 */
	private String adminTel;
	
	/**
	 * 邮箱.
	 */
	private String adminEmail;
	
	/**
	 * 消息内容.
	 */
	private String messageContent;

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

	public String getAdminTel() {
		return adminTel;
	}

	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	
}
