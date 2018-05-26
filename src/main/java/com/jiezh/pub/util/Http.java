package com.jiezh.pub.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Http {


    /**
     * @Title: methodGet
     * @Description:  以get方法提交数的使用 参数的方式param="name=test&password=test";
     * @param @return
     * @param @throws Exception
     * @return String
     * @throws
     */
    public static  String methodGet(String url,String param) throws  Exception {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        //代理的设置
//           HttpHost proxy = new HttpHost("10.60.8.20", 8080);
//           httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

        //目标地址
        HttpPost httpGet = new HttpPost(url);

        //构造最简单的字符串数据
//           StringEntity reqEntity = new StringEntity("name=test&password=test");
        StringEntity reqEntity = new StringEntity(param);
        // 设置类型
        reqEntity.setContentType("application/x-www-form-urlencoded");
        // 设置请求的数据
        httpGet.setEntity(reqEntity);
        // 执行
        HttpResponse response = httpclient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        System.out.println(response.getStatusLine());

        if (entity != null) {
            System.out.println("Response content length: " + entity.getContentLength());  //得到返回数据的长度
        }
        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        String line = reader.readLine();
        System.out.println(line);
        if (entity != null) {
            entity.consumeContent();
        }
        return line;
    }

    /**
     * 模拟url访问 从特定的url中获取json
     * @param urlStr
     * @param params
     * @return json object ,or null if failed
     * 参数经过封装后传过来 ，提交为 post请求
     */
    private static JSONObject getJsonFromUrl(String urlStr,
                                             Map<String, String> params) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(urlStr);
        JSONObject json = null;

        try {
            if (params != null) {
                Iterator<String> keys = params.keySet().iterator();
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                while (keys.hasNext()) {
                    String key = keys.next();
                    nvps.add(new BasicNameValuePair(key, params.get(key)));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            }
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            byte[] bytes = new byte[256];
            StringBuffer sb = new StringBuffer();
            while (is.read(bytes) > 0) {
                sb.append(new String(bytes));
                bytes = new byte[256];
            }
            json = JSONObject.fromObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

}