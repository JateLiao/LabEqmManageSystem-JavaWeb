/*
 * 文件名：IEqmLendRecordService.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： IEqmLendRecordService.java
 * 修改人：KOBE
 * 修改时间：2016年4月24日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.service;

import java.util.List;
import java.util.Map;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;

/**
 * TODO IEqmLendRecordService.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * <pre>
 * </pre>
 * 
 * @author     KOBE
 */
public interface IEqmLendRecordService {

	/**
	 * TODO 添加借出申请.
	 * 
	 * @param vo
	 * @return
	 */
	Response<Integer> addLendOutApply(EqmLendRecordVo vo);

	/**
	 * TODO 查询借出记录总数量.
	 * 
	 * @param paramMap
	 * @return
	 */
	Response<Integer> getLendRecordCount(Map<String, Object> paramMap);

	/**
	 * TODO 分页查询记录.
	 * 
	 * @param paramMap
	 * @return
	 */
	Response<List<EqmLendRecordVo>> queryLendRecordByPage(Map<String, Object> paramMap);

	/**
	 * TODO 根据id更新设备借出状态.
	 * 
	 * @param vo
	 * @return
	 */
	Response<Integer> updateLendStatusById(List<EqmLendRecordVo> list);

}
