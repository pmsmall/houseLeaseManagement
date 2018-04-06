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
		if (attributes.containsAttribute(Dictionary.loginSession)) {
			User user = (User) httpSession.getAttribute(Dictionary.user);
			if (user.getType().equals("zuke")) {
				return "zuke/main";
			} else {
				return "admin/main1";
			}
		} else {
			return "redirect:/login.html";
		}
	}

	@RequestMapping("404")
	public String notFound() {
		return "redirect:/error.html";
	}

	@RequestMapping("500")
	public String serverError() {
		return "redirect:/serverError.html";
	}

	@RequestMapping(value = "/center")
	public String center(HttpSession httpSession, RedirectAttributes attributes) {
		User user = (User) httpSession.getAttribute(Dictionary.user);
		if (user != null) {
			if (user.getType().equals("zuke")) {
				return "zuke/main";
			} else {
				return "admin/main1";
			}
		} else {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/about")
	public String about() {
		return "about";
	}

	@RequestMapping(value = "/help")
	public String help() {
		return "help";
	}

	@RequestMapping(value = "/system")
	public String system() {
		return "system-setting";
	}

}
