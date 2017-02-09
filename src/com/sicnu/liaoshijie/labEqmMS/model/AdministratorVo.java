/*
 * 文件名：EquipmentBo.java
 * 版权：Copyright 2007-2015 KOBE Tech. Co. Ltd. All Rights Reserved.
 * 描述： EquipmentBo.java
 * 修改人：tianzhong
 * 修改时间：Thu Dec 31 17:20:31 CST 2015
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.model;

import java.util.Date;

/**
 * TODO 管理员信息.
 *
 * @author tianzhong
 */
public class AdministratorVo {

    /**
     * @管理员账号.
     */
    private String adminID;

    /**
     * @管理员姓名.
     */
    private String adminName;

    /**
     * @描述.
     */
    private String description;

    /**
     * @主键ID.
     */
    private Integer keyID;

    /**
     * @管理员身份证号.
     */
    private String adminIDCardNo;

    /**
     * @修改时间.
     */
    private Date modifyTime;

    /**
     * @管理员生日.
     */
    private Date adminBirthday;

    /**
     * @管理员联系方式.
     */
    private String adminTel;

    /**
     * @管理员邮箱.
     */
    private String adminEmail;

    /**
     * 设置adminTel.
     * 
     * @return 返回adminTel
     */
    public String getAdminTel() {
        return adminTel;
    }

    /**
     * @管理员性别.
     */
    private String adminSex;

    /**
     * @管理员部门.
     */
    private String adminDept;

    /**
     * @扩展字段2.
     */
    private String tmp2;

    /**
     * @扩展字段1.
     */
    private String tmp1;

    /**
     * @扩展字段3.
     */
    private String tmp3;

    /**
     * @密码.
     */
    private String passWord;
    
    /**
     * @住址.
     */
    private String adminAddress;
    
    /**
     * @籍贯.
     */
    private String adminNativePlace;

    public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	public String getAdminNativePlace() {
		return adminNativePlace;
	}

	public void setAdminNativePlace(String adminNativePlace) {
		this.adminNativePlace = adminNativePlace;
	}

	/**
     * @管理员所属学院.
     */
    private String adminCollege;

    /**
     * @管理员级别.
     */
    private String adminLevel;

    /**
     * 获取adminTel.
     * 
     * @param adminTel
     *            要设置的adminTel
     */
    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel;
    }  

    /**
     * 
     * 无参数构造函数.
     * 
     */
    public AdministratorVo() {
        // 构造
    }

    /**
     * 
     * 有参数构造函数.
     *
     * @param var
     *            转换对象（Object自己换成要转的实际类型，这个构造只是一个模板）
     */
    public AdministratorVo(AdministratorVo var) {
        this.adminID = var.getAdminID();
        this.adminName = var.getAdminName();
        this.description = var.getDescription();
        this.keyID = var.getKeyID();
        this.modifyTime = var.getModifyTime();
        this.adminBirthday = var.getAdminBirthday();
        this.adminSex = var.getAdminSex();
        this.adminDept = var.getAdminDept();
        this.tmp2 = var.getTmp2();
        this.tmp1 = var.getTmp1();
        this.tmp3 = var.getTmp3();
        this.passWord = var.getPassWord();
        this.adminCollege = var.getAdminCollege();
        this.adminLevel = var.getAdminLevel();
    }

    /**
     * 设置adminID.
     * 
     * @return 返回adminID
     */
    public String getAdminID() {
        return adminID;
    }

    /**
     * 获取adminID.
     * 
     * @param adminID
     *            要设置的adminID
     */
    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    /**
     * 设置adminName.
     * 
     * @return 返回adminName
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * 获取adminName.
     * 
     * @param adminName
     *            要设置的adminName
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * 设置description.
     * 
     * @return 返回description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 获取description.
     * 
     * @param description
     *            要设置的description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 设置keyID.
     * 
     * @return 返回keyID
     */
    public Integer getKeyID() {
        return keyID;
    }

    /**
     * 获取keyID.
     * 
     * @param keyID
     *            要设置的keyID
     */
    public void setKeyID(Integer keyID) {
        this.keyID = keyID;
    }

    /**
     * 设置modifyTime.
     * 
     * @return 返回modifyTime
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 获取modifyTime.
     * 
     * @param modifyTime
     *            要设置的modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 设置adminBirthday.
     * 
     * @return 返回adminBirthday
     */
    public Date getAdminBirthday() {
        return adminBirthday;
    }

    /**
     * 获取adminBirthday.
     * 
     * @param adminBirthday
     *            要设置的adminBirthday
     */
    public void setAdminBirthday(Date adminBirthday) {
        this.adminBirthday = adminBirthday;
    }

    /**
     * 设置adminSex.
     * 
     * @return 返回adminSex
     */
    public String getAdminSex() {
        return adminSex;
    }

    /**
     * 获取adminSex.
     * 
     * @param adminSex
     *            要设置的adminSex
     */
    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    /**
     * 设置adminDept.
     * 
     * @return 返回adminDept
     */
    public String getAdminDept() {
        return adminDept;
    }

    /**
     * 获取adminDept.
     * 
     * @param adminDept
     *            要设置的adminDept
     */
    public void setAdminDept(String adminDept) {
        this.adminDept = adminDept;
    }

    /**
     * 设置passWord.
     * 
     * @return 返回passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 获取passWord.
     * 
     * @param passWord
     *            要设置的passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 设置tmp2.
     * 
     * @return 返回tmp2
     */
    public String getTmp2() {
        return tmp2;
    }

    /**
     * 获取tmp2.
     * 
     * @param tmp2
     *            要设置的tmp2
     */
    public void setTmp2(String tmp2) {
        this.tmp2 = tmp2;
    }

    /**
     * 设置tmp1.
     * 
     * @return 返回tmp1
     */
    public String getTmp1() {
        return tmp1;
    }

    /**
     * 获取tmp1.
     * 
     * @param tmp1
     *            要设置的tmp1
     */
    public void setTmp1(String tmp1) {
        this.tmp1 = tmp1;
    }

    /**
     * 设置tmp3.
     * 
     * @return 返回tmp3
     */
    public String getTmp3() {
        return tmp3;
    }

    /**
     * 获取tmp3.
     * 
     * @param tmp3
     *            要设置的tmp3
     */
    public void setTmp3(String tmp3) {
        this.tmp3 = tmp3;
    }

    /**
     * 设置adminCollege.
     * 
     * @return 返回adminCollege
     */
    public String getAdminCollege() {
        return adminCollege;
    }

    /**
     * 获取adminCollege.
     * 
     * @param adminCollege
     *            要设置的adminCollege
     */
    public void setAdminCollege(String adminCollege) {
        this.adminCollege = adminCollege;
    }

    /**
     * 设置adminLevel.
     * 
     * @return 返回adminLevel
     */
    public String getAdminLevel() {
        return adminLevel;
    }

    /**
     * 获取adminLevel.
     * 
     * @param adminLevel
     *            要设置的adminLevel
     */
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    /**
     * 获取adminIDCardNo.
     * 
     * @return String
     */
    public String getAdminIDCardNo() {
        return adminIDCardNo;
    }

    /**
     * 设置 adminLevel.
     * 
     * @param adminIDCardNo
     *            要设置的adminLevel
     */
    public void setAdminIDCardNo(String adminIDCardNo) {
        this.adminIDCardNo = adminIDCardNo;
    }
}
