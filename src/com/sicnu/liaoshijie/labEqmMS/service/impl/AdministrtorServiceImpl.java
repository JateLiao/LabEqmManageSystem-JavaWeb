/*
 * 文件名：AdministrtorServiceImpl.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： AdministrtorServiceImpl.java
 * 修改人：tianzhong
 * 修改时间：2015年12月31日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.commons.StatusCodeEnum;
import com.sicnu.liaoshijie.labEqmMS.dao.IAdministrtorDao;
import com.sicnu.liaoshijie.labEqmMS.model.AdministratorVo;
import com.sicnu.liaoshijie.labEqmMS.service.IAdministrtorService;
import com.sicnu.liaoshijie.labEqmMS.util.StringUtil;

/**
 * TODO AdministrtorService实现类.
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
@Component("administrtorService")
public class AdministrtorServiceImpl implements IAdministrtorService {

    /**
     * administrtorDao.
     */
    @Resource(name = "administrtorDao")
    private IAdministrtorDao administrtorDao;

    /**
     * {@inheritDoc}查询管理员列表.
     */
    @Override
    public Response<List<AdministratorVo>> queryAdminList(AdministratorVo paramBo) {
        Response<List<AdministratorVo>> response = new Response<List<AdministratorVo>>();
        if (null == paramBo) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            List<AdministratorVo> list = administrtorDao.queryAdminList(paramBo);
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
     * {@inheritDoc}根据管理员名查询管理员.
     */
    @Override
    public Response<List<AdministratorVo>> queryAdminByName(AdministratorVo paramBo) {
        Response<List<AdministratorVo>> response = new Response<List<AdministratorVo>>();
        if (null == paramBo || StringUtil.isNullOrEmpty(paramBo.getAdminName().trim())) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            List<AdministratorVo> list = administrtorDao.queryAdminList(paramBo);
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
     * {@inheritDoc}根据id查询管理员.
     */
    @Override
    public Response<AdministratorVo> queryAdminByAdminId(AdministratorVo paramBo) {
        Response<AdministratorVo> response = new Response<AdministratorVo>();
        if (null == paramBo || StringUtil.isNullOrEmpty(paramBo.getAdminID().trim())) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            AdministratorVo bo = administrtorDao.queryAdminById(paramBo);
            if (null == bo) {
                response.setCode(StatusCodeEnum.ERROR.getValue());
                return response;
            }
            response.setCode(StatusCodeEnum.SUCCESS.getValue());
            response.setContent(bo);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(StatusCodeEnum.ERROR.getValue());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    /**
     * {@inheritDoc}根据管理员姓名删除管理员.
     */
    @Override
    public Response<Integer> deleteAdminByName(AdministratorVo paramBo) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramBo || StringUtil.isNullOrEmpty(paramBo.getAdminName())) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = administrtorDao.deleteAdminByName(paramBo);
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
     * {@inheritDoc}删除管理员,根据id .
     */
    @Override
    public Response<Integer> deleteAdminById(AdministratorVo paramBo) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramBo || StringUtil.isNullOrEmpty(paramBo.getAdminID())) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = administrtorDao.deleteAdminByName(paramBo);
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
     * {@inheritDoc}更新管理员信息.
     */
    @Override
    public Response<Integer> updateAdmin(AdministratorVo paramBo) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramBo) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = administrtorDao.updateAdmin(paramBo);
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
     * {@inheritDoc}添加管理员.
     */
    @Override
    public Response<Integer> insertAdmin(AdministratorVo paramBo) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramBo) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = administrtorDao.insertAdmin(paramBo);
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
	public Response<Integer> getAdminCount(Map<String, Object> paramMap) {
        Response<Integer> response = new Response<Integer>();
        if (null == paramMap) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }

        try {
            Integer result = administrtorDao.getAdminCount(paramMap);
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
	public Response<List<AdministratorVo>> queryAdminByPage(Map<String, Object> paramMap) {
        Response<List<AdministratorVo>> response = new Response<List<AdministratorVo>>();
        if (null == paramMap) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            List<AdministratorVo> list = administrtorDao.queryAdminByPage(paramMap);
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
	public Response<Integer> deleteAdminByAdminIDList(List<AdministratorVo> list) {
        Response<Integer> response = new Response<Integer>();
        if (null == list) {
            response.setCode(StatusCodeEnum.PARAMETER_IS_NULL.getValue());
            response.setMessage("参数为空");
            return response;
        }
        try {
            int result = administrtorDao.deleteAdminByAdminIDList(list);
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

}
