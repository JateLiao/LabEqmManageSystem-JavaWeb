/*
 * 文件名：EqmScrapRecordDaoImpl.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EqmScrapRecordDaoImpl.java
 * 修改人：KOBE
 * 修改时间：2016年4月29日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sicnu.liaoshijie.labEqmMS.dao.IEqmScrapRecordDao;
import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;
import com.sicnu.liaoshijie.labEqmMS.model.EqmScrapRecordVo;

/**
 * TODO 报废记录dao.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * <pre>
 * </pre>
 * 
 * @author     KOBE
 */
@Component("eqmScrapRecordDao")
public class EqmScrapRecordDaoImpl extends BaseDaoImpl implements IEqmScrapRecordDao{

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public int scrapApply(EqmScrapRecordVo vo) {
		return sqlSession.insert("scrapRecord.insert", vo);
	}

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Integer getScrapRecordCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("scrapRecord.query_count", paramMap);
	}

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public List<EqmLendRecordVo> queryScrapRecordByPage(Map<String, Object> paramMap) {
		return sqlSession.selectList("scrapRecord.select_page_map", paramMap);
	}

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Integer updateScrapRecordStatusById(EqmScrapRecordVo scrapVo) {
		return sqlSession.update("scrapRecord.update_status_id", scrapVo);
	}

}
