package com.edu.nchu.distributed;

import com.edu.nchu.distributed.Utils.FileUtils;
import com.edu.nchu.distriuted.excel.ExcelHelper;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alen on 2016/11/12.
 */
public class RenamePDFWithExcelTest {

    @Test
    public void testRenamePdfWithExcel(){
        String fupenzi = "E:\\yawen\\excel\\覆盆子-126.xls";
        String ss = "E:\\yawen\\excel\\桑葚-112.xls";
        String fpzfpd = "E:\\yawen\\Image-20161111-pm061221-126\\";
        String sspfd = "E:\\yawen\\Image-20161111-pm073146-122\\";

        File[] ssArray = FileUtils.getFileList(sspfd);

        Set<String> duplicate = new HashSet<String>();


        for (File file : ssArray) {
            String applayNo = file.getName().replace(".pdf", "").replaceAll("[a-zA-Z]+", "");
            if (duplicate.contains(applayNo)){
                System.out.println("删除重复的pdf文件："+ file.getName());
                FileUtils.deleteFile(file);
            } else {
                duplicate.add(applayNo);
            }
        }
        ssArray = FileUtils.getFileList(sspfd);
        List<String[]> fpzExcelContent = ExcelHelper.importExcel(new File(ss),
                ss.endsWith("xlsx")? true : false);
        for (File file : ssArray) {
            String fileApplyNo = file.getName().replace(".pdf", "").replaceAll("[a-zA-z]+", "");
            System.out.println("文件专利号："+ fileApplyNo);
            for (String[] content : fpzExcelContent){
                System.out.println("excel 文件专利号：" + content[1]);
                if (fileApplyNo.equals(content[1])){
                    FileUtils.renameFile(sspfd, file.getName(), content[0]+".pdf");
                    break;
                }
            }

        }




        /*for (File file : fpzArray) {
            String applayNo = file.getName().replace(".pdf", "").replaceAll("[a-zA-Z]+", "");
//            System.out.println(applayNo);

            if (duplicateFPZ.contains(applayNo)){
                System.out.println("删除重复的pdf文件："+ file.getName());
                FileUtils.deleteFile(file);
            } else {
                duplicateFPZ.add(applayNo);
            }
        }
        fpzArray = FileUtils.getFileList(fpzfpd);
        List<String[]> fpzExcelContent = ExcelHelper.importExcel(new File(fupenzi),
                fupenzi.endsWith("xlsx")? true : false);
        for (File file : fpzArray) {
            String fileApplyNo = file.getName().replace(".pdf", "").replaceAll("[a-zA-z]+", "");
            System.out.println("文件专利号："+ fileApplyNo);
            for (String[] content : fpzExcelContent){
                System.out.println("excel 文件专利号：" + content[1]);
                if (fileApplyNo.equals(content[1])){
                    FileUtils.renameFile(fpzfpd, file.getName(), content[0]+".pdf");
                    break;
                }
            }

        }*/

    }
}
