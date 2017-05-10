/*
 * 文件名：EquipmentBo.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved.
 * 描述： EquipmentBo.java
 * 修改人：tianzhong
 * 修改时间：Mon Dec 28 18:36:18 CST 2015
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * TODO 表说明.
 *
 * @author tianzhong
 */
public class EquipmentVo {

    /**
     * @设备名.
     */
    private String eqmName;
    
    /**
     * @资产号.
     */
    private String propertyNo;

    /**
     * @设备价格.
     */
    private BigDecimal eqmPrice;

    /**
     * @设备描述.
     */
    private String description;

    /**
     * @设备状态.
     */
    private String eqmStatus; 

    /**
     * @设备管理员账号.
     */
    private String managerId;

    /**
     * @设备厂商.
     */
    private String eqmFactory;

    /**
     * @设备类别.
     */
    private String eqmClass;

    /**
     * @设备所在实验室.
     */
    private String eqmLab;

    /**
     * @主键ID 资产号.
     */
    private Integer keyID;

    /**
     * 设置eqmLab.
     * 
     * @return 返回eqmLab
     */
    public String getEqmLab() {
        return eqmLab;
    }

    /**
     * 获取eqmLab.
     * 
     * @param eqmLab
     *            要设置的eqmLab
     */
    public void setEqmLab(String eqmLab) {
        this.eqmLab = eqmLab;
    }

    /**
     * @采购人.
     */
    private String buyStaff;

    /**
     * @所属学院.
     */
    private String college;

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
     * @设备管理人.
     */
    private String manager;

    /**
     * @设备型号.
     */
    private String eqmType;

    /**
     * @采购时间.
     */
    private Date buyTime;

    /**
     * @记录修改时间
     */
    private Date modifyTime;

    /**
     * 
     * 无参数构造函数.
     * 
     */
    public EquipmentVo() {
        // 构造
    }

    /**
     * 
     * 有参数构造函数.
     *
     * @param var
     *            转换对象（Object自己换成要转的实际类型，这个构造只是一个模板）
     */
    public EquipmentVo(EquipmentVo var) {
        this.eqmName = var.getEqmName();
        this.eqmPrice = var.getEqmPrice();
        this.description = var.getDescription();
        this.eqmFactory = var.getEqmFactory();
        this.eqmClass = var.getEqmClass();
        this.keyID = var.getKeyID();
        this.buyStaff = var.getBuyStaff();
        this.college = var.getCollege();
        this.tmp2 = var.getTmp2();
        this.tmp1 = var.getTmp1();
        this.tmp3 = var.getTmp3();
        this.manager = var.getManager();
        this.eqmType = var.getEqmType();
        this.buyTime = var.getBuyTime();
        this.modifyTime = var.getModifyTime();
    }

    /**
     * 设置eqmName.
     * 
     * @return 返回eqmName
     */
    public String getEqmName() {
        return eqmName;
    }

    /**
     * 获取eqmName.
     * 
     * @param eqmName
     *            要设置的eqmName
     */
    public void setEqmName(String eqmName) {
        this.eqmName = eqmName;
    }

    /**
     * 设置eqmPrice.
     * 
     * @return 返回eqmPrice
     */
    public BigDecimal getEqmPrice() {
        return eqmPrice;
    }

    /**
     * 获取eqmPrice.
     * 
     * @param eqmPrice
     *            要设置的eqmPrice
     */
    public void setEqmPrice(BigDecimal eqmPrice) {
        this.eqmPrice = eqmPrice;
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
     * 设置eqmFactory.
     * 
     * @return 返回eqmFactory
     */
    public String getEqmFactory() {
        return eqmFactory;
    }

    /**
     * 获取eqmFactory.
     * 
     * @param eqmFactory
     *            要设置的eqmFactory
     */
    public void setEqmFactory(String eqmFactory) {
        this.eqmFactory = eqmFactory;
    }

    /**
     * 设置eqmClass.
     * 
     * @return 返回eqmClass
     */
    public String getEqmClass() {
        return eqmClass;
    }

    /**
     * 获取eqmClass.
     * 
     * @param eqmClass
     *            要设置的eqmClass
     */
    public void setEqmClass(String eqmClass) {
        this.eqmClass = eqmClass;
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
     * 设置buyStaff.
     * 
     * @return 返回buyStaff
     */
    public String getBuyStaff() {
        return buyStaff;
    }

    /**
     * 获取buyStaff.
     * 
     * @param buyStaff
     *            要设置的buyStaff
     */
    public void setBuyStaff(String buyStaff) {
        this.buyStaff = buyStaff;
    }

    /**
     * 设置college.
     * 
     * @return 返回college
     */
    public String getCollege() {
        return college;
    }

    /**
     * 获取college.
     * 
     * @param college
     *            要设置的college
     */
    public void setCollege(String college) {
        this.college = college;
    } 

    /**
     * 设置manager.
     * 
     * @return 返回manager
     */
    public String getManager() {
        return manager;
    }

    /**
     * 获取manager.
     * 
     * @param manager
     *            要设置的manager
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * 设置eqmType.
     * 
     * @return 返回eqmType
     */
    public String getEqmType() {
        return eqmType;
    }

    /**
     * 获取eqmType.
     * 
     * @param eqmType
     *            要设置的eqmType
     */
    public void setEqmType(String eqmType) {
        this.eqmType = eqmType;
    }

    /**
     * 设置buyTime.
     * 
     * @return 返回buyTime
     */
    public Date getBuyTime() {
        return buyTime;
    }

    /**
     * 获取buyTime.
     * 
     * @param buyTime
     *            要设置的buyTime
     */
    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
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
     * 设置modifyTime.
     * 
     * @return 返回modifyTime
     */
    public String getEqmStatus() {
        return eqmStatus;
    }

    /**
     * 获取eqmStatus.
     * 
     * @param eqmStatus
     *            要设置的eqmStatus
     */
    public void setEqmStatus(String eqmStatus) {
        this.eqmStatus = eqmStatus;
    } 

    /**
     * 设置managerId.
     * 
     * @return 返回managerId
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 获取managerId.
     * 
     * @param managerId
     *            要设置的managerId
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

	public String getPropertyNo() {
		return propertyNo;
	}

	public void setPropertyNo(String propertyNo) {
		this.propertyNo = propertyNo;
	}

	public String getTmp2() {
		return tmp2;
	}

	public void setTmp2(String tmp2) {
		this.tmp2 = tmp2;
	}

	public String getTmp1() {
		return tmp1;
	}

	public void setTmp1(String tmp1) {
		this.tmp1 = tmp1;
	}

	public String getTmp3() {
		return tmp3;
	}

	public void setTmp3(String tmp3) {
		this.tmp3 = tmp3;
	}
	
	
}
