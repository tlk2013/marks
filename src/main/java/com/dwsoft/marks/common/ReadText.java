package com.dwsoft.marks.common;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tlk
 * @date 2020/6/4-14:32
 */
public class ReadText {
    private static org.apache.commons.logging.Log logg = LogFactory.getLog(ReadText.class);
    private static List<String> mdnList = new ArrayList<>();
    private static Map<String, String> txtMap = new HashedMap();
    private static String path = "/home/marks/mdndata/";
//    private static String date = "";

    public static List<String> getMdnList() {

        return mdnList;
    }

    public static void clearMdnList() {

        mdnList.clear();
    }

    public static boolean checkTxtMap() {
        logg.warn("判断》》" + txtMap);
        if ("Yes".equals(txtMap.get(getPathAndFileName(0)))) return true;
        return false;
    }

    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     */
    public static boolean readTxtFile() {
        try {
            String pathAndFileName = getPathAndFileName(0);
            String encoding = "UTF-8";
            File file = new File(pathAndFileName);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    if (!StringUtils.isEmpty(lineTxt))
                        mdnList.add(lineTxt.trim());
                }
                read.close();
                txtMap.clear();
                if (mdnList.size() > 0) txtMap.put(pathAndFileName, "Yes");
            } else {
                logg.error("找不到指定的文件");
                return false;
            }
        } catch (Exception e) {
            logg.error("读取文件内容出错：" + e.getMessage());
            return false;
        }
        return true;
    }

    public static String getPathAndFileName(int i) {
        LocalDate today = LocalDate.now();
        String date = today.toString().replaceAll("-", "");
        if (i == 1) {
            LocalDate yesterday = today.plusDays(-1);
            date = yesterday.toString().replaceAll("-", "");
            if ("MONDAY".equals(today.getDayOfWeek().toString())) {
                yesterday = today.plusDays(-3);
                date = yesterday.toString().replaceAll("-", "");
            }

        }
        return path + date + ".txt";
    }


    public static void main(String argv[]) {
        System.out.println(LocalDateTime.now());
        LocalDate yesterday = LocalDate.now().plusDays(-1);
        System.out.println(yesterday);
        System.out.println(yesterday.getDayOfWeek().toString().equals("MONDAY"));
//        readTxtFile();
//        System.out.println(txtMap);
//        System.out.println(checkTxtMap());
//        System.out.println(LocalDateTime.now());
//        System.out.println(mdnList);
    }

    public static void cleartextMap() {
        txtMap.clear();
    }

    /**
     * 删除文件
     */
    public static void deleteFile() {
        String pathAndFileName = getPathAndFileName(1);
        File file = new File(pathAndFileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                logg.warn("删除" + pathAndFileName + "成功");
            } else {
                logg.warn("删除" + pathAndFileName + "失败");
            }
        } else {
            logg.warn("删除" + pathAndFileName + "失败，请确认是否存在");
        }
    }

    public static boolean existsFile() {
        String pathAndFileName = getPathAndFileName(0);
        File file = new File(pathAndFileName);
        if (file.isFile() && file.exists()) { //判断文件是否存在
            return true;
        }
        return false;
    }
}