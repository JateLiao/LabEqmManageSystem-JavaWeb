/*
 * 文件名：FileUploadResultVo.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： FileUploadResultVo.java
 * 修改人：tianzhong
 * 修改时间：2016年3月30日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.model;

import java.io.File;

/**
 * TODO 文件上传结果model.
 * 
 * @author tianzhong
 */
public class FileUploadResultVo {
    /**
     * 上传结果：成功，失败.
     */
    private boolean isSucess;

    /**
     * 上传到服务器的路径.
     */
    private String filePath;

    /**
     * file.
     */
    private File file;

    /**
     * 设置isSucess.
     * 
     * @return 返回isSucess
     */
    public boolean isSucess() {
        return isSucess;
    }

    /**
     * 获取isSucess.
     * 
     * @param s
     *            要设置的isSucess
     */
    public void setSucess(boolean s) {
        this.isSucess = s;
    }

    /**
     * 设置filePath.
     * 
     * @return 返回filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 获取filePath.
     * 
     * @param filePath
     *            要设置的filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 设置file.
     * 
     * @return 返回file
     */
    public File getFile() {
        return file;
    }

    /**
     * 获取file.
     * 
     * @param file
     *            要设置的file
     */
    public void setFile(File file) {
        this.file = file;
    }
}
