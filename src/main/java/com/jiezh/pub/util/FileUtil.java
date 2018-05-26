
package com.jiezh.pub.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @ClassName: FileUtil
 * @Description: 文件上传处理类
 * @author 陈继龙 E-mail: cqcnihao@139.com
 * @date 2016年1月26日 下午5:26:45
 * 
 */
public class FileUtil {

    static Logger logger = Logger.getLogger(FileUtil.class);

    /** 数据长度 */
    private static int buffersize;

    /** 文件所在 采用静态方法 */
    private static Properties filepProperties = new Properties();

    /** 属性文件的路径 */
    static String profilepath = "file.properties";

    /** 开始获取 */
    static {
        URL path = FileUtil.class.getClassLoader().getResource(profilepath);
        try {
            filepProperties.load(new FileInputStream(new File(path.toURI())));

            buffersize = Integer.parseInt(filepProperties.getProperty("BufferSize"));
        } catch (Exception e) {
            logger.fatal("系统错误", e);
        }
    }

    /**
     * 上传的文件保存。保存失败了的话，null回报。
     * 
     * @param file
     *            保存文件实体
     * @param fileName
     *            保存文件名
     * @param kind
     *            种别:
     * @param year
     *            保存日期时间
     * @return 保存的文件对象
     */
    public static File saveFile(File file, String fileName, String kind, String year) {

        // 路径编辑
        String folderPath = "";
        String path = "";
        // kind: 1、2
        if (kind.equals("1")) {
            path = filepProperties.getProperty("uploadXXXX");
        } else {
            path = filepProperties.getProperty("XXXX");
        }

        folderPath = new StringBuilder(path).toString();

        // 保存路径不存在的时候，就生成一个新的路径
        File folderPathFile = new File(folderPath);
        if (!folderPathFile.exists()) {
            folderPathFile.mkdirs();
        }

        // 文件保存
        String filePath = folderPath + fileName;
        // 保存数据到根目录，具体到文件
        File outputFile = new File(filePath);
        // 文件输入流
        FileInputStream fileInputStream = null;
        // 文件输出流
        FileOutputStream fileOutputStream = null;
        // 自定义一个输入缓冲区
        BufferedInputStream inputStream = null;
        // 自定义一个输出缓冲数区
        BufferedOutputStream outputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            fileOutputStream = new FileOutputStream(outputFile);
            // 输入流放入输入缓冲区中
            inputStream = new BufferedInputStream(fileInputStream);
            // 输出流放入输出缓冲区中
            outputStream = new BufferedOutputStream(fileOutputStream);
            // 缓冲数组
            byte[] buffer = new byte[buffersize];

            int readLength = 0;
            while ((readLength = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readLength);
            }

            outputStream.flush();

        } catch (FileNotFoundException e) {
            logger.error("系统错误", e);
            outputFile = null;
        } catch (IOException e) {
            logger.error("系统错误", e);
            outputFile = null;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                logger.error("系统错误", e);
                outputFile = null;
            }

        }

        return outputFile;
    }

    /**
     * 
     * 单个文件上传
     * 
     * @param is
     * 
     * @param fileName
     * 
     * @param filePath
     * 
     */

    public static void upFile(InputStream is, String fileName, String filePath) {

        FileOutputStream fos = null;

        BufferedOutputStream bos = null;

        BufferedInputStream bis = null;

        File file = new File(filePath);
        System.err.println(filePath);
        if (!file.exists()) {

            file.mkdirs();

        }

        File f = new File(filePath + "/" + fileName);

        try {

            bis = new BufferedInputStream(is);

            fos = new FileOutputStream(f);

            bos = new BufferedOutputStream(fos);

            byte[] bt = new byte[4096];

            int len;

            while ((len = bis.read(bt)) > 0) {

                bos.write(bt, 0, len);

            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (null != bos) {

                    bos.close();

                    bos = null;
                }

                if (null != fos) {

                    fos.close();

                    fos = null;

                }

                if (null != is) {

                    is.close();

                    is = null;

                }

                if (null != bis) {

                    bis.close();

                    bis = null;

                }
            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }

}
