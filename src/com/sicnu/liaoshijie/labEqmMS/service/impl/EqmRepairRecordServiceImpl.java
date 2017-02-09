/*
 * 文件名：EqmRepairRecordImpl.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EqmRepairRecordImpl.java
 * 修改人：KOBE
 * 修改时间：2016年4月29日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.commons.StatusCodeEnum;
import com.sicnu.liaoshijie.labEqmMS.dao.IEqmRepairRecordDao;
import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;
import com.sicnu.liaoshijie.labEqmMS.model.EqmRepairRecordVo;
import com.sicnu.liaoshijie.labEqmMS.service.IEqmRepairRecordService;

/**
 * TODO 设备维修记录表.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * <pre>
 * </pre>
 * 
 * @author     KOBE
 */
@Component("eqmRepairRecordService")
public class EqmRepairRecordServiceImpl implements IEqmRepairRecordService{
	
	/**
	 * eqmRepairRecordDao
	 */
	@Resource(name="eqmRepairRecordDao")
	private IEqmRepairRecordDao eqmRepairRecordDao;

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Response<Integer> repairApply(EqmRepairRecordVo vo) {
        Response<Integer> response = new Response<Integer>();
        if (null == vo) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = eqmRepairRecordDao.repairApply(vo);
            if (result < 1) {
                response.setCode(StatusCodeEnum.ERROR.getValue());
                return response;
            }
            response.setCode(StatusCodeEnum.SUCCESS.getValue());
            response.setContent(result);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(StatusCodeEnum.ERROR.getValue());
            response.setMessage(e.getMessage());
        }

        return response;
    }

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Response<Integer> getRepairRecordCount(Map<String, Object> paramMap) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramMap) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }

        try {
            Integer result = eqmRepairRecordDao.getRepairRecordCount(paramMap);
            response.setCode(StatusCodeEnum.SUCCESS.getValue());
            response.setContent(result);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(StatusCodeEnum.ERROR.getValue());
            response.setMessage(e.getMessage());
        }

        return response;
    }

	/** 
	 * {@inheritDoc}.
	 */
	@Override 
	public Response<Integer> updateRepairRecordStatusById(EqmRepairRecordVo repairVo) {
        Response<Integer> response = new Response<Integer>();
        try {
        	Integer result = eqmRepairRecordDao.updateRepairRecordStatusById(repairVo);
            response.setCode(StatusCodeEnum.SUCCESS.getValue());
            response.setContent(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Response<List<EqmLendRecordVo>> queryRepairRecordByPage(Map<String, Object> paramMap) {
        Response<List<EqmLendRecordVo>> response = new Response<List<EqmLendRecordVo>>();
        if (null == paramMap) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            List<EqmLendRecordVo> list = eqmRepairRecordDao.queryRepairRecordByPage(paramMap);
            if (null == list) {
                response.setCode(StatusCodeEnum.ERROR.getValue());
                return response;
            }
 
            response.setCode(StatusCodeEnum.SUCCESS.getValue());
            response.setContent(list);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(StatusCodeEnum.ERROR.getValue());
            response.setMessage(e.getMessage());
        }

        return response;
    }

}
