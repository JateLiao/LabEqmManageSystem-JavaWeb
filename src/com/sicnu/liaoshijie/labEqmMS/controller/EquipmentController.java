/*
 * 文件名：EquipmentController.java
 * 版权：Copyright 2007-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EquipmentController.java
 * 修改人：tianzhong
 * 修改时间：2016年1月18日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.commons.StatusCodeEnum;
import com.sicnu.liaoshijie.labEqmMS.constant.AdminRole;
import com.sicnu.liaoshijie.labEqmMS.constant.EquipmentHandleResult;
import com.sicnu.liaoshijie.labEqmMS.dao.IEquipmentDao;
import com.sicnu.liaoshijie.labEqmMS.helper.ExcelHelper;
import com.sicnu.liaoshijie.labEqmMS.helper.LogHelper;
import com.sicnu.liaoshijie.labEqmMS.model.AdministratorVo;
import com.sicnu.liaoshijie.labEqmMS.model.EquipmentVo;
import com.sicnu.liaoshijie.labEqmMS.service.IAdministrtorService;
import com.sicnu.liaoshijie.labEqmMS.service.IEquipmentService;
import com.sicnu.liaoshijie.labEqmMS.util.JsonUtils;
import com.sicnu.liaoshijie.labEqmMS.util.ParamMapUtil;
import com.sicnu.liaoshijie.labEqmMS.util.StringUtil;

/**
 * TODO EquipmentController.
 * 
 * @author tianzhong
 */
@Controller
@RequestMapping("/equipments")
public class EquipmentController {
    /**
     * logHelper，日志记录组件.
     */
    @Resource(name = "logHelper")
    private LogHelper logHelper;

    /**
     * equipmentService.
     */
    @Resource(name = "equipmentService")
    private IEquipmentService equipmentService;

    /**
     * equipmentDao.
     */
    @Resource(name = "equipmentDao")
    private IEquipmentDao equipmentDao;

    /**
     * administrtorService.
     */
    @Resource(name = "administrtorService")
    private IAdministrtorService administrtorService;

