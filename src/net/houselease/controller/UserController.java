package net.houselease.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.houselease.pojo.User;
import net.houselease.service.interfaces.UserService;
import net.houselease.staticData.Dictionary;

@Controller
@RequestMapping("")
public class UserController {

	static {
		System.out.println("Init class UserController");
	}

	@Resource
	private UserService userService;

	@RequestMapping("/login")
	public String userList() throws Exception {
		return "login";
	}

	@RequestMapping("/logincheck")
	public String login(User user, Model model, HttpSession httpSession) throws Exception {
		System.out.println(user);
		User user1 = userService.login(user);

		if (user1 != null) {
			httpSession.setAttribute(Dictionary.user, user1);
			if (user1.getType().equals("zuke")) {
				return "zuke/main";
			} else {
				return "admin/main1";
			}
		} else {
			String error = "error";
			model.addAttribute("error", error);
			return "login";
		}
	}

	@RequestMapping("/toindex")
	public String toindex(Model model) throws Exception {

		return "admin/index";
	}
}
