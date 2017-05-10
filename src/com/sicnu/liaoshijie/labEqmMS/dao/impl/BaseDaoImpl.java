/*
 * 文件名：BaseDao.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： BaseDao.java
 * 修改人：tianzhong
 * 修改时间：2015年12月30日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

/**
 * TODO 基础Dao实现类.
 * 
 * @author tianzhong
 */
@Component("baseDaoImpl")
public class BaseDaoImpl {
    /**
     * 写库.
     */
    @Resource(name = "sqlSession")
    protected SqlSessionTemplate sqlSession;

    /**
     * 读库.
     */
    /*
     * @Resource(name = "sqlSession") protected SqlSessionTemplate sqlSessionRead;
     */
}
