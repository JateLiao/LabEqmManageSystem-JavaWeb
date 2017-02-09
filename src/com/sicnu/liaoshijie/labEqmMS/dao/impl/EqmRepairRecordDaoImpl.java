/*
 * 文件名：EqmRepairRecordDaoImpl.java
 * 版权：Copyright 2012-2016 KOBE Tech. Co. Ltd. All Rights Reserved. 
 * 描述： EqmRepairRecordDaoImpl.java
 * 修改人：KOBE
 * 修改时间：2016年4月29日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sicnu.liaoshijie.labEqmMS.dao.IEqmRepairRecordDao;
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
@Component("eqmRepairRecordDao")
public class EqmRepairRecordDaoImpl extends BaseDaoImpl implements IEqmRepairRecordDao {

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public int repairApply(EqmRepairRecordVo vo) {
		return sqlSession.insert("repairRecord.insert", vo);
	}

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Integer getRepairRecordCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("repairRecord.query_count", paramMap);
	}

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public Integer updateRepairRecordStatusById(EqmRepairRecordVo repairVo) {
		return sqlSession.update("repairRecord.update_status_id", repairVo);
	}

	/** 
	 * {@inheritDoc}.
	 */
	@Override
	public List<EqmLendRecordVo> queryRepairRecordByPage(Map<String, Object> paramMap) {
		return sqlSession.selectList("repairRecord.select_page_map", paramMap);
	}

}
