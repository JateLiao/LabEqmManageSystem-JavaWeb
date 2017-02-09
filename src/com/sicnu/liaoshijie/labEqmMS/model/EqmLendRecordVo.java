/*
 * 文件名：EqmLendRecordVo.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EqmLendRecordVo.java
 * 修改人：KOBE
 * 修改时间：2016年4月23日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.model;

import java.util.Date;

/**
 * TODO 设备借入借出model.
 * 
 * @author     KOBE
 */
public class EqmLendRecordVo {

	/**
	 * 添加字段注释.
	 */
	private String keyID;
	
	/**
	 * 添加字段注释.
	 */
	private String eqmName;
	
	/**
	 * 添加字段注释.
	 */
	private String propertyNo;
	
	/**
	 * 添加字段注释.
	 */
	private String lenderId; 
	
	/**
	 * 添加字段注释.
	 */
	private String lenderName; 
	
	
	
	public String getLenderName() {
		return lenderName;
	}
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	/**
	 * 添加字段注释.
	 */
	private String lenderLevel;
	
	/**
	 * 添加字段注释.
	 */
	private Date lendDate;
	
	/**
	 * 添加字段注释.
	 */
	private String application;
	
	/**
	 * 添加字段注释.
	 */
	private Date planReturnDate;
	
	/**
	 * 添加字段注释.
	 */
	private Date actualReturnDate;
	
	/**
	 * 添加字段注释.
	 */
	private String adminId;
	
	/**
	 * 添加字段注释.
	 */
	private String adminName;
	
	/**
	 * 添加字段注释.
	 */
	private String handleStatus;
	
	/**
	 * 添加字段注释.
	 */
	private String handleStatusName;
	/**
	 * 添加字段注释.
	 */
	private String handleDate;
	
	/**
	 * 添加字段注释.
	 */
	private String handleReason;
	
	/**
	 * 添加字段注释.
	 */
	private String tmp1;
	
	/**
	 * 添加字段注释.
	 */
	private String tmp2;
	
	/**
	 * 添加字段注释.
	 */
	private String tmp3;
	
	
	public String getKeyID() {
		return keyID;
	}
	public void setKeyID(String keyId) {
		this.keyID = keyId;
	}
	public String getEqmId() {
		return propertyNo;
	}
	public void setEqmId(String eqmId) {
		this.propertyNo = eqmId;
	}
	public String getLenderId() {
		return lenderId;
	}
	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}
	public String getLenderLevel() {
		return lenderLevel;
	}
	public void setLenderLevel(String lenderLevel) {
		this.lenderLevel = lenderLevel;
	}
	public Date getLendDate() {
		return lendDate;
	}
	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public Date getPlanReturnDate() {
		return planReturnDate;
	}
	public void setPlanReturnDate(Date planReturnDate) {
		this.planReturnDate = planReturnDate;
	}
	public Date getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(Date actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	public String getHandleStatus() {
		return handleStatus;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
	public String getHandleStatusName() {
		return handleStatusName;
	}
	public void setHandleStatusName(String handleStatusName) {
		this.handleStatusName = handleStatusName;
	}
	public String getHandleDate() {
		return handleDate;
	}
	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}
	public String getTmp1() {
		return tmp1;
	}
	public void setTmp1(String tmp1) {
		this.tmp1 = tmp1;
	}
	public String getTmp2() {
		return tmp2;
	}
	public void setTmp2(String tmp2) {
		this.tmp2 = tmp2;
	}
	public String getTmp3() {
		return tmp3;
	}
	public void setTmp3(String tmp3) {
		this.tmp3 = tmp3;
	} 
	public String getPropertyNo() {
		return propertyNo;
	}
	public void setPropertyNo(String propertyNo) {
		this.propertyNo = propertyNo;
	}
	public String getHandleReason() {
		return handleReason;
	}
	public void setHandleReason(String handleReason) {
		this.handleReason = handleReason;
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getEqmName() {
		return eqmName;
	}
	public void setEqmName(String eqmName) {
		this.eqmName = eqmName;
	}
	
}
