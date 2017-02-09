/*
 * 文件名：IAdministrtorService.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： IAdministrtorService.java
 * 修改人：tianzhong
 * 修改时间：2015年12月31日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.service;

import java.util.List;
import java.util.Map;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.labEqmMS.model.AdministratorVo;

/**
 * TODO AdministrtorService接口.
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
public interface IAdministrtorService     {

    /**
     * TODO 查询管理员列表.
     * 
     * @param paramBo
     *            参数Bo
     * @return AdministratorBo paramBo
     */
    public Response<List<AdministratorVo>> queryAdminList(AdministratorVo paramBo);

    /**
     * TODO 根据管理员名查询管理员.
     * 
     * @param paramBo
     *            参数Bo
     * @return Response<List<AdministratorBo>>
     */
    public Response<List<AdministratorVo>> queryAdminByName(AdministratorVo paramBo);

    /**
     * TODO 根据id查询管理员.
     * 
     * @param paramBo
     *            参数Bo
     * @return AdministratorBo
     */
    public Response<AdministratorVo> queryAdminByAdminId(AdministratorVo paramBo);

    /**
     * TODO 根据管理员姓名删除管理员.
     * 
     * @param paramBo
     *            参数Bo
     * @return 删除管理员的数量
     */
    public Response<Integer> deleteAdminByName(AdministratorVo paramBo);

    /**
     * TODO 删除管理员,根据id .
     * 
     * @param paramBo
     *            参数Bo
     * @return 删除管理员的数量
     */
    public Response<Integer> deleteAdminById(AdministratorVo paramBo);

    /**
     * TODO 更新管理员信息.
     * 
     * @param paramBo
     *            参数Bo
     * @return 更新成功的数量
     */
    public Response<Integer> updateAdmin(AdministratorVo paramBo);

    /**
     * TODO 添加管理员.
     * 
     * @param paramBo
     *            参数Bo
     * @return 插入成功的数量
     */
    public Response<Integer> insertAdmin(AdministratorVo paramBo);

	/**
	 * TODO 获取所有管理员的数量.
	 * 
	 * @param paramMap 参数
	 * @return   Response<Integer>
	 */
	public Response<Integer> getAdminCount(Map<String, Object> paramMap);

	/**
	 * TODO 分页查询管理员.
	 * 
	 * @param paramMap 参数.
	 * @return Response<List<AdministratorVo>>
	 */
	public Response<List<AdministratorVo>> queryAdminByPage(Map<String, Object> paramMap);

	/**
	 * TODO  根据工号删除管理员.
	 * 
	 * @param list
	 * @return
	 */
	public Response<Integer> deleteAdminByAdminIDList(List<AdministratorVo> list);

}
