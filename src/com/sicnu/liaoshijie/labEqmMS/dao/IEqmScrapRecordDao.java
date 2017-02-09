/*
 * 文件名：IEqmScrapRecordDao.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： IEqmScrapRecordDao.java
 * 修改人：KOBE
 * 修改时间：2016年4月29日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao;

import java.util.List;
import java.util.Map;

import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;
import com.sicnu.liaoshijie.labEqmMS.model.EqmScrapRecordVo;

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
public interface IEqmScrapRecordDao {

	/**
	 * TODO 报废申请.
	 * 
	 * @param vo
	 * @return
	 */
	int scrapApply(EqmScrapRecordVo vo);

	/**
	 * TODO 总量.
	 * 
	 * @param paramMap
	 * @return
	 */
	Integer getScrapRecordCount(Map<String, Object> paramMap);

	/**
	 * TODO 报修查询.
	 * 
	 * @param paramMap
	 * @return
	 */
	List<EqmLendRecordVo> queryScrapRecordByPage(Map<String, Object> paramMap);

	/**
	 * TODO 更新处理状态.
	 * 
	 * @param scrapVo
	 * @return
	 */
	Integer updateScrapRecordStatusById(EqmScrapRecordVo scrapVo);

}
