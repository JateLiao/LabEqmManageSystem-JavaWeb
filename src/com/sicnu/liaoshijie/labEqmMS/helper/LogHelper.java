/*
 * 文件名：LogHelper.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： LogHelper.java
 * 修改人：tianzhong
 * 修改时间：2016年1月25日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.helper;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * TODO 日志记录类.
 * <p>
 * TODO 把日志组件设计成单例模式
 * <p>
 * TODO 示例代码
 * 
 * <pre>
 * </pre>
 * 
 * @author tianzhong
 */
@Component("logHelper")
public class LogHelper {
    // error,warn,info,debug

    /**
     * logger.
     */
    private Logger logger = Logger.getRootLogger();

    /**
     * TODO error日志记录.
     * 
     * @param info
     *            .
     * @param e
     *            .
     */
    public void error(String info, Exception e) {
        logger.error(info + ">>" + e.getMessage());
    }

    /**
     * TODO debug日志记录.
     * 
     * @param info
     *            .
     * @param e
     *            .
     */
    public void debug(String info, Exception e) {
        logger.debug(info + ">>" + e.getMessage());
    }

    /**
     * TODO debug日志记录.
     * 
     * @param info
     *            .
     */
    public void info(String info) {
        logger.info(info);
    }
}