    /**
     * TODO 分页显示，获取每页数据.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/page.htm")
    public void getEquipmentPageList(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始设备分页查询...");

        String result = "";
        PrintWriter out = null;
        try {
            out = res.getWriter();

            Map<String, Object> paramMap = ParamMapUtil.getEuipmentQueryParamMap(req);
            Response<Integer> resAllSize = equipmentService.getEquipmentCount(paramMap); // 获取所有数量
            Response<List<EquipmentVo>> response = equipmentService.queryEuipmentByPage(paramMap);
            logHelper.info("设备分页总量以及内容查询完毕！");
            if (response != null && response.getContent() != null) {
                // 拼接发送到前端的json串
                if (resAllSize.getContent() < 1) {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":[]}";
                } else {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":" + JsonUtils.toJson(response.getContent()) + "}";
                }
            }

            out.write(result);
            logHelper.info("设备分页查询发送到前端完毕！");

        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("设备分页异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }

    /**
     * TODO 更新设备.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/updateEquipment.htm")
    public void updateEquipment(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始更新设备！");
        String result = "";
        PrintWriter out = null;

        try {
            out = res.getWriter();
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            EquipmentVo eqmVo = null;

            if (StringUtil.isNullOrEmpty(param)) {
                logHelper.info("更新异常：参数为空！");
                result = EquipmentHandleResult.UPDATE_EQM_NULL_PARAM;
                out.write(JsonUtils.toJson(result));
                return;
            }

            eqmVo = JsonUtils.toObject(param, EquipmentVo.class);
            AdministratorVo adminVo = new AdministratorVo();
            adminVo.setAdminID(eqmVo.getManagerId());
            adminVo.setAdminName(eqmVo.getManager());
            Response<List<AdministratorVo>> adminResponse = administrtorService.queryAdminList(adminVo);
            String level = adminResponse.getContent().get(0).getAdminLevel();
            if (adminResponse == null || adminResponse.getContent() == null || adminResponse.getContent().size() < 1) { // 管理人的账号和姓名不匹配
                result = EquipmentHandleResult.UPDATE_MANAGER_ID_NAME_NOT_MATCH;
                out.print(JsonUtils.toJson(result));
                return;
            } else if (AdminRole.STUDENT.equals(level) || AdminRole.NORMAL_STAFF.equals(level)) {
                result = EquipmentHandleResult.UPDATE_NOT_ADMINISTRATOR;
                out.print(JsonUtils.toJson(result));
                return;
            }

            Response<Integer> response = equipmentService.updateEquipment(eqmVo); // 更新设备信息
            if (response != null) {
                result = response.getContent() >= 1 ? EquipmentHandleResult.SUCCESS : EquipmentHandleResult.UPDATE_EQM_FAILED;
            }

            out.write(JsonUtils.toJson(result));
            logHelper.info("更新设备结束！");
        } catch (Exception e) {
            e.printStackTrace();
            result = EquipmentHandleResult.UPDATE_EQM_FAILED;
            out.write(JsonUtils.toJson(result));
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 添加设备.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/addEquipment.htm")
    public void addEquipment(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始添加设备！");
        String result = "";
        PrintWriter out = null;

        try {
            out = res.getWriter();
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            EquipmentVo eqmVo = null;

            if (StringUtil.isNullOrEmpty(param)) {
                logHelper.info("添加设备异常：参数为空！");
                result = EquipmentHandleResult.ADD_EQM_NULL_PARAM;
                out.write(JsonUtils.toJson(result));
                return;
            }

            eqmVo = JsonUtils.toObject(param, EquipmentVo.class);
            AdministratorVo adminVo = new AdministratorVo();
            adminVo.setAdminID(eqmVo.getManagerId());
            adminVo.setAdminName(eqmVo.getManager());
            Response<List<AdministratorVo>> adminResponse = administrtorService.queryAdminList(adminVo);
            String level = adminResponse.getContent().get(0).getAdminLevel();
            if (adminResponse == null || adminResponse.getContent() == null || adminResponse.getContent().size() < 1) { // 管理人的账号和姓名不匹配
                result = EquipmentHandleResult.ADD_MANAGER_ID_NAME_NOT_MATCH;
                out.print(JsonUtils.toJson(result));
                return;
            } else if (AdminRole.STUDENT.equals(level) || AdminRole.NORMAL_STAFF.equals(level)) {
                result = EquipmentHandleResult.ADD_NOT_ADMINISTRATOR;
                out.print(JsonUtils.toJson(result));
                return;
            }

            Response<Integer> response = equipmentService.insertEquipment(eqmVo); // 添加设备
            if (response != null) {
                result = response.getContent() >= 1 ? EquipmentHandleResult.ADD_EQM_SUCCESS : EquipmentHandleResult.ADD_EQM_FAILED;
            }

            out.write(JsonUtils.toJson(result));
            logHelper.info("添加设备结束！");
        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("添加设备异常：" + e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 删除设备.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/delete.htm")
    public void deleteEquipment(HttpServletRequest req, HttpServletResponse res) {
        String result = "failure";
        String param = req.getParameter("param");

        logHelper.info("开始删除选中设备，ID：" + param);

        String[] ids = param.split(",");
        List<EquipmentVo> list = new ArrayList<EquipmentVo>();

        for (int i = 0; i < ids.length; i++) {
            EquipmentVo e = new EquipmentVo();
            e.setKeyID(Integer.parseInt(ids[i]));
            list.add(e);
        }

        PrintWriter out = null;
        try {
            out = res.getWriter();
            Response<Integer> response = equipmentService.deleteEquipmentBatch(list); // 删除设备
            if (null != response && response.getCode() == StatusCodeEnum.SUCCESS.getValue()) {
                result = "success";
            }

            out.write(JsonUtils.toJson(result));

            logHelper.info("设备删除成功！");
        } catch (IOException e) {
            e.printStackTrace();
            logHelper.error("删除选中设备异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }

    /**
     * TODO 批量导入设备.
     * 
     * @param files
     *            上传文件参数
     * @param request
     *            请求
     * @param response
     *            响应
     */
    @RequestMapping("/importEquipment.htm")
    public void importEquipment(@RequestParam("myfiles") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter(); // 获取输出流

            for (MultipartFile file : files) {
            	//解析成List
            	List<EquipmentVo> eqmList = ExcelHelper.parseEquipmentExcelToList(file); 
                equipmentService.insertEquipmentList(eqmList); // 入库
            } 
            
//            List<FileUploadResultVo> resultVoList = new ArrayList<FileUploadResultVo>();
//            for (MultipartFile file : files) {
//                // 文件先上传到服务器指定目录
//                FileUploadResultVo resultVo = FileHelper.uploadFile(request, file);
//
//                logHelper.info(resultVo.getFile().getName() + "上传结果：" + resultVo.isSucess());
//                if (!resultVo.isSucess()) { // 只要一个文件上传失败都算失败
//                    logHelper.info("批量添加设备：上传Excel文件失败！上传失败的文件：" + resultVo.getFile().getName());
//
//                    out.print(EquipmentHandleResult.FAILED);
//                    return;
//                }
//
//                resultVoList.add(resultVo);
//            }
//
//            // 上传成功开始解析excel
//            for (FileUploadResultVo resultVo : resultVoList) {
//                List<EquipmentVo> eqmList = null;
//                eqmList = ExcelHelper.parseEquipmentExcelToList(resultVo.getFilePath());
//
//                if (eqmList == null) { // 解析失败
//                    logHelper.info(resultVo.getFile().getName() + "导入失败！");
//
//                    out.print(JsonUtils.toJson(EquipmentHandleResult.FAILED));
//                    return;
//                }
//                equipmentService.insertEquipmentList(eqmList); // 入库
//
//                // 导入成功，删除已上传的文件
//                resultVo.getFile().delete();
//            }

            logHelper.info("excel导入成功!");
            out.print(EquipmentHandleResult.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("解析Excel，批量导入设备异常", e);
            out.print(EquipmentHandleResult.FAILED);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 批量导入设备模板下载.
     * 
     * @param request
     *            请求
     * @param response
     *            响应
     * @throws Exception
     *             .
     */
    @RequestMapping("/downloadDocument.htm")
    public void downloadDocument(HttpServletRequest request, HttpServletResponse response) throws Exception {
        InputStream in = null;
        OutputStream out = null;
        try {
            // 获得请求文件名
            String oriName = "设备导入模板以及数据规范说明.rar";
            String filename = new String(oriName.getBytes("GBK"), "ISO-8859-1");

            // 设置文件MIME类型
            response.setContentType(request.getSession().getServletContext().getMimeType(filename));
            // 设置Content-Disposition
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            // 读取目标文件，通过response将目标文件写到客户端
            // 获取目标文件的绝对路径
            String fullFileName = request.getSession().getServletContext().getRealPath("/") + "Files/DownloadFiles/" + oriName;
            // 读取文件
            in = new BufferedInputStream(new FileInputStream(fullFileName));
            out = new BufferedOutputStream(response.getOutputStream());

            // 写文件
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }

            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 批量导出设备.
     * 
     * @param request
     *            请求
     * @param response
     *            响应
     */
    @RequestMapping("/exportEquipmentExcel.htm")
    public void exportEquipmentExcel(HttpServletRequest request, HttpServletResponse response) {
        logHelper.info("设备导出为Excel文件开始！");
        int page = Integer.parseInt(request.getParameter("page"));

        try {
            Map<String, Object> paramMap = ParamMapUtil.getEuipmentQueryParamMap(request);
            Response<List<EquipmentVo>> queryResponse = equipmentService.queryEuipmentByPage(paramMap);
            logHelper.info("设备分页总量以及内容查询完毕！");
            if (queryResponse != null && queryResponse.getContent() != null) {
                HSSFWorkbook workbook = ExcelHelper.exportEquipmentExcel(queryResponse.getContent());
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-disposition", "attachment;filename=" + new String("设备列表".getBytes("GBK"), "ISO-8859-1") + page + ".xls");
                OutputStream ouputStream = response.getOutputStream();
                workbook.write(ouputStream);
                ouputStream.flush();
                ouputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("设备导出为Excel文件异常", e);
        }
    }
}
