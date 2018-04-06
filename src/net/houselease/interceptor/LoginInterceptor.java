package net.houselease.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.houselease.pojo.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("uri" + request.getRequestURI());
		// System.out.println("path" + request.getServletPath());
		User user1 = (User) request.getSession().getAttribute("user");
		if (user1 == null) {
			response.sendRedirect("/houseLeaseManagement/login.html");
			return false;
		}
		return super.preHandle(request, response, handler);
	}

}
