/*
 * 文件名：EqmScrapRecordServiceImpl.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EqmScrapRecordServiceImpl.java
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
import com.sicnu.liaoshijie.labEqmMS.dao.IEqmScrapRecordDao;
import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;
import com.sicnu.liaoshijie.labEqmMS.model.EqmScrapRecordVo;
import com.sicnu.liaoshijie.labEqmMS.service.IEqmScrapRecordService;

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
@Component("eqmScrapRecordService")
public class EqmScrapRecordServiceImpl implements IEqmScrapRecordService{
	
	/**
	 * eqmScrapRecordDao
	 */
	@Resource(name="eqmScrapRecordDao")
	private IEqmScrapRecordDao eqmScrapRecordDao;

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Response<Integer> scrapApply(EqmScrapRecordVo vo) {
        Response<Integer> response = new Response<Integer>();
        if (null == vo) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = eqmScrapRecordDao.scrapApply(vo);
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
	public Response<Integer> getScrapRecordCount(Map<String, Object> paramMap) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramMap) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }

        try {
            Integer result = eqmScrapRecordDao.getScrapRecordCount(paramMap);
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
	public Response<List<EqmLendRecordVo>> queryScrapRecordByPage(Map<String, Object> paramMap) {
        Response<List<EqmLendRecordVo>> response = new Response<List<EqmLendRecordVo>>();
        if (null == paramMap) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            List<EqmLendRecordVo> list = eqmScrapRecordDao.queryScrapRecordByPage(paramMap);
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
	public Response<Integer> updateScrapRecordStatusById(EqmScrapRecordVo scrapVo) {
        Response<Integer> response = new Response<Integer>();
        try {
        	Integer result = eqmScrapRecordDao.updateScrapRecordStatusById(scrapVo);
            response.setCode(StatusCodeEnum.SUCCESS.getValue());
            response.setContent(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
