package net.houselease.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * TODO : 路由控制器
 */
@Controller
@RequestMapping(value = "")
public class RouteController {

	@RequestMapping(value = "")
	public String index() {
		System.out.println("index");
		return "redirect:/login.jsp";
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
