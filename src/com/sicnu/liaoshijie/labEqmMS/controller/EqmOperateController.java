/*
 * 文件名：EqmLendReturnController.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EqmLendReturnController.java
 * 修改人：KOBE
 * 修改时间：2016年4月23日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.labEqmMS.helper.LogHelper;
import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;
import com.sicnu.liaoshijie.labEqmMS.model.EqmRepairRecordVo;
import com.sicnu.liaoshijie.labEqmMS.model.EqmScrapRecordVo;
import com.sicnu.liaoshijie.labEqmMS.model.EquipmentVo;
import com.sicnu.liaoshijie.labEqmMS.service.IAdministrtorService;
import com.sicnu.liaoshijie.labEqmMS.service.IEqmLendRecordService;
import com.sicnu.liaoshijie.labEqmMS.service.IEqmRepairRecordService;
import com.sicnu.liaoshijie.labEqmMS.service.IEqmScrapRecordService;
import com.sicnu.liaoshijie.labEqmMS.service.IEquipmentService;
import com.sicnu.liaoshijie.labEqmMS.util.JsonUtils;
import com.sicnu.liaoshijie.labEqmMS.util.ParamMapUtil;
import com.sicnu.liaoshijie.labEqmMS.util.StringUtil;

/**
 * TODO 设备借出，归还，报修.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * <pre>
 * </pre>
 * 
 * @author     KOBE
 */
@Controller
@RequestMapping("/eqmOperate")
public class EqmOperateController {
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
     * administrtorService.
     */
    @Resource(name = "administrtorService")
    private IAdministrtorService administrtorService;
    
    /**
     * eqmLendRecordService.
     */
    @Resource(name = "eqmLendRecordService")
    private IEqmLendRecordService eqmLendRecordService;
    
    /**
     * eqmRepairRecordService
     */
    @Resource(name="eqmRepairRecordService")
    private IEqmRepairRecordService eqmRepairRecordService;
    
    /**
     * eqmScrapRecordService
     */
    @Resource(name="eqmScrapRecordService")
    private IEqmScrapRecordService eqmScrapRecordService;
    
