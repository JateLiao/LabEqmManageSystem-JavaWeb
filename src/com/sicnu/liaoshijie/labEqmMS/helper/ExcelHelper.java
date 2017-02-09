/*
 * 文件名：ExcelHelper.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ExcelHelper.java
 * 修改人：tianzhong
 * 修改时间：2016年3月24日
 * 修改内容：新增
 */
package com.sicnu.liaoshijie.labEqmMS.helper;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.sicnu.liaoshijie.labEqmMS.model.EquipmentVo;
import com.sicnu.liaoshijie.labEqmMS.util.DateUtil;

/**
 * TODO 列表的Excel导入导出Helper.
 * 
 * @author tianzhong
 */
public class ExcelHelper {

    /**
     * TODO 从外部导入设备列表到数据库.
     * 
     * @param filePath
     *            文件导入路径.
     * 
     * @return List<EquipmentVo> 解析excel文档后的设备列表.
     * @throws Exception
     *             .
     */
    @SuppressWarnings("resource")
    public static List<EquipmentVo> parseEquipmentExcelToList(String filePath) throws Exception {
        List<EquipmentVo> list = new ArrayList<EquipmentVo>();
        FileInputStream in = null;

        try {
            Workbook book = null;
            in = new FileInputStream(filePath);

            try {
                book = new XSSFWorkbook(filePath);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(filePath);
                book = new HSSFWorkbook(in);
            }

            int sheets = book.getNumberOfSheets();
            for (int k = 0; k < sheets; k++) {
                Sheet sheet = book.getSheetAt(k);
                Row fisrtRow = sheet.getRow(0); // 获取第一行：表头
                int rows = sheet.getPhysicalNumberOfRows(); // 获取所有行数
                int columns = fisrtRow.getPhysicalNumberOfCells();
                for (int i = 1; i < rows; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) { // 行为空
                        return null;
                    }

                    EquipmentVo vo = new EquipmentVo();
                    setValueToVo(vo, sheet, i, fisrtRow, columns); // 把每行的值注入到设备vo里
                    list.add(vo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return list;
    }
    
    /**
     * TODO 从外部导入设备列表到数据库.
     * 
     * @param filePath
     *            文件导入路径.
     * 
     * @return List<EquipmentVo> 解析excel文档后的设备列表.
     * @throws Exception
     *             .
     */
    @SuppressWarnings("resource")
    public static List<EquipmentVo> parseEquipmentExcelToList(MultipartFile file) throws Exception {
        List<EquipmentVo> list = new ArrayList<EquipmentVo>();

        try {
            Workbook book = null;

            try {
                book = new XSSFWorkbook(file.getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
                book = new HSSFWorkbook(file.getInputStream());
            }

            int sheets = book.getNumberOfSheets();
            for (int k = 0; k < sheets; k++) {
                Sheet sheet = book.getSheetAt(k);
                Row fisrtRow = sheet.getRow(0); // 获取第一行：表头
                int rows = sheet.getPhysicalNumberOfRows(); // 获取所有行数
                int columns = fisrtRow.getPhysicalNumberOfCells();
                for (int i = 1; i < rows; i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) { // 行为空
                        return null;
                    }

                    EquipmentVo vo = new EquipmentVo();
                    setValueToVo(vo, sheet, i, fisrtRow, columns); // 把每行的值注入到设备vo里
                    list.add(vo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	file.getInputStream().close(); // 关闭文件关联
        }

        return list;
    }

    /**
     * 导出设备列表.
     * 
     * @param list
     *            .
     * @return .
     */
    public static HSSFWorkbook exportEquipmentExcel(List<EquipmentVo> list) {
        // 表头
        String[] headers = {"设备资产号", "设备名", "设备类别", "设备价格", "设备厂商", "设备型号", "设备状态", "采购人", "采购时间", "所属学院", "设备所在实验室", "托管人姓名", "托管人账号", "备注"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("设备表");
        HSSFRow row = sheet.createRow(0);

        HSSFCellStyle headerStyle = getHeaderStyle(workbook); // 表头格式
        HSSFCellStyle dataStyle = getDataStyle(workbook); // 数据格式
        row.setHeight((short) 400); // 表头行高

        // 生成表头行
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
            setClomunSize(sheet, i);
        }

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            row.setHeight((short) 350); // 行高
            EquipmentVo vo = list.get(i);
            constructDataContent(row, vo, dataStyle); // 创建数据内容
        }

        return workbook;
    }

    /**
     * TODO 创建数据内容.
     * 
     * @param row
     *            .
     * @param vo
     *            .
     * @param dataStyle
     *            .
     */
    private static void constructDataContent(HSSFRow row, EquipmentVo vo, HSSFCellStyle dataStyle) {
        // 单元格
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        HSSFCell cell6 = row.createCell(6);
        HSSFCell cell7 = row.createCell(7);
        HSSFCell cell8 = row.createCell(8);
        HSSFCell cell9 = row.createCell(9);
        HSSFCell cell10 = row.createCell(10);
        HSSFCell cell11 = row.createCell(11);
        HSSFCell cell12 = row.createCell(12);
        HSSFCell cell13 = row.createCell(13);

        // 设置格式
        cell0.setCellStyle(dataStyle);
        cell1.setCellStyle(dataStyle);
        cell2.setCellStyle(dataStyle);
        cell3.setCellStyle(dataStyle);
        cell4.setCellStyle(dataStyle);
        cell5.setCellStyle(dataStyle);
        cell6.setCellStyle(dataStyle);
        cell7.setCellStyle(dataStyle);
        cell8.setCellStyle(dataStyle);
        cell9.setCellStyle(dataStyle);
        cell10.setCellStyle(dataStyle);
        cell11.setCellStyle(dataStyle);
        cell12.setCellStyle(dataStyle);
        cell13.setCellStyle(dataStyle);

        // 设置值
        cell0.setCellValue(vo.getPropertyNo());
        cell1.setCellValue(vo.getEqmName());
        cell2.setCellValue(vo.getEqmClass());
        cell3.setCellValue((vo.getEqmPrice() == null) ? null : vo.getEqmPrice().toString());
        cell4.setCellValue(vo.getEqmFactory());
        cell5.setCellValue(vo.getEqmType());
        cell6.setCellValue(vo.getEqmStatus());
        cell7.setCellValue(vo.getBuyStaff());
        cell8.setCellValue((vo.getBuyTime() == null) ? null : DateUtil.dateToStringYMD(vo.getBuyTime()));
        cell9.setCellValue(vo.getCollege());
        cell10.setCellValue(vo.getEqmLab());
        cell11.setCellValue(vo.getManager());
        cell12.setCellValue(vo.getManagerId());
        cell13.setCellValue(vo.getDescription());
    }

    /**
     * TODO 设置列宽.
     * 
     * @param sheet
     *            .
     * @param i
     *            .
     */
    private static void setClomunSize(HSSFSheet sheet, int i) {
        switch (i) {
            case 0: // 设备资产号
                sheet.setColumnWidth(i, 5000);
                break;
            case 1: // 设备名
                sheet.setColumnWidth(i, 6000);
                break;
            case 2: // 设备类别
                sheet.setColumnWidth(i, 4500);
                break;
            case 3: // 设备价格
                sheet.setColumnWidth(i, 3800);
                break;
            case 4: // 设备厂商
                sheet.setColumnWidth(i, 3000);
                break;
            case 5: // 设备型号
                sheet.setColumnWidth(i, 3500);
                break;
            case 6: // 设备状态
                sheet.setColumnWidth(i, 3000);
                break;
            case 7: // 采购人
                sheet.setColumnWidth(i, 3000);
                break;
            case 8: // 采购时间
                sheet.setColumnWidth(i, 4000);
                break;
            case 9: // 所属学院
                sheet.setColumnWidth(i, 6000);
                break;
            case 10: // 设备所在实验室
                sheet.setColumnWidth(i, 5000);
                break;
            case 11: // 托管人账号
                sheet.setColumnWidth(i, 3500);
                break;
            case 12: // 托管人姓名
                sheet.setColumnWidth(i, 3500);
                break;
            case 13: // 备注
                sheet.setColumnWidth(i, 15000);
                break;
            default:
                break;
        }

    }

    /**
     * TODO 数据格式.
     * 
     * @param workbook
     *            .
     * @return .
     */
    private static HSSFCellStyle getDataStyle(HSSFWorkbook workbook) {
        HSSFCellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        dataStyle.setWrapText(true); // 自动换行

        HSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        dataStyle.setFont(font);

        return dataStyle;
    }

    /**
     * TODO 表头样式.
     * 
     * @param workbook
     *            .
     * @return .
     */
    private static HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook) {
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
        // headerStyle.setFillForegroundColor(HSSFColor.BLACK.index); // 设置前端色
        headerStyle.setFillBackgroundColor(HSSFColor.GREEN.index); // 设置背景色
        // headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setWrapText(true); // 自动换行

        HSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        headerStyle.setFont(font);

        return headerStyle;
    }

    /**
     * TODO 把每行的值注入到设备vo里.
     * 
     * @param vo
     *            设备vo.
     * @param sheet
     *            sheet.
     * @param index
     *            当前行.
     * @param fisrtRow
     *            表头行.
     * @param columns
     *            总列数.
     */
    private static void setValueToVo(EquipmentVo vo, Sheet sheet, int index, Row fisrtRow, int columns) {
        try {
            Row row = sheet.getRow(index); // 获取当前行
            for (int i = 0; i < columns; i++) {
                Cell cell = row.getCell(i);
                if (cell == null) { // 单元格为空，跳过
                    continue;
                }

                String cellValue = "";
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_FORMULA:
                        cellValue = cell.getCellFormula();
                        break;
                    case Cell.CELL_TYPE_NUMERIC: // 数字型（数字/日期）
                        if (HSSFDateUtil.isCellDateFormatted(cell)) { // 日期
                            cellValue = DateUtil.dateToString(cell.getDateCellValue());
                        } else {
                            DecimalFormat df = new DecimalFormat("0");
                            cellValue = df.format(cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        cellValue = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        cellValue = cell.getBooleanCellValue() + "";
                        break;
                    default:
                        break;
                }
                cellValue = cellValue.trim();

                switch (fisrtRow.getCell(i).getStringCellValue()) {
                    case "设备名":
                        vo.setEqmName(cellValue);
                        break;
                    case "设备资产号":
                        vo.setPropertyNo(cellValue);
                        break;
                    case "设备类别":
                        vo.setEqmClass(cellValue);
                        break;
                    case "设备价格":
                        vo.setEqmPrice(new BigDecimal(cellValue));
                        break;
                    case "设备厂商":
                        vo.setEqmFactory(cellValue);
                        break;
                    case "设备型号":
                        vo.setEqmType(cellValue);
                        break;
                    case "设备状态":
                        vo.setEqmStatus(cellValue);
                        break;
                    case "采购人":
                        vo.setBuyStaff(cellValue);
                        break;
                    case "采购时间":
                        vo.setBuyTime(DateUtil.stringToDate(cellValue));
                        break;
                    case "所属学院":
                        vo.setCollege(cellValue);
                        break;
                    case "设备所在实验室":
                        vo.setEqmLab(cellValue);
                        break;
                    case "托管人账号":
                        vo.setManagerId(cellValue);
                        break;
                    case "托管人姓名":
                        vo.setManager(cellValue);
                        break;
                    case "备注":
                        vo.setDescription(cellValue);
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
