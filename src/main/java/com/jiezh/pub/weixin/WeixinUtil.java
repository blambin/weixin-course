package com.jiezh.pub.weixin;

import com.jiezh.pub.weixin.sdk.WXPayConstants;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.*;

public class WeixinUtil {

    /**
     * 解析XML文件
     */
    public static Map<String, String> decodeXml(String content) {

        try {
            Map<String, String> xml = new HashMap<String, String>();
            XmlPullParserFactory pullParserFactory = XmlPullParserFactory
                    .newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                String nodeName = parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        if ("xml".equals(nodeName) == false) {

                            xml.put(nodeName, parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }

            return xml;
        } catch (Exception e) {

        }
        return null;

    }

    /**
     * 生成XML文件
     */
    private static String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<" + params.get(i).getName() + ">");
            String value = params.get(i).getValue();
            String special = "&";
            String xmlcharacter = "&amp;";
            if (value.contains(special)) {
                value = value.replace(special, xmlcharacter);
            }
            sb.append(value);
            sb.append("</" + params.get(i).getName() + ">");
        }
        sb.append("</xml>");

        try {
            return new String(sb.toString().getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 生成请求参数
     */
    public static String genProductArgs(String body, String out_trade_no, String spbill_create_ip, String total_fee) {

        try {

            // 随机字符串，长度要求在32位以内。推荐随机数生成算法
            String nonceStr = genNonceStr();

            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", WXPayConstants.APP_ID));
            packageParams.add(new BasicNameValuePair("mch_id", WXPayConstants.MCH_ID));
            packageParams.add(new BasicNameValuePair("notify_url", WXPayConstants.NOTIFY_URL));
            packageParams.add(new BasicNameValuePair("trade_type", WXPayConstants.TRADE_TYPE));
            packageParams.add(new BasicNameValuePair("body", body));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("out_trade_no", out_trade_no));
            packageParams.add(new BasicNameValuePair("spbill_create_ip", spbill_create_ip));
            packageParams.add(new BasicNameValuePair("total_fee", total_fee));

            //生成签名
            String sign = genPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));

            String xmlstring = toXml(packageParams);

            return xmlstring;
        } catch (Exception e) {

            return null;
        }

    }

    /**
     * 签名
     */
    private static String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000))
                .getBytes());
    }

    /**
     * 签名
     */
    private static String genPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(WXPayConstants.API_KEY);

        String packageSign = MD5.getMessageDigest(sb.toString().getBytes())
                .toUpperCase();
        return packageSign;
    }

    public static byte[] httpGet(String url) {
        if (url == null || url.length() == 0) {
            return null;
        }

        HttpClient httpClient = getNewHttpClient();
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse resp = httpClient.execute(httpGet);
            if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return null;
            }

            return EntityUtils.toByteArray(resp.getEntity());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] httpPost(String url, String entity) {
        if (url == null || url.length() == 0) {
            return null;
        }


        try {
            HttpClient httpClient = getNewHttpClient();

            HttpPost httpPost = new HttpPost(url);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            try {
                httpPost.setEntity(new StringEntity(entity));


                HttpResponse resp = httpClient.execute(httpPost);
                if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {

                    return null;
                }

                return EntityUtils.toByteArray(resp.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return null;
    }

    private static HttpClient getNewHttpClient() {
        try {
            //指定读取证书格式为PKCS12
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            //读取本机存放的PKCS12证书文件
            FileInputStream instream = new FileInputStream(new File("E:\\cert\\apiclient_cert.p12"));
            try {
                //指定PKCS12的密码(商户ID)
                keyStore.load(instream, WXPayConstants.MCH_ID.toCharArray());
            } finally {
                instream.close();
            }
            SSLContext sslcontext;
            sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, WXPayConstants.MCH_ID.toCharArray()).build();
            //指定TLS版本
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            //设置httpclient的SSLSocketFactory
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();
            return httpClient;
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

}
