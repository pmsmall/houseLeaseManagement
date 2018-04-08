package net.houselease.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.houselease.pojo.User;
import net.houselease.staticData.Dictionary;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// System.out.println("requested");
		// System.out.println(request.getRequestURI());
		User user = (User) request.getSession().getAttribute(Dictionary.USER_FIELD);
		if (user == null) {
			response.sendRedirect(Dictionary.LOGIN_URI);
			return false;
		}
		return super.preHandle(request, response, handler);
	}

}
