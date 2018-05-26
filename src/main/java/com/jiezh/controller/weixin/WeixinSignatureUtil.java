package com.jiezh.controller.weixin;

import com.alibaba.fastjson.JSONObject;
import com.jiezh.pub.weixin.sdk.WXPayConstants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 微信签名工具
 */
public class WeixinSignatureUtil {
	
	private static Logger log = LoggerFactory.getLogger(WeixinSignatureUtil.class);

	/**
	 * 获取微信签名信息
	 * @param request
	 * @param requestUrl	请求页面地址
	 * @return	返回map：noncestr:随机字符串；timestamp：签名时间戳；appid;微信公众号Id;signature:签名串
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws CloneNotSupportedException
	 */
	public static Map<String,Object> getSignature(HttpServletRequest request,String requestUrl) throws ClientProtocolException, IOException, CloneNotSupportedException{
		Map<String, Object> map = new HashMap<String,Object>();

		HttpSession session = request.getSession();

		// 直接查询签名信息
		Object objMap = session.getAttribute(requestUrl);
		if (objMap != null) {
			return (Map<String, Object>) objMap;
		}

		String appid = WXPayConstants.APP_ID;
		String secret = WXPayConstants.SECRET;
		String token;
		String jsapi_ticket;

		Object tokenObj = session.getAttribute("token");
		if (tokenObj != null) {
			token = String.valueOf(tokenObj);
		} else {
			token = getToken(appid, secret);
			session.setAttribute("token", token);
		}

		Object jsapiTicketObj = session.getAttribute("jsapi_ticket");
		if (jsapiTicketObj != null) {
			jsapi_ticket = String.valueOf(jsapiTicketObj);
		} else {
			jsapi_ticket = getTicket(token);
			session.setAttribute("jsapi_ticket", jsapi_ticket);
		}

		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();

		String signature = "";

		//注意这里参数名必须全部小写，且必须有序
		String string1 = "jsapi_ticket=" + jsapi_ticket +
				"&noncestr=" + nonce_str +
				"&timestamp=" + timestamp +
				"&url=" + requestUrl;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		map.put("noncestr", nonce_str);
		map.put("timestamp", timestamp);
		map.put("appid", appid);
		map.put("signature", signature);

		session.setAttribute(requestUrl, map);

		return map;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash)
		{
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	public static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	
	/**
	 * 
	 * @param appid		公众号应用id
	 * @param secret	公众号应用密钥
	 * @return 返回token
	 * @throws IOException
	 * @throws CloneNotSupportedException
	 */
	public static String getToken(String appid,String secret) throws IOException, CloneNotSupportedException{
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;
		System.out.println(url);
		log.info("url:"+url);
		// 生成一个请求对象
		HttpGet httpGet = new HttpGet(url);
		// 生成一个Http客户端对象
        HttpClient httpClient = new DefaultHttpClient();
        // 下面使用Http客户端发送请求，并获取响应内容
        InputStream inputStream = null;
        // 发送请求并获得响应对象
        HttpResponse mHttpResponse = null;
        
        BufferedReader bufferedReader = null;
        String result = "";
		String line;
		try {
			mHttpResponse = httpClient.execute(httpGet);
			// 获得响应的消息实体
	        HttpEntity mHttpEntity = mHttpResponse.getEntity();
	       
	        // 获取一个输入流
			inputStream = mHttpEntity.getContent();
			bufferedReader = new BufferedReader(
			                new InputStreamReader(inputStream));
			while ((line = bufferedReader.readLine()) != null) {
			    result += line;
			}
			JSONObject json = JSONObject.parseObject(result);
			return json.get("access_token").toString();
		} catch (IOException e) {
			throw new IOException("获取access_token异常！");
		}finally {
			bufferedReader.close();
			inputStream.close();
			httpGet.clone();
		}
	}
	
	/**
	 * 
	 * @param access_token 生成的token
	 * @return	返回jsapi_ticket
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getTicket(String access_token) throws ClientProtocolException, IOException{
		HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi");
		// 生成一个Http客户端对象
        HttpClient httpClient = new DefaultHttpClient();
        // 下面使用Http客户端发送请求，并获取响应内容
        InputStream inputStream = null;
        // 发送请求并获得响应对象
        HttpResponse mHttpResponse = null;
        
        BufferedReader bufferedReader = null;
        String result = "";
		String line;
		try {
			mHttpResponse = httpClient.execute(httpGet);
			HttpEntity mHttpEntity = mHttpResponse.getEntity();
			inputStream = mHttpEntity.getContent();
			bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));
			while ((line = bufferedReader.readLine()) != null) {
				result += line;
			}
			JSONObject json2 = JSONObject.parseObject(result);
			
			return json2.get("ticket").toString();
		} catch (IOException e) {
			throw new IOException("获取jsapi_ticket异常！");
		}finally {
			bufferedReader.close();
			inputStream.close();
		}
	}
	
	/**
	 * 
	 * @Title getPhotoWeixinUrl
	 * @Description 获取微信图片上传路径
	 * @param media_id
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws CloneNotSupportedException 
	 * @throws IOException 
	 * @throws 
	 * @author 潘 鹏 pengpengpan@huashenghaoche.com
	 * @date 2017年5月23日 上午10:25:34
	 */
	public static String getPhotoWeixinUrl(String media_id) throws NoSuchAlgorithmException, IOException, CloneNotSupportedException{
		String appid = WXPayConstants.APP_ID;
		String secret = WXPayConstants.SECRET;
		String token = getToken(appid,secret);
        String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + token + "&media_id=" + media_id;
        return url;
    }
}
