package com.jiezh.pub.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.Random;

/****
 * 检查实用程序<BR>
 * 
 * @ClassName: StringUtil
 * 
 * @author 李志恒 E-mail: zhihengli@jiezhongchina.com *
 * 
 * @date 2016年3月1日15:42:37
 */
public class StringUtil {

    public static final String BR = "<BR>";

    /**
     * 外行代码<BR>
     * 有关转换
     */
    public static String lineCodeToBR(String txtOriginal) {

        if (isBlank(txtOriginal)) {
            return txtOriginal;
        }

        String txtChanged = txtOriginal.replace("\r\n", BR);
        txtChanged = txtChanged.replace("\r", BR);
        txtChanged = txtChanged.replace("\n", BR);

        return txtChanged;
    }

    /**
     * 去除双引号
     * 
     * @param str
     * @return
     */
    public static String delDoubleQuotationMarks(String str) {
        String retString = str.substring(1, str.length() - 1);
        return retString;
    }

    /**
     * 判断字符串是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {

        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 32MD5加密
     * 
     * @param plainText
     * @return
     */
    public final static String md5(String plainText) {

        // 返回字符串
        String md5Str = null;
        try {
            // 操作字符串
            StringBuffer buf = new StringBuffer();

            MessageDigest md = MessageDigest.getInstance("MD5");

            // 添加要进行计算摘要的信息,使用 plainText 的 byte 数组更新摘要。
            md.update(plainText.getBytes());

            // 计算出摘要,完成哈希计算。
            byte b[] = md.digest();
            int i;

            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                // 将整型 十进制 i 转换为16位，用十六进制参数表示的无符号整数值的字符串表示形式。
                buf.append(Integer.toHexString(i));
            }

            // 32位的加密
            md5Str = buf.toString();
            // 16位的加密
            // md5Str = buf.toString().md5Strstring(8,24);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    /**
     * 字符串数字组合加1
     * 
     * @param string
     * @return
     */
    public static String getStringNcAddOne(String string) {
        String s = "";
        int str = 0;
        int strNew = 0;
        // string = "001001";
        int j = 0;
        for (int i = 0; i < string.length(); i++) {
            str = Integer.parseInt(String.valueOf(string.charAt(i)));
            if (str != 0) {
                str = Integer.parseInt(string.substring(i, string.length()));
                strNew = str + 1;
                break;
            } else {
                strNew = 1;
                j++;
            }
        }
        if (String.valueOf(str).length() != String.valueOf(strNew).length()) {
            j = j - 1;
        }

        for (int i = 0; i < j; i++) {
            s = s + 0;
        }
        return s + strNew;
    }

    /**
     * 指定的数字0填补
     * 
     * @param num
     *            格式化数字
     * @param len
     *            行数
     * @return
     */
    public static String fillZero(int num, int len) {

        String ret = "";

        String format = "%1$0" + String.valueOf(len) + "d";

        ret = String.format(format, num);

        return ret;
    }

    /**
     * 指定的字符串Integer转换
     * 
     * @param num
     *            格式化数字
     * @return Integer
     */
    public static Integer stringToInteger(String num) {

        Integer ig = null;

        if (isBlank(num)) {
            return null;
        }

        ig = Integer.valueOf(num.replace(",", ""));

        return ig;
    }

    /**
     * 指定时间格式
     * 
     * @param money
     *            金額
     * @return String
     */
    public static String formatMoney(String money) {

        if (isBlank(money)) {
            return money;
        }

        Double dMoney = Double.valueOf(money);
        NumberFormat cFormat = NumberFormat.getNumberInstance();
        String ret = cFormat.format(dMoney);

        // 小数点を切り捨て
        int i = ret.indexOf(".");
        if (i > 0) {
            ret = ret.substring(0, i);
        }

        return ret;
    }

    /**
     * 指定时间格式（Double时间）
     * 
     * @param money
     *            金額
     * @return String
     */
    public static String formatTimes(Double time) {

        String format = "%1$,1.2f";
        String ret = String.format(format, time);

        return ret;
    }

    /**
     * 指定时间格式（Ingeger时间）
     * 
     * @param time
     * @return String
     */
    public static String formatTimes(Integer time) {

        Double dTime = Double.valueOf(time);
        String format = "%1$,1.2f";
        String ret = String.format(format, dTime);

        return ret;
    }

    /**
     * 指定时间格式（日报时间）
     * 
     * @param time
     * @return String
     */
    public static String formatTimes(String time) {

        if (isBlank(time)) {
            return time;
        }
        Double timeTemp = Double.parseDouble(time);

        String format = "%1$,1.2f";
        String ret = String.format(format, timeTemp);

        return ret;
    }

    /**
     * 指定的金额格式的字符串格式化
     * 
     * @param money
     *            金額
     * @return String
     */
    public static String formatMoneyToString(Integer money) {

        if (money == null) {
            return "";
        }

        String format = "%1$-10d";
        String ret = String.format(format, money);

        return ret.trim();
    }

    /**
     * FILECD格式
     * 
     * @param fileCd
     * @return String
     */
    public static String fileCdformat(String fileCd) {

        if (fileCd == null) {

        } else {
            fileCd = fileCd.trim();
        }

        return fileCd;
    }

    /**
     * 判断是否为中文
     * 
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为中文
     * 
     * @param strName
     * @return
     */
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c) == true) {
                return isChinese(c);
            }
        }
        return false;
    }

    /**
     * 获取本机IP地址
     * 
     * @return
     */
    public static String loadIp() {

        InetAddress addr;
        String retIp = "";
        try {
            addr = InetAddress.getLocalHost();
            retIp = addr.getHostAddress();// 获得本机IP
            // String address = addr.getHostName();//获得本机名称
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return retIp;
    }

    /**
     * 获取随机数字
     * 
     * @param length
     * @return
     */
    public static String getCharAndNumr(int length) {
        String val = "";

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }

        return val;
    }

    /**
     * 随机数
     * 
     * @param length
     * @return
     */
    public static String getRandomNum(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    public static String toUtf8String(String s){
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (c >= 0 && c <= 255){sb.append(c);}
            else{
                byte[] b;
                try { b = Character.toString(c).getBytes("utf-8");}
                catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
