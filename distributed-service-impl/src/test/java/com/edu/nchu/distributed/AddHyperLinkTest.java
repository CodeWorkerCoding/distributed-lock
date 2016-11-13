package com.edu.nchu.distributed;

import com.edu.nchu.distriuted.excel.ExcelHelper;
import org.junit.Test;

import java.io.File;

/**
 * Created by fujianjian on 2016/11/12.
 */
public class AddHyperLinkTest {


    @Test
    public void testAddHyperLink(){

        String xlsPath = "E:\\yawen\\表格\\覆盆子.xls";
        String filePath = "E:\\yawen\\覆盆子\\";
        String relativePath = "..\\PDF(FuPenZi)\\";

        /*String xlsPath = "E:\\yawen\\表格\\桑葚.xls";
        String filePath = "E:\\yawen\\桑葚\\";
        String relativePath = "..\\PDF(SangShen)\\";*/


        File excelFile = new File(xlsPath);
        ExcelHelper.addHyperLinker(excelFile, xlsPath.endsWith(".xlsx")?true:false, relativePath);
    }
}
