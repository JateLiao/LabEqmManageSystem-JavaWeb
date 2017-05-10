/*
 * 文件名：IEqmRepairRecordService.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： IEqmRepairRecordService.java
 * 修改人：KOBE
 * 修改时间：2016年4月29日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.service;

import java.util.List;
import java.util.Map;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;
import com.sicnu.liaoshijie.labEqmMS.model.EqmRepairRecordVo;

/**
 * TODO 设备维修记录表.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * <pre>
 * </pre>
 * 
 * @author     KOBE
 */
public interface IEqmRepairRecordService {

	/**
	 * TODO 添加报修申请.
	 * 
	 * @param vo
	 * @return
	 */
	Response<Integer> repairApply(EqmRepairRecordVo vo);

	/**
	 * TODO 获取所有数量.
	 * 
	 * @param paramMap
	 * @return
	 */
	Response<Integer> getRepairRecordCount(Map<String, Object> paramMap);

	/**
	 * TODO 更新记录信息.
	 * 
	 * @param repairVo
	 */
	Response<Integer> updateRepairRecordStatusById(EqmRepairRecordVo repairVo);

	/**
	 * TODO 报修记录查询.
	 * 
	 * @param paramMap
	 * @return
	 */
	Response<List<EqmLendRecordVo>> queryRepairRecordByPage(Map<String, Object> paramMap);

}
