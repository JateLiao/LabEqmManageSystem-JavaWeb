/*
 * 文件名：ImportResultConstant.java
 * 版权：Copyright 2007-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ImportResultConstant.java
 * 修改人：tianzhong
 * 修改时间：2016年3月31日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.constant;

/**
 * TODO 设备处理结果常量.
 * 
 * @author tianzhong
 */
public class EquipmentHandleResult {
    /**
     * 成功.
     */
    public static final String ADD_EQM_SUCCESS = "success";

    /**
     * 没有文件.
     */
    public static final String NO_FILE = "no_file";

    /**
     * 添加设备失败统称.
     */
    public static final String ADD_EQM_FAILED = "添加失败！";

    /**
     * 添加设备空参数.
     */
    public static final String ADD_EQM_NULL_PARAM = "参数为空，添加失败！";

    /**
     * 更新设备空参数.
     */
    public static final String UPDATE_EQM_NULL_PARAM = "参数为空，更新失败！";

    /**
     * 更新设备失败.
     */
    public static final String UPDATE_EQM_FAILED = "更新设备失败！";
    
    /**
     * 管理人的账号和姓名不匹配.
     */
    public static final String ADD_MANAGER_ID_NAME_NOT_MATCH = "添加失败：托管人账号和姓名不匹配，请仔细核对！";
    
    /**
     * 管理人的账号和姓名不匹配.
     */
    public static final String UPDATE_MANAGER_ID_NAME_NOT_MATCH = "修改失败：托管人账号和姓名不匹配，请仔细核对！";
    
    /**
     * 账号角色不是管理员.
     */
    public static final String UPDATE_NOT_ADMINISTRATOR = "修改失败：该账号角色不是管理员！";
    
    /**
     * 账号角色不是管理员.
     */
    public static final String ADD_NOT_ADMINISTRATOR = "添加失败：该账号角色不是管理员！";

    /**
     * 成功统称.
     */
    public static final String SUCCESS = "success";

    /**
     * 失败统称.
     */
    public static final String FAILED = "failed";

}
