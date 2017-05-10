/*
 * 文件名：FileHelper.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： FileHelper.java
 * 修改人：KOBE
 * 修改时间：2016年3月28日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.helper;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sicnu.liaoshijie.labEqmMS.model.FileUploadResultVo;

/**
 * TODO 文件处理Helper.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * 
 * <pre>
 * </pre>
 * 
 * @author KOBE
 */
public class FileHelper {
    /**
     * logHelper，日志记录组件.
     */
    @Resource(name = "logHelper")
    private static LogHelper logHelper;

    /**
     * TODO 上传文件.
     * 
     * @param request
     *            参数
     * @param file
     *            参数
     * 
     * @return boolean
     */
    public static FileUploadResultVo uploadFile(HttpServletRequest request, MultipartFile file) {
        FileUploadResultVo resultVo = new FileUploadResultVo();

        if (file.isEmpty()) { // 文件为空
            resultVo.setSucess(false);
            return resultVo;
        }

        try {
            // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中 )
            String fileName = file.getOriginalFilename().split("\\.")[0] + System.currentTimeMillis() + "." + file.getOriginalFilename().split("\\.")[1];
            String filePath = request.getSession().getServletContext().getRealPath("/") + "Files/UploadFiles/" + fileName;
            File saveDir = new File(filePath);

            if (!saveDir.exists() && !saveDir.isDirectory()) { // 文件夹不存在就创建
                new File(request.getSession().getServletContext().getRealPath("/") + "Files/UploadFiles/").mkdir();
                // saveDir.mkdir();
            }

            // 转存文件
            file.transferTo(saveDir);
            resultVo.setFile(saveDir);
            resultVo.setFilePath(filePath);
            resultVo.setSucess(true);
        } catch (Exception e) {
            e.printStackTrace();

            logHelper.error("批量添加设备：上传Excel文件异常", e);
        }

        return resultVo;
    }

}
