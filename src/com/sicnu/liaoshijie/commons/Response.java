/*
 * 文件名：Response.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Response.java
 * 修改人：tianzhong
 * 修改时间：2015年12月30日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.commons;

import java.io.Serializable;

/**
 * TODO Response.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * 
 * <pre>
 * </pre>
 * 
 * @author tianzhong
 * 
 * @param <T>
 *            泛型.
 */
public class Response<T> implements Serializable {
    /**
     * 添加字段注释.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 添加字段注释.
     */
    private int code;

    /**
     * 添加字段注释.
     */
    private String message;

    /**
     * 添加字段注释.
     */
    private T content;

    /**
     * 添加字段注释.
     */
    private long time;

    /**
     * 构造函数.
     * 
     */
    public Response() {
        this.time = System.currentTimeMillis();
    }

    /**
     * 构造函数.
     * 
     * @param code
     *            .
     * @param message
     *            .
     * @param content
     *            .
     */
    public Response(int code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
        this.time = System.currentTimeMillis();
    }

    /**
     * TODO 添加方法注释.
     * 
     * @return int
     */
    public int getCode() {
        return this.code;
    }

    /**
     * TODO 添加方法注释.
     * 
     * @param code
     *            .
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * TODO 添加方法注释.
     * 
     * @return String
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * TODO 添加方法注释.
     * 
     * @param message
     *            .
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * TODO 添加方法注释.
     * 
     * @return T
     */
    public T getContent() {
        return this.content;
    }

    /**
     * TODO 添加方法注释.
     * 
     * @param content
     *            .
     */
    public void setContent(T content) {
        this.content = content;
    }

    /**
     * TODO 添加方法注释.
     * 
     * @return
     */
    /**
     * TODO 添加方法注释.
     * 
     * @return long
     */
    public long getTime() {
        return this.time;
    }

    /**
     * TODO 添加方法注释.
     * 
     * @param time
     *            .
     */
    public void setTime(long time) {
        this.time = time;
    }
}
