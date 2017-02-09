/*
 * 文件名：NewController.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： NewController.java
 * 修改人：tianzhong
 * 修改时间：2015年12月30日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sicnu.liaoshijie.commons.Response;
import com.sicnu.liaoshijie.labEqmMS.model.EquipmentVo;
import com.sicnu.liaoshijie.labEqmMS.service.IEquipmentService;
import com.sicnu.liaoshijie.labEqmMS.util.DateUtil;

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
@Controller
@RequestMapping("/test")
public class NewController {
    /**
     * 添加字段注释.
     */
    // Logger logger = Logger.getLogger(NewController.class);

    /**
     * 添加字段注释.
     */
    @Resource(name = "equipmentService")
    private IEquipmentService equipmentService;

    /**
     * TODO 添加方法注释.
     * 
     * @param request
     *            .
     * @param response
     *            .
     */
    @RequestMapping("/create.htm")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        System.err.println("Working...");

        EquipmentVo paramBo = new EquipmentVo();
        paramBo.setBuyStaff("Scotte");
        paramBo.setBuyTime(new Date());
        paramBo.setCollege("计算机科学学院");
        paramBo.setEqmName("交换机");
        paramBo.setEqmLab("第一实验楼西201");
        paramBo.setEqmPrice(new BigDecimal("49999"));

        Response<Integer> re = equipmentService.insertEquipment(paramBo);
        System.out.println("单次插入：" + re.getContent());

        List<EquipmentVo> list = new ArrayList<EquipmentVo>();
        try {
            for (int i = 0; i < 500; i++) {
                EquipmentVo bo = new EquipmentVo();
                bo.setBuyStaff("Scotte" + i);
                bo.setBuyTime(new Date());
                bo.setCollege("计算机科学学院" + i);
                bo.setEqmName("交换机" + i);
                bo.setEqmPrice(new BigDecimal("49999" + i));
                bo.setManager("Manager" + i);
                bo.setBuyTime(DateUtil.stringToDate("2016-01-22 12:00:00"));
                list.add(bo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        re = equipmentService.insertEquipmentList(list);
        System.out.println("批量插入：" + re.getContent());

        System.out.println("Finished!");
    }

    /**
     * TODO 添加方法注释.
     * 
     * @param req
     *            .
     * @param res
     *            .
     */
    @RequestMapping("/createHugeData.htm")
    public void find(HttpServletRequest req, HttpServletResponse res) {
        System.err.println("Working...");
        
         

        try {
            for (int i = 0; i < 10000000; i++) {
                System.out.println(i + 1);

                EquipmentVo bo = new EquipmentVo();
                bo.setBuyStaff("Scotte" + i);
                bo.setBuyTime(new Date());
                bo.setCollege("计算机科学学院" + i);
                bo.setEqmName("交换机" + i);
                bo.setEqmPrice(new BigDecimal("49999" + i));
                bo.setManager("Manager" + i);
                switch (i % 4) {
                    case 0:
                        bo.setEqmStatus("使用中");
                        break;
                    case 1:
                        bo.setEqmStatus("库存中");
                        break;
                    case 2:
                        bo.setEqmStatus("维修中");
                        break;
                    case 3:
                        bo.setEqmStatus("其他");
                        break;
                    default:
                        break;
                }
                bo.setDescription("我他妈真帅" + i);
                bo.setEqmLab("第一实验楼西" + i + 1);
                bo.setBuyTime(DateUtil.stringToDate(DateUtil.dateToString(new Date())));

                equipmentService.insertEquipment(bo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.err.println("Finished!");
    }

}
