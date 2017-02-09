/*
 * 文件名：EquipmentServiceImpl.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EquipmentServiceImpl.java
 * 修改人：tianzhong
 * 修改时间：2015年12月30日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.commons.StatusCodeEnum;
import com.sicnu.liaoshijie.labEqmMS.dao.IEquipmentDao;
import com.sicnu.liaoshijie.labEqmMS.model.EquipmentVo;
import com.sicnu.liaoshijie.labEqmMS.service.IEquipmentService;
import com.sicnu.liaoshijie.labEqmMS.util.StringUtil;

/**
 * TODO 设备服务实现类.
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
@Component("equipmentService")
public class EquipmentServiceImpl implements IEquipmentService {

    /**
     * 添加字段注释.
     */
    @Resource(name = "equipmentDao")
    private IEquipmentDao equipmentDao;

    /**
     * {@inheritDoc}.
     */
    @Override
    public Response<List<EquipmentVo>> queryEquipmentList(EquipmentVo paramBo) {
        Response<List<EquipmentVo>> response = new Response<List<EquipmentVo>>();
        if (null == paramBo) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }

        try {
            List<EquipmentVo> list = equipmentDao.queryEquipmentList(paramBo);
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
    public Response<List<EquipmentVo>> queryEquipmentByName(EquipmentVo paramBo) {
        Response<List<EquipmentVo>> response = new Response<List<EquipmentVo>>();
        if (null == paramBo || StringUtil.isNullOrEmpty(paramBo.getEqmName())) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }

        try {
            List<EquipmentVo> list = equipmentDao.queryEquipmentByName(paramBo);
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
    public Response<EquipmentVo> queryEquimentById(EquipmentVo paramBo) {
        Response<EquipmentVo> response = new Response<EquipmentVo>();
        if (null == paramBo || paramBo.getKeyID() != 0) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }

        try {
            EquipmentVo reslutBo = equipmentDao.queryEquimentById(paramBo);
            if (null == reslutBo) {
                response.setCode(StatusCodeEnum.ERROR.getValue());
                return response;
            }
            response.setCode(StatusCodeEnum.SUCCESS.getValue());
            response.setContent(reslutBo);
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
    public Response<Integer> deleteEquipmentByName(EquipmentVo paramBo) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramBo || StringUtil.isNullOrEmpty(paramBo.getEqmName())) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = equipmentDao.deleteEquipmentByName(paramBo);
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
    public Response<Integer> deleteEquipmentById(EquipmentVo paramBo) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramBo || paramBo.getKeyID() != 0) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = equipmentDao.deleteEquipment(paramBo);
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
    public Response<Integer> updateEquipment(EquipmentVo paramBo) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramBo) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = equipmentDao.updateEquipment(paramBo);
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
    public Response<Integer> insertEquipment(EquipmentVo paramBo) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramBo) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = equipmentDao.insertEquipment(paramBo);
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
    public Response<Integer> insertEquipmentList(List<EquipmentVo> list) {
        Response<Integer> response = new Response<Integer>();
        if (null == list) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = equipmentDao.insertEquipmentList(list);
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
     * {@inheritDoc}分页查询设备.
     */
    @Override
    public Response<List<EquipmentVo>> queryEuipmentByPage(Map<String, Object> paramMap) {
        Response<List<EquipmentVo>> response = new Response<List<EquipmentVo>>();
        if (null == paramMap) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            List<EquipmentVo> list = equipmentDao.queryEquipmentByPage(paramMap);
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
    public Response<Integer> getEquipmentCount(Map<String, Object> paramMap) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramMap) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }

        try {
            Integer result = equipmentDao.getEquipmentCount(paramMap);
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
    public Response<Integer> deleteEquipmentBatch(List<EquipmentVo> list) {
        Response<Integer> response = new Response<Integer>();
        if (null == list) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }

        try {
            Integer result = equipmentDao.deleteEquipmentBatch(list);
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
	public Response<Integer> updateEquipmentStatusByPropertyNo(List<EquipmentVo> list) {
        Response<Integer> response = new Response<Integer>();
        if (null == list) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }

        try {
            Integer result = equipmentDao.updateEquipmentStatusByPropertyNo(list);
            response.setCode(StatusCodeEnum.SUCCESS.getValue());
            response.setContent(result);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(StatusCodeEnum.ERROR.getValue());
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
