/*
 * 文件名：MapUtil.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： MapUtil.java
 * 修改人：tianzhong
 * 修改时间：2016年1月19日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.util;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO MapUtil.
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
public class ParamMapUtil {

    /**
     * TODO 获取参数组成的map，用于传参到mybatis的sql语句.
     * 
     * @param req
     *            参数:HttpServletRequest
     * 
     * @return Map<String, Object>
     */
    public static Map<String, Object> getEuipmentQueryParamMap(HttpServletRequest req) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	try {
            Integer page = Integer.parseInt((StringUtil.isNullOrEmpty(req.getParameter("page"))) ? "1" : req.getParameter("page"));
            Integer pageSize = Integer.parseInt((StringUtil.isNullOrEmpty(req.getParameter("rows"))) ? "15" : req.getParameter("rows"));
            
            String eqmName = StringUtil.isNullOrEmpty(req.getParameter("eqmName")) ? null : URLDecoder.decode(req.getParameter("eqmName") , "UTF-8");
            String manager = StringUtil.isNullOrEmpty(req.getParameter("manager")) ? null : URLDecoder.decode(req.getParameter("manager") , "UTF-8");
            String keyID = StringUtil.isNullOrEmpty(req.getParameter("keyID")) ? null : req.getParameter("keyID");
            String propertyNo = StringUtil.isNullOrEmpty(req.getParameter("propertyNo")) ? null : URLDecoder.decode(req.getParameter("propertyNo") , "UTF-8");
            
            String buyStaff = StringUtil.isNullOrEmpty(req.getParameter("buyStaff")) ? null : URLDecoder.decode(req.getParameter("buyStaff") , "UTF-8");
            String college = StringUtil.isNullOrEmpty(req.getParameter("college")) ? null : req.getParameter("college");
            String description = StringUtil.isNullOrEmpty(req.getParameter("description")) ? null : req.getParameter("description");
            String eqmClass = StringUtil.isNullOrEmpty(req.getParameter("eqmClass")) ? null : req.getParameter("eqmClass");
            String eqmFactory = StringUtil.isNullOrEmpty(req.getParameter("eqmFactory")) ? null : req.getParameter("eqmFactory");
            String eqmPrice = StringUtil.isNullOrEmpty(req.getParameter("eqmPrice")) ? null : req.getParameter("eqmPrice");
            String eqmType = StringUtil.isNullOrEmpty(req.getParameter("eqmType")) ? null : req.getParameter("eqmType");
            String eqmLab = StringUtil.isNullOrEmpty(req.getParameter("eqmLab")) ? null : req.getParameter("eqmLab");
            String eqmStatus = StringUtil.isNullOrEmpty(req.getParameter("eqmStatus")) ? null : URLDecoder.decode(req.getParameter("eqmStatus") , "UTF-8");
            String managerId = StringUtil.isNullOrEmpty(req.getParameter("managerId")) ? null : URLDecoder.decode(req.getParameter("managerId") , "UTF-8");

            Date startDate = null;
            Date endDate = null;
            Date buyTime = null;


            startDate = StringUtil.isNullOrEmpty(req.getParameter("startDate")) ? null : DateUtil.stringToDate(req.getParameter("startDate") + " 00:00:00");
            endDate = StringUtil.isNullOrEmpty(req.getParameter("endDate")) ? null : DateUtil.stringToDate(req.getParameter("endDate") + " 00:00:00");
            buyTime = StringUtil.isNullOrEmpty(req.getParameter("buyTime")) ? null : DateUtil.stringToDate(req.getParameter("buyTime") + " 00:00:00");

            map.put("keyID", keyID);
            map.put("buyStaff", buyStaff);
            map.put("buyTime", buyTime);
            map.put("college", college);
            map.put("description", description);
            map.put("eqmClass", eqmClass);
            map.put("eqmFactory", eqmFactory);
            map.put("eqmName", eqmName);
            map.put("eqmPrice", eqmPrice);
            map.put("eqmType", eqmType);
            map.put("manager", manager);
            map.put("eqmLab", eqmLab);
            map.put("eqmStatus", eqmStatus);
            map.put("managerId", managerId); 
            map.put("propertyNo", propertyNo);

            map.put("startDate", startDate);
            map.put("endDate", endDate);

            map.put("pageSize", pageSize);
            map.put("pageCurrent", (page - 1) * pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        return map;
    }
    
    
    /**
     * TODO 获取参数组成的map，用于传参到mybatis的sql语句.
     * 
     * @param req
     *            参数:HttpServletRequest
     * 
     * @return Map<String, Object>
     */
    public static Map<String, Object> getAdminQueryParamMap(HttpServletRequest req) {
        Map<String, Object> map = new HashMap<String, Object>();

        Integer page = Integer.parseInt((StringUtil.isNullOrEmpty(req.getParameter("page"))) ? "1" : req.getParameter("page"));
        Integer pageSize = Integer.parseInt((StringUtil.isNullOrEmpty(req.getParameter("rows"))) ? "15" : req.getParameter("rows"));
        String adminName = StringUtil.isNullOrEmpty(req.getParameter("adminName")) ? null : req.getParameter("adminName");
        String adminId = StringUtil.isNullOrEmpty(req.getParameter("adminID")) ? null : req.getParameter("adminID");

        try { 
            map.put("adminName", adminName);
            map.put("adminID", adminId); 

            map.put("pageSize", pageSize);
            map.put("pageCurrent", (page - 1) * pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
    
    /**
     * TODO 获取参数组成的map，用于传参到mybatis的sql语句.
     * 
     * @param req
     *            参数:HttpServletRequest
     * 
     * @return Map<String, Object>
     */
    public static Map<String, Object> getLendOutQueryParamMap(HttpServletRequest req) {
        Map<String, Object> map = new HashMap<String, Object>();

        try { 
        	Integer page = Integer.parseInt((StringUtil.isNullOrEmpty(req.getParameter("page"))) ? "1" : req.getParameter("page"));
            Integer pageSize = Integer.parseInt((StringUtil.isNullOrEmpty(req.getParameter("rows"))) ? "15" : req.getParameter("rows"));
            String eqmName = StringUtil.isNullOrEmpty(req.getParameter("eqmName")) ? null : req.getParameter("eqmName");
            String lenderId = StringUtil.isNullOrEmpty(req.getParameter("lenderId")) ? null : req.getParameter("lenderId");
            String propertyNo = StringUtil.isNullOrEmpty(req.getParameter("propertyNo")) ? null : req.getParameter("propertyNo");
            String handleStatus = StringUtil.isNullOrEmpty(req.getParameter("handleStatus")) ? null : req.getParameter("handleStatus");
            String adminId = StringUtil.isNullOrEmpty(req.getParameter("adminId")) ? null : req.getParameter("adminId");
            String lenderName = StringUtil.isNullOrEmpty(req.getParameter("lenderName")) ? null : req.getParameter("lenderName");
            
            Date lendDate = StringUtil.isNullOrEmpty(req.getParameter("lendDate")) ? null : DateUtil.stringToDate(req.getParameter("lendDate") + " 00:00:00");
            Date startDate = StringUtil.isNullOrEmpty(req.getParameter("planReturnDate")) ? null : DateUtil.stringToDate(req.getParameter("planReturnDate") + " 00:00:00");
            
            map.put("eqmName", eqmName);
            map.put("propertyNo", propertyNo);
            map.put("handleStatus", handleStatus); 
            map.put("lendDate", lendDate);
            map.put("startDate", startDate);
            map.put("lenderId", lenderId);
            map.put("adminId", adminId);
            map.put("lenderName", lenderName);

            map.put("pageSize", pageSize);
            map.put("pageCurrent", (page - 1) * pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
    
    /**
     * TODO 获取参数组成的map，用于传参到mybatis的sql语句.
     * 
     * @param req
     *            参数:HttpServletRequest
     * 
     * @return Map<String, Object>
     */
    public static Map<String, Object> getRepairQueryParamMap(HttpServletRequest req) {
        Map<String, Object> map = new HashMap<String, Object>();

        try { 
        	Integer page = Integer.parseInt((StringUtil.isNullOrEmpty(req.getParameter("page"))) ? "1" : req.getParameter("page"));
            Integer pageSize = Integer.parseInt((StringUtil.isNullOrEmpty(req.getParameter("rows"))) ? "15" : req.getParameter("rows"));
            String eqmName = StringUtil.isNullOrEmpty(req.getParameter("eqmName")) ? null : req.getParameter("eqmName");
            String applyId = StringUtil.isNullOrEmpty(req.getParameter("applyId")) ? null : req.getParameter("applyId");
            String propertyNo = StringUtil.isNullOrEmpty(req.getParameter("propertyNo")) ? null : req.getParameter("propertyNo");
            String handleStatus = StringUtil.isNullOrEmpty(req.getParameter("handleStatus")) ? null : req.getParameter("handleStatus");
            String adminId = StringUtil.isNullOrEmpty(req.getParameter("adminId")) ? null : req.getParameter("adminId");
            String applyName = StringUtil.isNullOrEmpty(req.getParameter("applyName")) ? null : req.getParameter("applyName");
            Date applyDate = StringUtil.isNullOrEmpty(req.getParameter("applyDate")) ? null : DateUtil.stringToDate(req.getParameter("applyDate") + " 00:00:00");
            
            map.put("eqmName", eqmName);
            map.put("propertyNo", propertyNo);
            map.put("handleStatus", handleStatus); 
            map.put("applyDate", applyDate);
            map.put("applyId", applyId);
            map.put("adminId", adminId);
            map.put("applyName", applyName);

            map.put("pageSize", pageSize);
            map.put("pageCurrent", (page - 1) * pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
    
    /**
     * TODO 获取参数组成的map，用于传参到mybatis的sql语句.
     * 
     * @param req
     *            参数:HttpServletRequest
     * 
     * @return Map<String, Object>
     */
    public static Map<String, Object> getScrapQueryParamMap(HttpServletRequest req) {
        Map<String, Object> map = new HashMap<String, Object>();

        try { 
        	Integer page = Integer.parseInt((StringUtil.isNullOrEmpty(req.getParameter("page"))) ? "1" : req.getParameter("page"));
            Integer pageSize = Integer.parseInt((StringUtil.isNullOrEmpty(req.getParameter("rows"))) ? "15" : req.getParameter("rows"));
            String eqmName = StringUtil.isNullOrEmpty(req.getParameter("eqmName")) ? null : req.getParameter("eqmName");
            String applyId = StringUtil.isNullOrEmpty(req.getParameter("applyId")) ? null : req.getParameter("applyId");
            String propertyNo = StringUtil.isNullOrEmpty(req.getParameter("propertyNo")) ? null : req.getParameter("propertyNo");
            String handleStatus = StringUtil.isNullOrEmpty(req.getParameter("handleStatus")) ? null : req.getParameter("handleStatus");
            String adminId = StringUtil.isNullOrEmpty(req.getParameter("adminId")) ? null : req.getParameter("adminId");
            String applyName = StringUtil.isNullOrEmpty(req.getParameter("applyName")) ? null : req.getParameter("applyName");
            Date applyDate = StringUtil.isNullOrEmpty(req.getParameter("applyDate")) ? null : DateUtil.stringToDate(req.getParameter("applyDate") + " 00:00:00");
            
            map.put("eqmName", eqmName);
            map.put("propertyNo", propertyNo);
            map.put("handleStatus", handleStatus); 
            map.put("applyDate", applyDate);
            map.put("applyId", applyId);
            map.put("adminId", adminId);
            map.put("applyName", applyName);

            map.put("pageSize", pageSize);
            map.put("pageCurrent", (page - 1) * pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
