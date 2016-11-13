package com.edu.nchu.distriuted.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Excel 解析器
 * Created by fujianjian on 2016/11/12.
 */
public class ExcelHelper {

    private final static Logger logger = LoggerFactory.getLogger(ExcelHelper.class);

    public static List<String[]> importExcel(File excelFile, boolean isXlsx) {

        FileInputStream fileInputStream = null;
        List<String[]> result = new ArrayList<String[]>();

        try {
            fileInputStream = new FileInputStream(excelFile);
            Workbook workbook ;
            try {
                if (isXlsx){
                    workbook = new XSSFWorkbook(fileInputStream);
                } else {
                    workbook = new HSSFWorkbook(fileInputStream);
                }
                Set<String> duplicateNum = new HashSet<String>();
                for (Sheet sheet : workbook) {
                    if (sheet == null) continue;
                    for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
                        Row row = sheet.getRow(rowNum);
                        String num = row.getCell(0).toString();
                        String applyNo = row.getCell(4).toString();
                        logger.info("序号：{}", num);
                        logger.info("专利号：{}", applyNo);
                        if (duplicateNum.contains(applyNo)){
                            logger.info("重复的专利号：{}", applyNo);
                            continue;
                        } else {
                            duplicateNum.add(applyNo);
                         }
                        result.add(new String[]{num.replace(".0", ""), applyNo.replace("-", "")});
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static void addHyperLinker(File excelFile, boolean isXlsx, String hyperLinkAddress){
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(excelFile);
            Workbook workbook;
            CellStyle cellStyle;
            try {
                if (isXlsx) {
                    workbook = new XSSFWorkbook(fileInputStream);
                    cellStyle = workbook.createCellStyle();
                    Font hyperLinkFont = workbook.createFont();
                    hyperLinkFont.setUnderline(XSSFFont.U_SINGLE);
                    hyperLinkFont.setColor(HSSFColor.BLUE.index);
                    cellStyle.setFont(hyperLinkFont);
                } else {
                    workbook = new HSSFWorkbook(fileInputStream);
                    cellStyle = workbook.createCellStyle();
                    Font hyperLinkFont = workbook.createFont();
                    hyperLinkFont.setUnderline(HSSFFont.U_SINGLE);
                    hyperLinkFont.setColor(HSSFColor.BLUE.index);
                    cellStyle.setFont(hyperLinkFont);
                }

                for (Sheet sheet : workbook) {
                    if (sheet == null) continue;
                    for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
                        Row row = sheet.getRow(rowNum);
                        Hyperlink link = null;
                        if (isXlsx){
                            Hyperlink hssfLink = new HSSFHyperlink(HSSFHyperlink.LINK_FILE);
                            link = new XSSFHyperlink(hssfLink);
                        } else {
                            link = new HSSFHyperlink(HSSFHyperlink.LINK_FILE);
                        }
                        /*StringBuffer sb = new StringBuffer(
                                new String(hyperLinkAddress.getBytes(), Charset.forName("UTF-8")));
                        StringBuffer sb = new StringBuffer(hyperLinkAddress);
                        sb.append(row.getCell(0).toString()
                                .replace(".0", "")).append(".pdf");*/
                        String hpyerAddress = hyperLinkAddress+row.getCell(0)
                                .toString().replace(".0", "")+".pdf";
                        System.out.println("地址："+hpyerAddress);
                        link.setAddress(new String(hpyerAddress.getBytes(), Charset.forName("UTF-8")));
                        Cell cell =  row.getCell(5);
                        cell.setCellStyle(cellStyle);
                        cell.setHyperlink(link);
                        System.out.println("单元格的超链接地址为："+cell.getHyperlink().getAddress());
                    }
                }
                fileOutputStream = new FileOutputStream(excelFile);
//                OutputStreamWriter  outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
                workbook.write(fileOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        /*String path = "C:\\Users\\Alen\\Documents\\Tencent Files\\1455794830\\FileRecv\\覆盆子.xls";
        boolean isXlsx = false;
        File file = new File(path);
        if (path.endsWith("xlsx")){
            isXlsx = true;
        }
        List<String[]> result = importExcel(file, isXlsx);
        System.out.println(result.get(0)[0]+"    "+result.get(0)[1]);*/

        String str = "ap00034003430bp";
        System.out.println(str.replaceAll("\\d+", ""));
        System.out.println(str.replaceAll("[a-zA-Z]+", ""));
    }
}
