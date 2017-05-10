/*
 * 文件名：EqmLendRecordServiceImpl.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EqmLendRecordServiceImpl.java
 * 修改人：KOBE
 * 修改时间：2016年4月24日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.commons.StatusCodeEnum;
import com.sicnu.liaoshijie.labEqmMS.dao.IEqmLendRecordDao;
import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;
import com.sicnu.liaoshijie.labEqmMS.service.IEqmLendRecordService;

/**
 * TODO 添加类的一句话简单描述.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * <pre>
 * </pre>
 * 
 * @author     KOBE
 */
@Component("eqmLendRecordService")
public class EqmLendRecordServiceImpl implements IEqmLendRecordService{
	
	/**
	 * 添加字段注释.
	 */
	@Resource(name = "eqmLendRecordDao")
	private IEqmLendRecordDao eqmLendRecordDao;
	
	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Response<Integer> addLendOutApply(EqmLendRecordVo vo) {
        Response<Integer> response = new Response<Integer>();
        if (null == vo) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = eqmLendRecordDao.insertEqmLendOutApply(vo);
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
	public Response<Integer> getLendRecordCount(Map<String, Object> paramMap) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramMap) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }

        try {
            Integer result = eqmLendRecordDao.getLendRecordCount(paramMap);
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
	public Response<List<EqmLendRecordVo>> queryLendRecordByPage(Map<String, Object> paramMap) {
        Response<List<EqmLendRecordVo>> response = new Response<List<EqmLendRecordVo>>();
        if (null == paramMap) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            List<EqmLendRecordVo> list = eqmLendRecordDao.queryLendRecordByPage(paramMap);
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

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Response<Integer> updateLendStatusById(List<EqmLendRecordVo> list) {
        Response<Integer> response = new Response<Integer>();
        try {
        	Integer result = eqmLendRecordDao.updateLendStatusById(list);
            response.setCode(StatusCodeEnum.SUCCESS.getValue());
            response.setContent(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
