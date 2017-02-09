package com.sicnu.liaoshijie.labEqmMS;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.sicnu.liaoshijie.labEqmMS.dao.IEquipmentDao;
import com.sicnu.liaoshijie.labEqmMS.model.EquipmentVo;

/*
 * 文件名：Test.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Test.java
 * 修改人：tianzhong
 * 修改时间：2016年1月6日
 * 修改内容：新增
 */

/**
 * TODO 添加类的一句话简单描述.
 * <p>
 * TODO 详细描述
 * <p>
 * TODO 示例代码
 * 
 * <pre>
 * </pre>
 * 
 * @author tianzhong
 */
public class Tester extends BaseTest {
    /**
     * 添加字段注释.
     */
    @Resource(name = "equipmentDao")
    private IEquipmentDao equipmentDao;

    /**
     * TODO 添加方法注释.
     * 
     */
    @Test
    public void testDelteBatch() {
        List<EquipmentVo> list = new ArrayList<EquipmentVo>();
        for (int i = 155254; i < 155264; i++) {
            EquipmentVo e = new EquipmentVo();
            e.setKeyID(i);
            list.add(e);
        }
        equipmentDao.deleteEquipmentBatch(list);
    }

}
