package net.houselease.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.houselease.pojo.User;
import net.houselease.staticData.Dictionary;

/**
 * 
 * TODO : 路由控制器
 */
@Controller
@RequestMapping(value = "")
public class RouteController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(HttpSession httpSession, RedirectAttributes attributes) {
		if (attributes.containsAttribute(Dictionary.LOGIN_SESSION)) {
			User user = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
			if (user.getType().equals(Dictionary.RENTER_ROLE)) {
				return Dictionary.RENTER_FIELD + Dictionary.MAIN_VIEW;
			} else if (user.getType().equals(Dictionary.ADMIN_ROLE)) {
				return Dictionary.ADMIN_FIELD + Dictionary.MAIN_VIEW;
			}
		}
		return Dictionary.LOGIN_VIEW;
	}

	@RequestMapping("/404")
	public String notFound() {
		return "redirect:/error.html";
	}

	@RequestMapping("/500")
	public String serverError() {
		return "redirect:/serverError.html";
	}

	@RequestMapping(value = "/center")
	public String center(HttpSession httpSession, RedirectAttributes attributes) {
		User user = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		if (user != null) {
			if (user.getType().equals(Dictionary.RENTER_ROLE)) {
				return Dictionary.RENTER_FIELD + Dictionary.MAIN_VIEW;
			} else if (user.getType().equals(Dictionary.ADMIN_ROLE)) {
				return Dictionary.ADMIN_FIELD + Dictionary.MAIN_VIEW;
			}
		}
		return Dictionary.LOGIN_VIEW;
	}
}
