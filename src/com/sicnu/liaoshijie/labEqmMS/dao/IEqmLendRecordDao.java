/*
 * 文件名：IEqmLendRecordDao.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： IEqmLendRecordDao.java
 * 修改人：KOBE
 * 修改时间：2016年4月24日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao;

import java.util.List;
import java.util.Map;

import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;

/**
 * TODO IEqmLendRecordDao.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * <pre>
 * </pre>
 * 
 * @author     KOBE
 */
public interface IEqmLendRecordDao {

	/**
	 * TODO 插入添加设备借出.
	 * 
	 * @param vo .
	 * @return int
	 */
	int insertEqmLendOutApply(EqmLendRecordVo vo);

	/**
	 * TODO 查询借出记录总数量.
	 * 
	 * @param paramMap
	 * @return
	 */
	Integer getLendRecordCount(Map<String, Object> paramMap);

	/**
	 * TODO 分页查询记录.
	 * 
	 * @param paramMap
	 * @return
	 */
	List<EqmLendRecordVo> queryLendRecordByPage(Map<String, Object> paramMap);

	/**
	 * TODO 更新设备借出处理状态.
	 * 
	 * @param vo
	 * @return
	 */
	Integer updateLendStatusById(List<EqmLendRecordVo> list); 

}
