/*
 * 文件名：IEquipmentDao.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： IEquipmentDao.java
 * 修改人：tianzhong
 * 修改时间：2015年12月30日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao;

import java.util.List;
import java.util.Map;

import com.sicnu.liaoshijie.labEqmMS.model.EquipmentVo;

/**
 * TODO EquipmentDao接口.
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
public interface IEquipmentDao {

    /**
     * TODO 查询设备列表.
     * 
     * @param paramBo
     *            参数Bo
     * @return EquipmentBo paramBo
     */
    public List<EquipmentVo> queryEquipmentList(EquipmentVo paramBo);

    /**
     * TODO 根据设备名查询设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return List<EquipmentBo>
     */
    public List<EquipmentVo> queryEquipmentByName(EquipmentVo paramBo);

    /**
     * TODO 根据id查询设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return EquipmentBo
     */
    public EquipmentVo queryEquimentById(EquipmentVo paramBo);

    /**
     * TODO 根据设备名删除设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return 删除设备的数量
     */
    public int deleteEquipmentByName(EquipmentVo paramBo);

    /**
     * TODO 删除设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return 删除设备的数量
     */
    public int deleteEquipment(EquipmentVo paramBo);

    /**
     * TODO 添更新设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return 更新成功的数量
     */
    public int updateEquipment(EquipmentVo paramBo);

    /**
     * TODO 单个添加设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return 插入成功的数量
     */
    public int insertEquipment(EquipmentVo paramBo);

    /**
     * TODO 批量插入设备.
     * 
     * @param list
     *            参数Bo
     * @return 插入成功的数量
     */
    public int insertEquipmentList(List<EquipmentVo> list);

    /**
     * TODO 分页查询设备.
     * 
     * @param paramMap
     *            参数.
     * @return List
     */
    public List<EquipmentVo> queryEquipmentByPage(Map<String, Object> paramMap);

    /**
     * TODO 重载查询总量方法：参数为Map.
     * 
     * @param paramMap
     *            参数.
     * @return Integer
     */
    public Integer getEquipmentCount(Map<String, Object> paramMap);

    /**
     * TODO 批量删除设备.
     * 
     * @param list
     *            参数list
     * 
     * @return 删除的行数
     */
    public Integer deleteEquipmentBatch(List<EquipmentVo> list);

	/**
	 * TODO 修改设备状态 根据资产号.
	 * 
	 * @param vo
	 * @return
	 */
	public Integer updateEquipmentStatusByPropertyNo(List<EquipmentVo> list);

}
