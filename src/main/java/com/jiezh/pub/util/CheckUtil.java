package com.jiezh.pub.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/****
 * 检查实用程序<BR>
 * 
 * @ClassName: CheckUtil
 * 
 * @author 李志恒 E-mail: zhihengli@jiezhongchina.com *
 * 
 * @date 2016年3月1日15:42:37
 */
public class CheckUtil {

    public static void main(String[] args) {

    }

    /**
     * 必须检查方法
     * 
     * @param text
     * @return 输入数据是空白或者为NUll时,返回false <br>
     *         其它返回 true。
     * @since 01-00
     */
    public static boolean checkRequired(String text) {
        return (!StringUtil.isBlank(text));
    }

    /**
     * 時間・时刻检查(24時間制) <br>
     * 時間・时刻是「HHmm」的4为半角数字。
     * 
     * @param str
     * @return 时间、时刻的4位半角数字的情况为true ，其它为false.
     * @since 01-00
     */
    public static boolean check24Time(String str) {

        if (str.length() != 4) {
            return false;
        }

        // 半角数字检查
        if (!checkNumber(str)) {
            return false;
        }

        // HH 检查
        int hour = 0;
        int minute = 0;
        String strHour = str.substring(0, 2);
        String strMinute = str.substring(2);
        try {
            hour = Integer.valueOf(strHour);
            minute = Integer.valueOf(strMinute);
        } catch (NumberFormatException e) {
            // 数字检查前做了处理，所以NumberFormatException绝对不会发生
            ;
        }

        if (hour >= 24 || minute >= 60) {
            return false;
        }

        return true;
    }

