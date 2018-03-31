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
			System.out.println(user);
			if (user.getType().equals("zuke")) {
				System.out.println("zuke");
				return "zuke/main";
			} else {
				System.out.println("admin");
				return "admin/main1";
			}
		} else {
			System.out.println("index");
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
