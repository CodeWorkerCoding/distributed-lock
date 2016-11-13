package com.edu.nchu.distributed.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 文件解析工具
 * Created by fujianjian on 2016/11/11.
 */
public class FileUtils {

    private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static void renameFile(String path, String oldName, String newName){
        if (!oldName.equals(newName)){
            File oldFile = new File(path+oldName);
            File newFile = new File(path+newName);
            if (!oldFile.exists()){
                logger.info("旧的文件不存在， 重命名失败");
                return;
            }
            if (newFile.exists()){
                logger.info("新命名的文件已经存在，重命名失败");
                return;
            }
            oldFile.renameTo(newFile);
        } else {
            logger.info("新旧文件名相同，不需要重新命名， 重命名失败");
        }
    }

    public static File[] getFileList(String path){
        File file = new File(path);
        if (!file.exists()){
            logger.info("路径不存在.......");
        } else if (file.isDirectory()){
            return file.listFiles();
        } else if (file.isFile()){
            return new File[]{file};
        }
        return null;
    }

    public static String[] getFileNameList(String path){
        File file = new File(path);
        if (!file.exists()){
            logger.info("路径不存在.......");
        } else if (file.isDirectory()){
            return file.list();
        } else if (file.isFile()){
            return new String[]{file.getName()};
        }
        return null;
    }

    public static void deleteFile(File file){
        if (file.exists()){
            file.delete();
        }
    }

    public static void main_bak(String[] args) {
//        String path = "E:\\yawen\\Image-20161111-pm061221-126";
        String path = "E:\\yawen\\Image-20161111-pm073146-122\\";
//        String path = "E:\\yawen\\Image-20161111-pm073146-122\\cn201610687740ap.pdf";
//        File[] fileList = getFileList(path);

        /*String[] preNames = getFileNameList(path);
        for (String name : preNames) {
            System.out.println(path + " 路径下的文件名：" + name);
        }*/

//        deleteFile(new File(path));
//        renameFile(path, "rename_by_java.pdf", "cn201510731725ap.pdf");

        String[] names = getFileNameList(path);

//        System.out.println(path + " 路径下文件个数：【" +fileList.length + "】 个");
        System.out.println(path + " 路径下文件个数：【" +names.length + "】 个");
        for (String name : names) {
            System.out.println(path + " 路径下的文件名：" + name);
        }
        /*for (File file : fileList) {
            if (file.isFile()){
                System.out.println("文件：" + file);
            }
            if (file.isDirectory()){
                System.out.println("文件夹：" + file);
            }
        }*/
    }

}
