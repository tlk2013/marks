package com.dwsoft.marks.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dwsoft.marks.common.CommonUtils;
import com.dwsoft.marks.common.ResponUtil;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

public class MyInterceptor implements HandlerInterceptor {
	private static org.apache.commons.logging.Log logg = LogFactory
			.getLog(MyInterceptor.class);

	/**
	 * ���ز����Ƿ�Ϊ��
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			String mdn = request.getParameter("mdn");
			if (CommonUtils.isEmpty(mdn)) {
				response.getWriter().print(
						new Gson().toJson(ResponUtil.respon(1001,
								"�����쳣,���mdn����Ϊ�� ")));
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logg.warn("MyInterceptor>>" + e.getMessage());
			return false;
		}
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}