/*
 * 文件名：StatusCode.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： StatusCode.java
 * 修改人：tianzhong
 * 修改时间：2016年1月27日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.commons;

/**
 * TODO 状态码公共枚举.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * 
 * <pre>
 * </pre>
 * 
 * @author tianzhong
 */
public enum StatusCodeEnum {
    /**
     * 通知成功
     */
    SUCCESS(1, "成功"),
    
    /**
     * 添加字段注释.
     */
    SUCCESS_RES(99, "success"),

    /**
     * 请求错误.
     */
    BAD_REQUEST(2, "请求错误"),

    /**
     * 没有找到相关服务.
     */
    NOT_FOUND_SERVICE(3, "没有找到相关服务"),

    /**
     * 程序错误.
     */
    ERROR(4, "程序错误"),

    /**
     * 缺失参数.
     */
    PARAMETER_IS_NULL(5, "缺失参数"),

    /**
     * 参数错误.
     */
    PARAMETER_ERROR(6, "参数错误"),

    /**
     * 参数类型错误.
     */
    PARAMETER_TYPE_ERROR(7, "参数类型错误");

    /**
     * 值.
     */
    private final int value;

    /**
     * 描述.
     */
    private final String desc;

    /**
     * 构造函数.
     * 
     * @param value
     *            值
     * @param desc
     *            描述
     */
    private StatusCodeEnum(int value, String desc) {
        this.desc = desc;
        this.value = value;
    }

    /**
     * TODO 添加方法注释.
     * 
     * @return int
     */
    public int getValue() {
        return value;
    }

    /**
     * TODO 添加方法注释.
     * 
     * @return String
     */
    public String getDesc() {
        return desc;
    }
}
