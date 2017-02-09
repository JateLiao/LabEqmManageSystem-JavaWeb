/*
 * 文件名：AdministrtorDaoImpl.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： AdministrtorDaoImpl.java
 * 修改人：tianzhong
 * 修改时间：2015年12月31日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sicnu.liaoshijie.labEqmMS.dao.IAdministrtorDao;
import com.sicnu.liaoshijie.labEqmMS.model.AdministratorVo;

/**
 * TODO AdministrtorDao实现类.
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
@Component("administrtorDao")
public class AdministrtorDaoImpl extends BaseDaoImpl implements IAdministrtorDao {

	/**
	 * {@inheritDoc}查询管理员列表.
	 */
	@Override
	public List<AdministratorVo> queryAdminList(AdministratorVo paramBo) {
		return sqlSession.selectList("admin.select", paramBo);
	}

	/**
	 * {@inheritDoc}根据管理员名查询管理员.
	 */
	@Override
	public List<AdministratorVo> queryAdminByName(AdministratorVo paramBo) {
		return sqlSession.selectList("admin.select_name", paramBo);
	}

	/**
	 * {@inheritDoc}根据id查询管理员.
	 */
	@Override
	public AdministratorVo queryAdminById(AdministratorVo paramBo) {
		return sqlSession.selectOne("admin.select_adminID", paramBo);
	}

	/**
	 * {@inheritDoc}根据管理员姓名删除管理员.
	 */
	@Override
	public int deleteAdminByName(AdministratorVo paramBo) {
		return sqlSession.delete("admin.delete_name", paramBo);
	}

	/**
	 * {@inheritDoc}更新管理员信息.
	 */
	@Override
	public int updateAdmin(AdministratorVo paramBo) {
		return sqlSession.update("admin.update", paramBo);
	}

	/**
	 * {@inheritDoc}添加管理员.
	 */
	@Override
	public int insertAdmin(AdministratorVo paramBo) {
		return sqlSession.insert("admin.insert", paramBo);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public int deleteAdminById(AdministratorVo paramBo) {
		return sqlSession.delete("admin.delete_id", paramBo);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Integer getAdminCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("admin.count_map", paramMap);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public List<AdministratorVo> queryAdminByPage(Map<String, Object> paramMap) {
		return sqlSession.selectList("admin.select_page_map", paramMap);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public int deleteAdminByAdminIDList(List<AdministratorVo> list) {
		return sqlSession.delete("admin.delete_batch", list);
	}

}
