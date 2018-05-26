package com.jiezh.pub.web;

import com.jiezh.controller.weixin.WeixinSignatureUtil;
import com.jiezh.pub.util.BeanUtil;
import com.jiezh.pub.util.PropertiesUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ServletRequestUtils
// WebRequest
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public abstract class WebAction {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected Model model;
	protected RedirectAttributes redirectAttributes;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.model = model;
		this.redirectAttributes = redirectAttributes;
		request.setAttribute("contextPath", request.getContextPath());
		// 图片下载预览路径
		request.setAttribute("access_root_path", PropertiesUtil.getUPLOAD("access_root_path"));
	}

	public Object getBean(Class<? extends GeneralBean> aClass) {
		return BeanUtil.getBean(aClass, request);
	}

	public List<?> getBeanList(Class<? extends GeneralBean> aClass) {
		return BeanUtil.getBeanList(aClass, request, true);
	}

	/**
	 * 带前后缀 页面 demo.id VO id
	 */
	public Object getBeanByFix(Class<? extends GeneralBean> aClass, String prefix, String postfix) {
		return BeanUtil.getBean(aClass, prefix, postfix, request);
	}

	public List<?> getBeanListByFix(Class<? extends GeneralBean> aClass, String prefix, String postfix) {
		return BeanUtil.getBeanList(aClass, prefix, postfix, request);
	}

	/**
	 * 响应Ajax请求， 返回的json串的格式组装
	 *
	 * @param status
	 *            状态，成功或者失败
	 * @param info
	 *            额外信息（可用于弹框提示的信息等）
	 * @param object
	 *            数据对象
	 * @return 上述参数的组合成的一个Map
	 */
	private Map<String, Object> response(String status, String info, Object object) {
		Map<String, Object> resp = new HashMap<>();
		resp.put("result", status);
		resp.put("info", info);
		resp.put("object", object);
		return resp;
	}

	/**
	 * 返回操作成功的参数组合
	 *
	 * @param info
	 *            额外信息（可用于弹框提示的信息等）
	 * @param object
	 *            数据对象
	 * @return 成功的参数组合
	 */
	protected Map<String, Object> success(String info, Object object) {
		return response("success", info, object);
	}

	/**
	 * 返回操作失败的参数组合
	 *
	 * @param info
	 *            额外信息（可用于弹框提示的信息等）
	 * @param object
	 *            数据对象
	 * @return 失败的操作组合
	 */
	protected Map<String, Object> fail(String info, Object object) {
		return response("fail", info, object);
	}

	@SuppressWarnings("rawtypes")
	public Map getParameters() {
		Map m = new HashMap<String, Object>();
		if (request == null) {
			return m;
		}
		Enumeration<?> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			Object enN = en.nextElement();
			String para = request.getParameter(enN.toString()).trim();
			m.put(enN.toString(), ("undefined".equals(para)) ? "" : para.trim());
		}
		return m;
	}

	/**
	 * 错误提示页面
	 * 
	 * @param returnUrl
	 * @param msg
	 * @return
	 */
	public ModelAndView error(String returnUrl, String msg) {
		ModelAndView mv = new ModelAndView("weixin/pub/error");
		mv.addObject("url", returnUrl);
		mv.addObject("msg", msg);
		return mv;
	}
	/**
	 * 获取微信签名
	 * @param url
	 */
	protected void getWeixinMap(String url) {

		Map<String, Object> map = new HashMap<>();

		try {
			map = WeixinSignatureUtil.getSignature(request, url);
		} catch (IOException | CloneNotSupportedException e) {
			e.printStackTrace();
			System.out.println("获取微信签名信息异常！" + e.getMessage());
		}
		model.addAttribute("noncestr", map.get("noncestr"));
		model.addAttribute("timestamp", map.get("timestamp"));
		model.addAttribute("appid", map.get("appid"));
		model.addAttribute("signature", map.get("signature"));
	}

	/**
	 * 获取微信签名
	 */
	protected void getWeixinMap() {
		getWeixinMap(getRequestURL());
	}

	protected String getRequestURL() {
		String url;
		if (null == request.getQueryString()) {
			url = request.getRequestURL().toString();
		} else {
			url = request.getRequestURL() + "?" + request.getQueryString();
		}
		return url;
	}

}
