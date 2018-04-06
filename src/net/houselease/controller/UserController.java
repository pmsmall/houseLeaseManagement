package net.houselease.controller;

import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import net.houselease.pojo.SimpleUser;
import net.houselease.pojo.User;
import net.houselease.service.interfaces.UserService;
import net.houselease.staticData.Dictionary;

@Controller
@RequestMapping("")
public class UserController {

	@Resource
	private UserService userService;

//	@RequestMapping("/login")
//	public String userList() throws Exception {
//		return "redirect:/login.html";
//	}
	
//	@RequestMapping("/testLogin")
//	public String login() throws Exception {
//		return "/testLogin.html";
//	}

	@RequestMapping("/logout")
	public String logout(HttpSession httpSession) throws Exception {
		httpSession.removeAttribute(Dictionary.user);
		return "redirect:/login.html";
	}

	@RequestMapping("/logincheck")
	public String login(SimpleUser user, @RequestBody String param, Model model, HttpSession httpSession,
			HttpServletResponse response) throws Exception {
		boolean jsonOn = false;
		if (user.getUsername() == null) {
			user = JSONObject.parseObject(URLDecoder.decode(param, "utf8"), SimpleUser.class);
			jsonOn = true;
		}
		User resultUser = userService.login(user);
		boolean status = false;
		if (resultUser != null) {
			httpSession.setAttribute(Dictionary.user, resultUser);
			status = true;
		}

		if (jsonOn) {
			JSONObject result = new JSONObject();
			result.put("status", status);
			response.setContentType("application/json");
			// response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(result.toJSONString());
			out.flush();
			out.close();
			return null;
		} else {
			if (status) {
				return "redirect:/center";
			} else {
				String error = "error";
				model.addAttribute("error", error);
				return "redirect:/login.html";
			}
		}
	}

	@RequestMapping("/toindex")
	public String toindex(Model model) throws Exception {
		return "admin/index";
	}
}