    /**
     * 日期检查 <br>
     * 日期相关的输入检查是“yyyyMMdd”8位数的日期形式。
     * 
     * @param str
     * @return 输入参数是yyyyMMdd的日期场合返回 true; <br>
     *         否则返回false。
     * @since 01-00
     */
    public static boolean checkDate(String str) {

        if (str.length() != 10) {
            return false;
        }

        String ymd = str.replaceAll("/", "");
        if (ymd.length() < 1) {
            return false;
        }

        // 日期检查
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(ymd);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 日期检查 <br>
     * 日期相关的输入检查是“yyyyMMdd”8位数的日期形式。
     * 
     * @param str
     * @return 输入参数是yyyyMMdd的日期场合返回true; <br>
     *         否则返回false。
     * @since 01-00
     */
    public static boolean checkDate8(String str) {

        if (str.length() != 8) {
            return false;
        }

        // 日期检查
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(str);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 日期检查 <br>
     * 日期相关的输入检查是【yyyy】4位数的日期形式。
     * 
     * @param str
     * @return 输入参数是yyyy的年的格式。返回true; <br>
     *         其它返回false。
     * @since 01-00
     */
    public static boolean checkDate4(String str) {

        if (str.length() != 4) {
            return false;
        }

        // 半角数字检查
        if (!checkNumber(str)) {
            return false;
        }

        // 日期检查
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(str);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 
     * @param str
     * @return
     */
    public static boolean checkDate2(String str) {

        if (str.length() != 2) {
            return false;
        }

        // 半角数字检查
        if (!checkNumber(str)) {
            return false;
        }

        // 日期检查
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        sdf.setLenient(false);
        try {
            sdf.parse(str);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 日期检查 <br>
     * 日期相关的输入检查是「yyyy/MM」的6位数字形式。
     * 
     * @param str
     * @return 输入参数是yyyy/MM的年月格式场合 返回true; <br>
     *         其它返回false。
     * @since 01-00
     */
    public static boolean checkDate6(String str) {

        if (str.length() != 7) {
            return false;
        }

        // 日期检查
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");
        sdf.setLenient(false);
        try {
            sdf.parse(str);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 日期检查 <br>
     * 日期相关的输入检查是「yyyyMM」的6位日期形式。
     * 
     * @param str
     * @return 输入参数是yyyyMM的年月的场合 返回true; <br>
     *         其它返回false。
     * @since 01-00
     */
    public static boolean checkYYYYMM(String str) {

        if (str.length() != 6) {
            return false;
        }

        // 日期检查
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        sdf.setLenient(false);
        try {
            sdf.parse(str);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * 日期的From-To检查 <br>
     * 日期的FROM-TO的数值「FORM <= TO」的输入检查。
     * 
     * @param from
     * @param to
     * @return FORM <= TO的场合 返回true; <br>
     *         否则 返回false。
     * @since 01-00
     */
    public static boolean checkDateFromTo(String from, String to) {

        return checkFromTo(from, to);
    }

    /**
     * 等级的From-To检查 <br>
     * 等级的FROM-TO的数值「FORM <= TO」的输入检查。
     * 
     * @param from
     * @param to
     * @return FORM <= TO的场合 返回true; <br>
     *         其它 返回false。
     * @since 01-00
     */
    public static boolean checkRankFromTo(String from, String to) {

        return checkFromTo(from, to);
    }

    /**
     * From-To检查 <br>
     * FROM-TO的数值是「FORM <= TO」的输入检查。
     * 
     * @param from
     * @param to
     * @return FORM <= TO的场合 返回true; <br>
     *         否则 返回false。
     * @since 01-00
     */
    private static boolean checkFromTo(String from, String to) {

        if (StringUtil.isBlank(from)) {
            return true;
        }

        if (StringUtil.isBlank(to)) {
            return true;
        }

        if (from.compareTo(to) > 0) {
            return false;
        }

        return true;

    }

    /**
     * 文字列的位数检查。
     * 
     * @param text
     * @param maxLength
     *            文字列的长度
     * @return maxLength比位数多的情况 返回false； 其它 返回true
     * @since 01-00
     */
    public static boolean checkMaxLength(String text, int maxLength) {

        if (text.length() > maxLength) {
            return false;
        }

        return true;
    }

    /**
     * 文字列的位数检查。
     * 
     * @param text
     * @param maxLength
     *            文字列的长度
     * @return maxLength比位数多的情况 返回false； 其它 返回true
     * @since 01-00
     */
    public static boolean checkMaxFormatLength(String text, int maxLength) {

        String value = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= '0' && c <= '9') {
                value = value + c;
            }
        }
        if (value.length() > maxLength) {
            return false;
        }
        return true;
    }

    /**
     * 文字列 字符检查。
     * 
     * @param text
     * @param maxLength
     *            字符串长度
     * @return maxLength比位数多的情况 返回false； <br>
     *         其它 返回true。
     * @since 01-00
     */
    public static boolean checkMaxByte(String text, int maxLength) {

        try {
            if (text.getBytes("UNICODE").length > maxLength) {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 字符串位数检查。
     * 
     * @param text
     * @param minLength
     *            最小字符串位数
     * @param maxLength
     *            最大字符串位数
     * @return 输入文字列数指定范围内 则返回true; <br>
     *         其它 返回false。
     * @since 01-00
     */
    public static boolean checkLength(String text, int minLength, int maxLength) {

        if (text.length() < minLength) {
            return false;
        }
        if (text.length() > maxLength) {
            return false;
        }

        return true;
    }

    /**
     * 字符串一致的检查
     * 
     * @param str1
     * @param str2
     * @return 一致的情况 返回true； 其它 返回false。
     * @since 01-00
     */
    public static boolean checkEqual(String str1, String str2) {
        return (str1.equals(str2));
    }

    /**
     * 字符串的数字混合检查
     * 
     * @param text 输入字符串
     * @return 数字的情况 返回true；其它 返回false
     * @since 01-00
     */
    public static boolean checkHasDigit(String text) {

        boolean ret = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= '0' && c <= '9') {
                ret = true;
                break;
            }
        }

        return ret;
    }

    /**
     * 字符串的英文检查
     * 
     * @param text 输入的字符串
     * @return 英文输入情况 返回true；其它 返回false
     * @since 01-00
     */
    public static boolean checkHasEnglish(String text) {

        boolean ret = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                ret = true;
                break;
            }
        }

        return ret;
    }

    /**
     * 判断字符串是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {

        str = str.trim();

        if (str == null || "".equals(str.trim())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 半角数字检查
     * 
     * @param text 输入字符串
     * @return 半角数字情况 返回true、<br>
     *         其它 返回false。
     * @since 01-00
     */
    public static boolean checkNumber(String text) {

        // 检查是否为空
        if (isBlank(text)) {
            return false;
        }

        // 数字检查
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }

        return true;
    }

    /**
     * 半角数字，连字符检查
     * 
     * @param text 输入的字符串
     * @return 半角数字，连字符的情况 返回true、<br>
     *         其它 返回 false。
     * @since 01-00
     */
    public static boolean checkUntenNumber(String text) {

        // 数字检查
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!(c >= '0' && c <= '9') && c != '-') {
                return false;
            }
        }

        return true;
    }

    /**
     * 半角数字，连字符检查
     * 
     * @param text 输入字符串
     * @return 半角数字，连字符 返回true、<br>
     *         其它 返回false。
     * @since 01-00
     */
    public static boolean checkFormatNumber(String text) {

        // 数字检查
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (i < 3 || i > 3) {
                if (!(c >= '0' && c <= '9')) {
                    return false;
                }
            }
            if (i == 3) {
                if (c != '-') {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 半角数字，圆点， 连接符检查
     * 
     * @param text 输入字符串
     * @return 半角数字，圆点， 连接符情况 返回true、<br>
     *         否则 返回false。
     * @since 01-00
     */
    public static boolean checkUntenNumberWithDot(String text) {

        // 数字检查
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!(c >= '0' && c <= '9') && c != '.' && c != '-') {
                return false;
            }
        }

        return true;
    }

    /**
     * 半角数字，点阵（复数可能）检查
     * 
     * @param text 输入字符串
     * @return 半角数字，圆点的情况 返回true、<br>
     *         否则 返回false。
     * @since 01-00
     */
    public static boolean checkNumberWithDot(String text) {

        // 数字检查
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (!(c >= '0' && c <= '9') && c != '.') {
                return false;
            }
        }

        return true;
    }

    /**
     * 小数点的数值检查
     * 
     * @param text 输入字符串
     * @return 是小数数值的 返回true、<br>
     *         否则 返回false。
     * @since 01-00
     */
    public static boolean isDecimal(String text) {

        // 半角数字，圆点检查
        if (!checkNumberWithDot(text)) {
            return false;
        }

        try {
            new Double(text);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean formatNum(String text, int numFormat) {

        int index = text.indexOf(".");
        if (index >= 1) {
            String textFormat = text.substring(text.indexOf(".") + 1);

            if (numFormat != textFormat.length()) {
                return false;
            }
        } else {
            return true;
        }

        return true;
    }

    /**
     * 半角英数字检查
     * 
     * @param text 输入字符串
     * @return 半角英数字情况 返回true、<br>
     *         其它 返回false。
     * @since 01-00
     */
    public static boolean isAlphaNumber(String text) {

        text = text.trim();
        // 检查是否为空
        if (isBlank(text)) {
            return false;
        }

        boolean ret = true;

        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i);
            if (c >= '0' && c <= '9') {
                continue;
            }
            if (c >= 'a' && c <= 'z') {
                continue;
            }
            if (c >= 'A' && c <= 'Z') {
                continue;
            }

            ret = false;
            break;
        }

        return ret;
    }

    /**
     * 半角字符检查 <br>
     * 键盘上的钥匙范围的。<br>
     * 
     * @param text 输入字符串
     * @return 半角情况 返回true、<br>
     *         否则返回false。
     * @since 01-00
     */
    public static boolean isHankaku(String text) {

        try {

            String correctStr = new String(text.getBytes("utf-8"), "gbk");

            String strHankaku = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "[\u4e00-\u9fa5]"
                + "ｰ｡､ﾞﾟ !#%&()*+,-./:;<=>?@[]^_`{|}~･｢｣$\"\'\\\r\n";
            char arrayChar[];
            arrayChar = correctStr.toCharArray();
            for (int c = 0; c < (arrayChar.length); c++) {
                if (strHankaku.indexOf(arrayChar[c]) == -1) {
                    return false;
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * （半角）只限英文数字符号检查 <br>
     * 键盘上的钥匙范围的。<br>
     * 
     * @param text 输入字符串
     * @return （半角）英文数字符号情况 返回true、<br>
     *         其它 返回false。
     * @since 01-00
     */
    public static boolean isHankakuWithoutMark(String text) {

        String strHankaku =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "ｰ｡､ﾞﾟ !#%&()*+,-./:;<=>?@[]^_`{|}~･｢｣$\"\'\\\r\n";

        char arrayChar[];
        arrayChar = text.toCharArray();
        for (int c = 0; c < (arrayChar.length); c++) {
            if (strHankaku.indexOf(arrayChar[c]) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 邮件地址的检查 <br>
     * 利用可能文字：abcdefghijklmnopqrstuvwxyz0123456789.+-_/? <br>
     * 
     * @param mail 邮件地址
     * @return 邮件地址的形式 返回true，除此之外 返回false
     * @since 01-00
     */
    public static boolean isMailAddr(String mail) {

        if (mail == null) {
            return false;
        }
        if (mail.equals("")) {
            return false;
        }

        // 域名，帐户的有效检查
        int pos = 0;

        pos = mail.indexOf("@");

        if (pos == -1) {
            return false;
        }
        if (pos == 0) return false;
        if (pos == mail.length() - 1) return false;

        // @标志的复数检查及.的前头，最终，连续检查

        int countRet = 0;
        int bufCount = 0;
        char c;
        for (int i = 0; i < mail.length(); i++) {
            c = mail.charAt(i);

            // 半角英数，点，连字符，地下及?、+、-、/有效
            if (isAlphaNumber(String.valueOf(c)) || c == 0x002d || c == 0x005f || c == 0x002e || c == 0x0040 || c == 0x003f || c == 0x002b
                || c == 0x002f) ;
            else {
                return false;
            }
            if (mail.charAt(i) == '@') {
                countRet++;
            }
            if (mail.charAt(i) == '.' && pos < i) {
                // 域名部的前面及最终.存在？
                if (i == pos + 1 || i == (mail.length() - 1)) {
                    return false;
                }
                // 域名部.连续存在。
                if (bufCount == (i - 1)) {
                    return false;
                }
                bufCount = i;
            }
            // 域名部-存在。
            if (mail.charAt(i) == '-' && i == pos + 1) {
                return false;
            }
        }
        // @标志复数存在。
        if (countRet > 1) {
            return false;
        }

        return true;

    }

    /**
     * 上传文件存在检查
     * 
     * @param file
     * @return
     */
    public static boolean isFileExisted(File file) {

        if (file == null) {
            return false;
        } else if (file.length() == 0) {
            return false;
        } else if (!file.isFile()) {
            return false;
        }
        return true;
    }

    /**
     * 字符串位数的检查
     * 
     * @param str
     * @param length 字符串长度
     */
    public static boolean checkNumberLength(String str, int length) {

        if (str.length() == length) {
            return true;
        }

        return false;
    }

    /**
     * 数字必须为半角,范围必须为100-899检查
     */
    public static boolean checkNumberrange(String str) {

        // 检查是否为空
        if (isBlank(str)) {
            return false;
        }
        // 半角数字检查
        if (!checkNumber(str)) {
            return false;
        }
        int i = Integer.valueOf(str);
        if (i < 100) {// 数字大小范围检查
            return false;
        } else if (i > 899) {
            return false;
        }

        return true;
    }

    /**
     * 手机号检查
     */
    public static boolean checkphone(String str) {

        // 检查是否为空
        if (isBlank(str)) {
            return false;
        }
        // 手机格式
        String reg_phone = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        Pattern p = Pattern.compile(reg_phone);
        Matcher m = p.matcher(str);

        if (!m.find()) {
            return false;
        }

        return true;
    }

    /**
     * 身份证号检测 /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/
     */
    public static boolean checkcardId(String str) {

        // 检查是否为空
        if (isBlank(str)) {
            return false;
        }
        // 身份证号格式
        String reg_cardId = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$";
        Pattern p = Pattern.compile(reg_cardId);
        Matcher m = p.matcher(str);

        if (!m.find()) {
            return false;
        }

        return true;
    }

    /**
     * 组播地址
     */
    public static boolean checkip(String str) {

        // 检查是否为空
        if (isBlank(str)) {
            return false;
        }
        // ip格式
        String reg_phone = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        Pattern p = Pattern.compile(reg_phone);
        Matcher m = p.matcher(str);

        if (!m.find()) {
            return false;
        }

        return true;
    }

    /**
     * 网络地址检查
     */
    public static boolean checknetwork(String str) {

        // 检查是否为空
        if (isBlank(str)) {
            return false;
        }
        // 网络地址格式
        String reg_phone = "[a-zA-z]+://[^\\s]*";
        Pattern p = Pattern.compile(reg_phone);
        Matcher m = p.matcher(str);

        if (!m.find()) {
            return false;
        }

        return true;
    }

    /**
     * MAC地址检查
     */
    public static boolean checkmac(String str) {

        // 检查是否为空
        if (isBlank(str)) {
            return false;
        }
        // mac地址格式
        String reg_phone = "[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}-[0-9A-F]{2}";
        // String reg_phone = "[0-9A-F]{2}[0-9A-F]{2}[0-9A-F]{2}[0-9A-F]{2}[0-9A-F]{2}[0-9A-F]{2}";
        Pattern p = Pattern.compile(reg_phone);
        Matcher m = p.matcher(str);

        if (!m.find()) {
            return false;
        }

        return true;
    }

    /**
     * 颜色值检查
     */
    public static boolean checkcolor(String str) {

        // 检查是否为空
        if (isBlank(str)) {
            return false;
        }
        // 颜色值格式
        String reg_phone = "^#[0-9a-fA-F]{6}{1}$";
        Pattern p = Pattern.compile(reg_phone);
        Matcher m = p.matcher(str);

        if (!m.find()) {
            return false;
        }

        return true;
    }

    /**
     * HH:mm:ss时间检查
     */
    public static boolean checktime(String str) {

        // 检查是否为空
        if (isBlank(str)) {
            return false;
        }
        // HH:mm:ss时间格式
        String reg_phone = "(([01]\\d)|(2[0-3])):[0-5]\\d(:[0-5]\\d)?";
        Pattern p = Pattern.compile(reg_phone);
        Matcher m = p.matcher(str);

        if (!m.find()) {
            return false;
        }

        return true;
    }

    /**
     * 匹配地址与频道
     * 
     * @param text
     * @return
     */
    public static boolean checkHanzi(String text) {

        // 检查是否为空
        if (isBlank(text)) {
            return false;
        }

        String strHankaku = "ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ" + "ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ" + "１２３４５６７８９０" + "－．＼＂＂￣！＠#＄％＾＆＊（）＿｜{}：＂＞？＜－＝＼［］；＇，．／*"
            + "ｰ｡､ﾞﾟ !#%&()*+,-./:;<=>?@[]^_`{|}~･｢｣$\"\'\\\r\n";
        char arrayChar[];
        arrayChar = text.toCharArray();
        for (int c = 0; c < (arrayChar.length); c++) {
            if (strHankaku.indexOf(arrayChar[c]) != -1) {
                return false;
            }
        }

        return true;
    }

    /**
     * TextArea
     * 输入文字。
     * 原因：从数据库取得的内容TextArea表示的时候，<有关>
     * 「\r\n」变更处理。
     * 
     * @param str
     * @return
     */
    public static boolean checkBrInTextarea(String str) {
        if (StringUtil.isBlank(str)) {
            return true;
        }

        if (str.toUpperCase().contains(StringUtil.BR)) {
            return false;
        }

        return true;
    }
}
