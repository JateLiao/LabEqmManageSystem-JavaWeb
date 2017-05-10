/*
 * 文件名：IAdministrtorDao.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： IAdministrtorDao.java
 * 修改人：tianzhong
 * 修改时间：2015年12月31日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao;

import java.util.List;
import java.util.Map;

import com.sicnu.liaoshijie.labEqmMS.model.AdministratorVo;

/**
 * TODO AdministrtorDao接口.
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
public interface IAdministrtorDao {

    /**
     * TODO 查询管理员列表.
     * 
     * @param paramBo
     *            参数Bo
     * @return AdministratorBo paramBo
     */
    public List<AdministratorVo> queryAdminList(AdministratorVo paramBo);

    /**
     * TODO 根据管理员名查询管理员.
     * 
     * @param paramBo
     *            参数Bo
     * @return List<AdministratorBo>
     */
    public List<AdministratorVo> queryAdminByName(AdministratorVo paramBo);

    /**
     * TODO 根据id查询管理员.
     * 
     * @param paramBo
     *            参数Bo
     * @return AdministratorBo
     */
    public AdministratorVo queryAdminById(AdministratorVo paramBo);

    /**
     * TODO 根据管理员姓名删除管理员.
     * 
     * @param paramBo
     *            参数Bo
     * @return 删除管理员的数量
     */
    public int deleteAdminByName(AdministratorVo paramBo);

    /**
     * TODO 删除管理员,根据id .
     * 
     * @param paramBo
     *            参数Bo
     * @return 删除管理员的数量
     */
    public int deleteAdminById(AdministratorVo paramBo);

    /**
     * TODO 更新管理员信息.
     * 
     * @param paramBo
     *            参数Bo
     * @return 更新成功的数量
     */
    public int updateAdmin(AdministratorVo paramBo);

    /**
     * TODO 添加管理员.
     * 
     * @param paramBo
     *            参数Bo
     * @return 插入成功的数量
     */
    public int insertAdmin(AdministratorVo paramBo);

	/**
	 * TODO 查询所有管理员的数量.
	 * 
	 * @param paramMap 参数.
	 * @return Integer
	 */
	public Integer getAdminCount(Map<String, Object> paramMap);

	/**
	 * TODO 分页查询管理员.
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<AdministratorVo> queryAdminByPage(Map<String, Object> paramMap);

	/**
	 * TODO 根据工号批量删除管理员.
	 * 
	 * @param list
	 * @return
	 */
	public int deleteAdminByAdminIDList(List<AdministratorVo> list);

}