    /**
     * TODO 借出申请.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/lendoutApply.htm")
    public void lendoutApply(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始借出申请！");
        PrintWriter out = null;

        try {
            out = res.getWriter();
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            EqmLendRecordVo vo = null;

            if (StringUtil.isNullOrEmpty(param)) {
                logHelper.info("借出申请异常：参数为空！");
                out.write("failed");
                return;
            }

            vo = JsonUtils.toObject(param, EqmLendRecordVo.class);

            Response<Integer> response = eqmLendRecordService.addLendOutApply(vo);
            if (response != null) {
            	if (response.getContent() >= 1) {
            		List<EquipmentVo> list = new ArrayList<EquipmentVo>();
            		EquipmentVo eqmVo = new EquipmentVo();
            		eqmVo.setEqmStatus("已借出");
            		eqmVo.setPropertyNo(vo.getPropertyNo());
            		list.add(eqmVo);
            		equipmentService.updateEquipmentStatusByPropertyNo(list); // 申请成功则修改设备状态
            		out.write("success");
				} else {
					out.write("failed");
					logHelper.info("借出申请失败");
            		return;
				}
            }

            logHelper.info("更新设备结束！");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("failed");
            logHelper.error("借出申请异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * TODO 报修申请.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/repairApply.htm")
    public void repairApply(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始报修申请！");
        PrintWriter out = null;

        try {
            out = res.getWriter();
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            EqmRepairRecordVo vo = null;

            vo = JsonUtils.toObject(param, EqmRepairRecordVo.class);

            Response<Integer> response = eqmRepairRecordService.repairApply(vo);
            if (response != null) {
            	if (response.getContent() >= 1) {
            		List<EquipmentVo> list = new ArrayList<EquipmentVo>();
            		EquipmentVo eqmVo = new EquipmentVo();
            		eqmVo.setEqmStatus("维修中");
            		eqmVo.setPropertyNo(vo.getPropertyNo());
            		list.add(eqmVo);
            		equipmentService.updateEquipmentStatusByPropertyNo(list); // 申请成功则修改设备状态
            		out.write("success");
				} else {
					out.write("failed");
					logHelper.info("报修申请失败");
            		return;
				}
            }

            logHelper.info("报修设备结束！");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("failed");
            logHelper.error("报修申请异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    } 
    
    /**
     * TODO 报废申请.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/scrapApply.htm")
    public void scrapApply(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始报废申请！");
        PrintWriter out = null;

        try {
            out = res.getWriter();
            String param = new String(req.getParameter("param").getBytes("iso8859-1"), "utf-8");
            EqmScrapRecordVo vo = null;

            vo = JsonUtils.toObject(param, EqmScrapRecordVo.class);

            Response<Integer> response = eqmScrapRecordService.scrapApply(vo);
            if (response != null) {
            	if (response.getContent() >= 1) {
            		List<EquipmentVo> list = new ArrayList<EquipmentVo>();
            		EquipmentVo eqmVo = new EquipmentVo();
            		eqmVo.setEqmStatus("已报废");
            		eqmVo.setPropertyNo(vo.getPropertyNo());
            		list.add(eqmVo);
            		equipmentService.updateEquipmentStatusByPropertyNo(list); // 申请成功则修改设备状态
            		out.write("success");
				} else {
					out.write("failed");
					logHelper.info("报废申请失败");
            		return;
				}
            }

            logHelper.info("报废设备结束！");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("failed");
            logHelper.error("报废申请异常", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    } 
    
    /**
     * TODO 分页显示，获取每页数据.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/lendOutPage.htm")
    public void getEquipmentPageList(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始借出记录分页查询...");

        String result = "";
        PrintWriter out = null;
        try {
            out = res.getWriter();

            Map<String, Object> paramMap = ParamMapUtil.getLendOutQueryParamMap(req);
            Response<Integer> resAllSize = eqmLendRecordService.getLendRecordCount(paramMap); // 获取所有数量
            Response<List<EqmLendRecordVo>> response = eqmLendRecordService.queryLendRecordByPage(paramMap);
            logHelper.info("借出记录分页总量以及内容查询完毕！");
            if (response != null && response.getContent() != null) {
            	// 拼接发送到前端的json串 
                if (resAllSize.getContent() < 1) {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":[]}";
                } else {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":" + JsonUtils.toJson(response.getContent()) + "}";
                }
			} 
            
            out.write(result);
            logHelper.info("借出记录分页查询发送到前端完毕！");

        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("借出记录分页异常", e);
        } finally {
            if (out != null) { 
                out.close();
            }
        }
    }
    
    /**
     * TODO 归还设备.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/returnEqm.htm")
    public void returnEqm(HttpServletRequest req, HttpServletResponse res) {
        String pNoParam = req.getParameter("pNoParam");
        String idParam = req.getParameter("idParam");

        logHelper.info("开始归还选中设备，资产号：" + pNoParam);

        String[] pNos = pNoParam.split(",");
        String[] ids = idParam.split(",");
        List<EquipmentVo> list = new ArrayList<EquipmentVo>();
        List<EqmLendRecordVo> lendList = new ArrayList<EqmLendRecordVo>();

        for (int i = 0; i < pNos.length; i++) {
            EquipmentVo e = new EquipmentVo();
            e.setEqmStatus("使用中");
            e.setPropertyNo(pNos[i]);
            list.add(e); 
        }
        for (int i = 0; i < ids.length; i++) {
			EqmLendRecordVo vo = new EqmLendRecordVo();
			vo.setHandleStatus("2"); // 2:已归还
			vo.setHandleStatusName("已归还");
			vo.setActualReturnDate(new Date());
			vo.setKeyID(ids[i]);
			lendList.add(vo);
		}
        PrintWriter out = null;
        try {
            out = res.getWriter();
            equipmentService.updateEquipmentStatusByPropertyNo(list);
            eqmLendRecordService.updateLendStatusById(lendList);

            out.write("success");
            logHelper.info("设备归还成功！");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("failed");
            logHelper.error("归还选中设备异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }
    
    /**
     * TODO 同意借出申请.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/passLendHandle.htm")
    public void passLendHandle(HttpServletRequest req, HttpServletResponse res) {
        String idParam = req.getParameter("idParam");

        logHelper.info("开始设备借出处理，处理结果：通过" + ", 处理id：" + idParam);

        String[] ids = idParam.split(",");
        List<EqmLendRecordVo> lendList = new ArrayList<EqmLendRecordVo>();

        for (int i = 0; i < ids.length; i++) {
			EqmLendRecordVo vo = new EqmLendRecordVo();
			vo.setHandleStatus("1"); // 1:已归还
			vo.setHandleStatusName("已借出");
			vo.setKeyID(ids[i]);
			lendList.add(vo);
		}
        PrintWriter out = null;
        try {
            out = res.getWriter();
            eqmLendRecordService.updateLendStatusById(lendList);

            out.write("success");
            logHelper.info("设备借出处理成功！");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("failed");
            logHelper.error("设备借出处理异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }
    
    /**
     * TODO 报修处理.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/repairHandle.htm")
    public void repairHandle(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;
        try {
            out = res.getWriter();
            String keyId = req.getParameter("keyId");
            String handleReason = new String(req.getParameter("handleReason").getBytes("iso8859-1"), "utf-8");
            String propertyNo = new String(req.getParameter("propertyNo").getBytes("iso8859-1"), "utf-8");
            String repairResult = new String(req.getParameter("repairResult").getBytes("iso8859-1"), "utf-8");
            String eqmStatus = ("1".equals(repairResult)) ? "使用中" : "维修中"; //1：维修成功；2维修失败。

            logHelper.info("开始设备报修处理，处理结果：" + ( ("1".equals(repairResult)) ? "维修成功" : "维修失败") + ", 资产号：" + propertyNo);
            
            EqmRepairRecordVo repairVo = new EqmRepairRecordVo();
            repairVo.setKeyID(keyId);
            repairVo.setHandleStatus("1");
            repairVo.setHandleStatusName("已处理");
            repairVo.setHandleReason(handleReason);
            eqmRepairRecordService.updateRepairRecordStatusById(repairVo);
            
            List<EquipmentVo> eqmList = new ArrayList<EquipmentVo>();
            EquipmentVo eqmVo = new EquipmentVo();
            eqmVo.setEqmStatus(eqmStatus);
            eqmVo.setPropertyNo(propertyNo);
            eqmList.add(eqmVo);
            equipmentService.updateEquipmentStatusByPropertyNo(eqmList);

            out.write("success");
            logHelper.info("设备报修处理成功！");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("failed");
            logHelper.error("设备报修处理异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }
    
    /**
     * TODO 拒绝借出申请.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/refuseLendHandle.htm")
    public void refuseLendHandle(HttpServletRequest req, HttpServletResponse res) {
        String idParam = req.getParameter("keyId");
        String pNo = req.getParameter("propertyNo");

        logHelper.info("开始设备借出处理，处理结果：拒绝" + ", 处理id：" + idParam);

        String[] ids = idParam.split(",");
        List<EqmLendRecordVo> lendList = new ArrayList<EqmLendRecordVo>();
        List<EquipmentVo> eqmList = new ArrayList<EquipmentVo>();
        
        PrintWriter out = null;
        try {
            out = res.getWriter();
            String handleReason = new String(req.getParameter("reason").getBytes("iso8859-1"), "utf-8");
            for (int i = 0; i < ids.length; i++) {
    			EqmLendRecordVo vo = new EqmLendRecordVo();
    			vo.setHandleStatus("3"); // 1:已归还
    			vo.setHandleStatusName("已拒绝");
    			vo.setHandleReason(handleReason);
    			vo.setKeyID(ids[i]);
    			lendList.add(vo);
    		}

            EquipmentVo eqmVo = new EquipmentVo();
            eqmVo.setPropertyNo(pNo);
            eqmVo.setEqmStatus("使用中");
            eqmList.add(eqmVo);
            
            equipmentService.updateEquipmentStatusByPropertyNo(eqmList);
            eqmLendRecordService.updateLendStatusById(lendList);

            out.write("success");
            logHelper.info("设备借出处理成功！");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("failed");
            logHelper.error("设备借出处理异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }
    
    /**
     * TODO 分页显示，获取每页数据.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/repairPage.htm")
    public void repairPage(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始报修记录分页查询...");

        String result = "";
        PrintWriter out = null;
        try {
            out = res.getWriter();

            Map<String, Object> paramMap = ParamMapUtil.getRepairQueryParamMap(req);
            Response<Integer> resAllSize = eqmRepairRecordService.getRepairRecordCount(paramMap); // 获取所有数量
            Response<List<EqmLendRecordVo>> response = eqmRepairRecordService.queryRepairRecordByPage(paramMap);
            logHelper.info("报修记录分页总量以及内容查询完毕！");
            if (response != null && response.getContent() != null) {
            	// 拼接发送到前端的json串 
                if (resAllSize.getContent() < 1) {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":[]}";
                } else {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":" + JsonUtils.toJson(response.getContent()) + "}";
                }
			} 
            
            out.write(result);
            logHelper.info("报修记录分页查询发送到前端完毕！");

        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("借报修记录分页异常", e);
        } finally {
            if (out != null) { 
                out.close();
            }
        }
    }
    
    /**
     * TODO 分页显示，获取每页数据.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/scrapPage.htm")
    public void scrapPage(HttpServletRequest req, HttpServletResponse res) {
        logHelper.info("开始报修记录分页查询...");

        String result = "";
        PrintWriter out = null;
        try {
            out = res.getWriter();

            Map<String, Object> paramMap = ParamMapUtil.getLendOutQueryParamMap(req);
            Response<Integer> resAllSize = eqmScrapRecordService.getScrapRecordCount(paramMap); // 获取所有数量
            Response<List<EqmLendRecordVo>> response = eqmScrapRecordService.queryScrapRecordByPage(paramMap);
            logHelper.info("报修记录分页总量以及内容查询完毕！");
            if (response != null && response.getContent() != null) {
            	// 拼接发送到前端的json串 
                if (resAllSize.getContent() < 1) {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":[]}";
                } else {
                    result = "{\"total\":" + resAllSize.getContent() + ",\"rows\":" + JsonUtils.toJson(response.getContent()) + "}";
                }
			} 
            
            out.write(result);
            logHelper.info("报修记录分页查询发送到前端完毕！");

        } catch (Exception e) {
            e.printStackTrace();
            logHelper.error("报修记录分页异常", e);
        } finally {
            if (out != null) { 
                out.close();
            }
        }
    }
    
    /**
     * TODO 报废处理.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/scrapHandle.htm")
    public void scrapHandle(HttpServletRequest req, HttpServletResponse res) { 
        PrintWriter out = null;
        try {
            out = res.getWriter();String keyId = req.getParameter("keyId");
            String handleReason = new String(req.getParameter("handleReason").getBytes("iso8859-1"), "utf-8");
            String propertyNo = new String(req.getParameter("propertyNo").getBytes("iso8859-1"), "utf-8");
            String scrapResult = new String(req.getParameter("scrapResult").getBytes("iso8859-1"), "utf-8");
            String eqmStatus = ("1".equals(scrapResult)) ? "使用中" : "维修中"; //1：维修成功；2维修失败。

            logHelper.info("开始设备报废处理，处理结果：" + ( ("1".equals(scrapResult)) ? "成功" : "失败") + ", 资产号：" + propertyNo);
            
            EqmScrapRecordVo scrapVo = new EqmScrapRecordVo();
            scrapVo.setKeyID(keyId);
            scrapVo.setHandleStatus("1");
            scrapVo.setHandleStatusName("已处理");
            scrapVo.setHandleReason(handleReason);
            eqmScrapRecordService.updateScrapRecordStatusById(scrapVo);
            
            List<EquipmentVo> eqmList = new ArrayList<EquipmentVo>();
            EquipmentVo eqmVo = new EquipmentVo();
            eqmVo.setEqmStatus(eqmStatus);
            eqmVo.setPropertyNo(propertyNo);
            eqmList.add(eqmVo);
            equipmentService.updateEquipmentStatusByPropertyNo(eqmList);

            out.write("success");
            logHelper.info("设备报废处理成功！");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("failed");
            logHelper.error("设备报废处理异常", e);
        } finally {
            if (out != null) {
                // out.flush();
                out.close();
            }
        }
    }
}
