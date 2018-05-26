package com.jiezh.pub.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @author cong ftp操作基础类
 */
public class FtpUtil {
    /**
     * 服务器地址
     */
    private String url;
    /**
     * 服务器端口
     */
    private int port;
    /**
     * 服务器用户名
     */
    private String username;
    /**
     * 服务器密码
     */
    private String password;

    private FTPClient ftpClient;

    /**
     * @param url
     *            服务器地址
     * @param port
     *            服务器端口号
     * @param username
     *            服务器用户名
     * @param password
     *            服务器密码
     * @param path
     *            文件保存的路径
     * @param filename
     *            文件名称
     * @param input
     *            文件内容
     * @return
     */

    public FtpUtil(String url, int port, String username, String password) {
        this.url = url;
        this.port = port;
        this.username = username;
        this.password = password;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(url, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.login(username, password);// 登录
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭FTP链接
     */
    public void closeFTP() {
        try {
            if (ftpClient != null) {
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * 
     * @param path
     *            上传的路径
     * @param filename
     *            上传的文件名字
     * @param input
     *            输入的文件内容
     * @return 结果
     */
    public boolean uploadFile(String path, String filename, InputStream input) {
        boolean success = false;
        try {
            int reply;
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                return success;
            }
            createDir(path);
            ftpClient.changeWorkingDirectory(path);
            ftpClient.storeFile(filename, input);
            input.close();
            ftpClient.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 循环创建路径
     * 
     * @param path
     *            路径
     * @return
     */
    public boolean createDir(String path) {
        boolean success = false;
        try {
            int reply;
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                return success;
            }
            StringTokenizer s = new StringTokenizer(path, "/"); // sign
            s.countTokens();
            String pathName = "";
            while (s.hasMoreElements()) {
                pathName = pathName + "/" + (String) s.nextElement();
                ftpClient.makeDirectory(pathName);
            }
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    /**
     * 循环删除的路径，慎用会将此路径下全部文件删除
     * 
     * @param path
     *            删除的路径
     * @return
     */
    public boolean deleteDir(String path) {
        boolean success = false;
        try {
            int reply;
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                return success;
            }
            String separator;
            if (path.indexOf("/") >= 0) {
                separator = "/";
            } else {
                separator = "\\";
            }
            deleteDir(path, separator);
            ftpClient.removeDirectory(path);
            ftpClient.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    public void deleteDir(String dir, String separator) throws IOException {
        // 获得ftp下的文件信息
        FTPFile[] listFiles = ftpClient.listFiles(dir);
        FTPFile ftpFile;
        for (int i = 0; i < listFiles.length; i++) {// 对本地文件夹下的文件信息进行遍历
            ftpFile = listFiles[i];
            String tmpPath = dir + separator + listFiles[i].getName();
            if (ftpFile.isFile()) { // 删除文件
                ftpClient.deleteFile(tmpPath);
            } else {// 上传文件夹
                deleteDir(tmpPath, separator);// 删除目录
            }
        }
        ftpClient.removeDirectory(dir);
        // 返回到上一级目录
    }

    /**
     * Description: 从FTP服务器下载文件
     * 
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param remotePath
     *            FTP服务器上的相对路径
     * @param fileName
     *            要下载的文件名
     * @param localPath
     *            下载后保存到本地的路径
     * @return
     */
    public boolean downFile(String remotePath, String fileName, String localPath) {
        boolean success = false;
        try {
            int reply;
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                return success;
            }
            ftpClient.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
            ftpClient.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

}
