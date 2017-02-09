/*
 * 文件名：IEqmScrapRecordService.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： IEqmScrapRecordService.java
 * 修改人：KOBE
 * 修改时间：2016年4月29日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.service;

import java.util.List;
import java.util.Map;

import com.sicnu.liaoshijie.commons.Response;
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
public interface IEqmScrapRecordService {

	/**
	 * TODO 报废申请.
	 * 
	 * @param vo
	 * @return
	 */
	Response<Integer> scrapApply(EqmScrapRecordVo vo);

	/**
	 * TODO 报修查询.
	 * 
	 * @param paramMap
	 * @return
	 */
	Response<Integer> getScrapRecordCount(Map<String, Object> paramMap);

	/**
	 * TODO 报修查询.
	 * 
	 * @param paramMap
	 * @return
	 */
	Response<List<EqmLendRecordVo>> queryScrapRecordByPage(Map<String, Object> paramMap);

	/**
	 * TODO 更新处理状态.
	 * 
	 * @param scrapVo
	 */
	Response<Integer> updateScrapRecordStatusById(EqmScrapRecordVo scrapVo);


}
