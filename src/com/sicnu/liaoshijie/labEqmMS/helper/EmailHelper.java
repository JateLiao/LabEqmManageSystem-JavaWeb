/*
 * 文件名：EmailHelper.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EmailHelper.java
 * 修改人：KOBE
 * 修改时间：2016年4月10日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.helper;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sicnu.liaoshijie.labEqmMS.model.SendNotifyVo;
import com.sun.mail.util.MailSSLSocketFactory;

//import com.sun.mail.util.MailSSLSocketFactory;

/**
 * TODO 电子邮件处理helper.
 * 
 * @author KOBE
 */
public class EmailHelper {
	/**
	 * TODO 发送消息.
	 * 
	 */
	public static void sendNotify(SendNotifyVo notifyVo){
		try {
			// String ENCODEING = "UTF-8";
			String host = "smtp.163.com";// "smtp.qq.com"; // 服务器地址
			String sender = "lsj24legend@163.com"; // 发件人的邮箱
			String receiver = notifyVo.getAdminEmail(); // 收件人的邮箱
			// String name = "天天都是实力派吃货"; // 发件人昵称
			String username = "lsj24legend@163.com"; // 账号
			String password = "htt374682617"; // 密码
			String subject = "四川师范大学设备管理系统--密码找回"; // 主题
			// String messageContent = "<a href='https://www.baidu.com'>这是一封测试邮件，不管你信不信，我这的是想多说几句废话，时间是2016年4月17日凌晨两点半！</a>"; // 信息(支持HTML)

			Properties prop = new Properties();
			prop.setProperty("mail.host", host);
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.debug", "true");
		    prop.setProperty("mail.smtp.auth", "true");
		    
		    MailSSLSocketFactory sf = new MailSSLSocketFactory();  
		    sf.setTrustAllHosts(true);  
		    prop.put("mail.smtp.ssl.enable", "true");  
		    prop.put("mail.smtp.ssl.socketFactory", sf); 

		    // 1、创建session
		    Session session = Session.getInstance(prop);
		    Transport ts = null;

		    // 2、通过session得到transport对象
		    ts = session.getTransport();

		    // 3、连上邮件服务器
		    ts.connect(host, 587, username, password);

		    // 4、创建邮件
		    MimeMessage message = new MimeMessage(session);

		    // 邮件消息头
		    message.setFrom(new InternetAddress(sender)); // 邮件的发件人 
		    message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver)); // 邮件的收件人 
		    message.setSubject(subject); // 邮件的标题
		    message.setText(notifyVo.getMessageContent());
		    message.saveChanges();

		    // 5、发送邮件
		    ts.sendMessage(message, message.getAllRecipients());
		    ts.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		try {
			// String ENCODEING = "UTF-8";
			String host = "smtp.163.com";// "smtp.qq.com"; // 服务器地址
			String sender = "lsj24legend@163.com"; // 发件人的邮箱
		    String receiver = "374682617@qq.com"; // 收件人的邮箱
			// String name = "天天都是实力派吃货"; // 发件人昵称
			String username = "lsj24legend@163.com"; // 账号
			String password = "htt374682617"; // 密码
			String subject = "四川师范大学设备管理系统--密码找回"; // 主题
			String messageContent = "<a href='https://www.baidu.com'>这是一封测试邮件，不管你信不信，我这的是想多说几句废话，时间是2016年4月17日凌晨两点半！</a>";

			Properties prop = new Properties();
			prop.setProperty("mail.host", host);
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.debug", "true");
			prop.setProperty("mail.smtp.auth", "true");

			 MailSSLSocketFactory sf = new MailSSLSocketFactory();
			 sf.setTrustAllHosts(true);
			 prop.put("mail.smtp.ssl.enable", "true");
			 prop.put("mail.smtp.ssl.socketFactory", sf);

			// 1、创建session
			Session session = Session.getInstance(prop);
			Transport ts = null;

			// 2、通过session得到transport对象
			ts = session.getTransport();

			// 3、连上邮件服务器
			ts.connect(host, username, password);

			// 4、创建邮件
			MimeMessage message = new MimeMessage(session);

			// 邮件消息头
			message.setFrom(new InternetAddress(sender)); // 邮件的发件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver)); // 邮件的收件人
			message.setSubject(subject); // 邮件的标题
			message.setText(messageContent);
			message.saveChanges();

			// 5、发送邮件
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
