/*
 * 文件名：EqmLendRecordDaoImpl.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EqmLendRecordDaoImpl.java
 * 修改人：KOBE
 * 修改时间：2016年4月24日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sicnu.liaoshijie.labEqmMS.dao.IEqmLendRecordDao;
import com.sicnu.liaoshijie.labEqmMS.model.EqmLendRecordVo;

/**
 * TODO EqmLendRecordDaoImpl.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * 
 * <pre>
 * </pre>
 * 
 * @author KOBE
 */
@Component("eqmLendRecordDao")
public class EqmLendRecordDaoImpl extends BaseDaoImpl implements IEqmLendRecordDao {

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public int insertEqmLendOutApply(EqmLendRecordVo vo) {
		return sqlSession.insert("lendRecord.insert", vo);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Integer getLendRecordCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("lendRecord.query_count", paramMap);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public List<EqmLendRecordVo> queryLendRecordByPage(Map<String, Object> paramMap) {
		return sqlSession.selectList("lendRecord.select_page_map", paramMap);
	}

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Integer updateLendStatusById(List<EqmLendRecordVo> list) {
		return sqlSession.update("lendRecord.updateLendStatusById", list);
	}

}
