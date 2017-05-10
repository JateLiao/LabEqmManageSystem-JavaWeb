/*
 * 文件名：EquipmentDaoImpl.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EquipmentDaoImpl.java
 * 修改人：tianzhong
 * 修改时间：2015年12月30日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sicnu.liaoshijie.labEqmMS.dao.IEquipmentDao;
import com.sicnu.liaoshijie.labEqmMS.model.EquipmentVo;

/**
 * TODO EquipmentDao实现类.
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
@Component("equipmentDao")
public class EquipmentDaoImpl extends BaseDaoImpl implements IEquipmentDao {

    /**
     * {@inheritDoc} 查询设备列表.
     */
    @Override
    public List<EquipmentVo> queryEquipmentList(EquipmentVo paramBo) {
        return sqlSession.selectList("eqm.select", paramBo);
    }

    /**
     * {@inheritDoc} 根据设备名查询设备.
     */
    @Override
    public List<EquipmentVo> queryEquipmentByName(EquipmentVo paramBo) {
        return sqlSession.selectList("eqm.select_name", paramBo);
    }

    /**
     * {@inheritDoc} 根据id查询设备.
     */
    @Override
    public EquipmentVo queryEquimentById(EquipmentVo paramBo) {
        return sqlSession.selectOne("eqm.select_id", paramBo);
    }

    /**
     * {@inheritDoc} 根据设备名删除设备.
     */
    @Override
    public int deleteEquipmentByName(EquipmentVo paramBo) {
        return sqlSession.delete("eqm.delete_name", paramBo);
    }

    /**
     * {@inheritDoc} 删除设备.
     */
    @Override
    public int deleteEquipment(EquipmentVo paramBo) {
        return sqlSession.delete("eqm.delete", paramBo);
    }

    /**
     * {@inheritDoc} 添更新设备.
     */
    @Override
    public int updateEquipment(EquipmentVo paramBo) {
        return sqlSession.update("eqm.update", paramBo);
    }

    /**
     * {@inheritDoc} 单个添加设备.
     */
    @Override
    public int insertEquipment(EquipmentVo paramBo) {
        return sqlSession.insert("eqm.insert", paramBo);
    }

    /**
     * {@inheritDoc} 批量插入设备.
     */
    @Override
    public int insertEquipmentList(List<EquipmentVo> list) {
        return sqlSession.insert("eqm.insert_batch", list);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public List<EquipmentVo> queryEquipmentByPage(Map<String, Object> paramMap) {
        return sqlSession.selectList("eqm.select_page_map", paramMap);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Integer getEquipmentCount(Map<String, Object> paramMap) {
        return sqlSession.selectOne("eqm.count_map", paramMap);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Integer deleteEquipmentBatch(List<EquipmentVo> list) {
        return sqlSession.delete("eqm.delete_batch", list);
    }

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Integer updateEquipmentStatusByPropertyNo(List<EquipmentVo> list) {
        return sqlSession.update("eqm.update_status_propertyNo", list);
    }
}
