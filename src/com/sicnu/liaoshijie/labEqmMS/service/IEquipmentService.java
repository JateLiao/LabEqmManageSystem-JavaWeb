/*
 * 文件名：IQuipmentService.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： IQuipmentService.java
 * 修改人：tianzhong
 * 修改时间：2015年12月30日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.service;

import java.util.List;
import java.util.Map;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.labEqmMS.model.EquipmentVo;

/**
 * TODO 设备服务接口.
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
public interface IEquipmentService {

    /**
     * TODO 查询设备列表.
     * 
     * @param paramBo
     *            参数Bo
     * @return EquipmentBo paramBo
     */
    public Response<List<EquipmentVo>> queryEquipmentList(EquipmentVo paramBo);

    /**
     * TODO 根据设备名查询设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return List<EquipmentBo>
     */
    public Response<List<EquipmentVo>> queryEquipmentByName(EquipmentVo paramBo);

    /**
     * TODO 根据id查询设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return EquipmentBo
     */
    public Response<EquipmentVo> queryEquimentById(EquipmentVo paramBo);

    /**
     * TODO 根据设备名删除设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return 删除设备的数量
     */
    public Response<Integer> deleteEquipmentByName(EquipmentVo paramBo);

    /**
     * TODO 删除设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return 删除设备的数量
     */
    public Response<Integer> deleteEquipmentById(EquipmentVo paramBo);

    /**
     * TODO 添更新设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return 更新成功的数量
     */
    public Response<Integer> updateEquipment(EquipmentVo paramBo);

    /**
     * TODO 单个添加设备.
     * 
     * @param paramBo
     *            参数Bo
     * @return 插入成功的数量
     */
    public Response<Integer> insertEquipment(EquipmentVo paramBo);

    /**
     * TODO 批量插入设备.
     * 
     * @param list
     *            参数Bo
     * @return 插入成功的数量
     */
    public Response<Integer> insertEquipmentList(List<EquipmentVo> list);

    /**
     * TODO 分页查询设备.
     * 
     * @param paramMap
     *            参数.
     * @return Response<List<EquipmentVo>>
     */
    public Response<List<EquipmentVo>> queryEuipmentByPage(Map<String, Object> paramMap);

    /**
     * TODO 重载查询总量方法：参数为Map.
     * 
     * @param paramMap
     *            参数.
     * @return Response<Integer>
     */
    public Response<Integer> getEquipmentCount(Map<String, Object> paramMap);

    /**
     * TODO 批量删除设备.
     * 
     * @param list
     *            参数list
     * @return Response<Integer>
     */
    public Response<Integer> deleteEquipmentBatch(List<EquipmentVo> list);
    
    
    /**
     * TODO 修改设备状态 根据资产号.
     * 
     * @param vo
     * @return
     */
    public Response<Integer> updateEquipmentStatusByPropertyNo(List<EquipmentVo> list);
}
