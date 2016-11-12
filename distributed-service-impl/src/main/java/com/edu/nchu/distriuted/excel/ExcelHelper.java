package com.edu.nchu.distriuted.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
