/*
 * 文件名：IEqmRepairRecordDao.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： IEqmRepairRecordDao.java
 * 修改人：KOBE
 * 修改时间：2016年4月29日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao;

import java.util.List;
import java.util.Map;

import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;
import com.sicnu.liaoshijie.labEqmMS.model.EqmRepairRecordVo;

/**
 * TODO 添加类的一句话简单描述.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * <pre>
 * </pre>
 * 
 * @author     KOBE
 */
public interface IEqmRepairRecordDao {

	/**
	 * TODO 添加报修申请.
	 * 
	 * @param vo
	 * @return
	 */
	int repairApply(EqmRepairRecordVo vo);

	/**
	 * TODO 总量.
	 * 
	 * @param paramMap
	 * @return
	 */
	Integer getRepairRecordCount(Map<String, Object> paramMap);

	/**
	 * TODO 更新记录信息.
	 * 
	 * @param repairVo
	 * @return
	 */
	Integer updateRepairRecordStatusById(EqmRepairRecordVo repairVo);

	/**
	 * TODO 报修记录查询.
	 * 
	 * @param paramMap
	 * @return
	 */
	List<EqmLendRecordVo> queryRepairRecordByPage(Map<String, Object> paramMap);

}
