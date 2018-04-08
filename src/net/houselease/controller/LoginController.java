package net.houselease.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import net.houselease.controller.io.JSONHelper;
import net.houselease.pojo.SimpleUser;
import net.houselease.pojo.User;
import net.houselease.service.interfaces.UserService;
import net.houselease.staticData.Dictionary;

@Controller
@RequestMapping("")
public class LoginController {

	@Resource
	private UserService userService;

//	@RequestMapping(path = "/login", method = RequestMethod.GET)
//	public String redirectLogin() {
//		return Dictionary.LOGIN_VIEW;
//	}

	@RequestMapping("/logout")
	public String logout(HttpSession httpSession) throws Exception {
		httpSession.removeAttribute(Dictionary.USER_FIELD);
		return Dictionary.LOGIN_VIEW;
	}

	@RequestMapping(path = "/logincheck", method = RequestMethod.POST)
	public String login(SimpleUser user, @RequestBody String param, Model model, HttpSession httpSession,
			HttpServletResponse response) throws Exception {
		boolean jsonOn = false;
		if (user.getUsername() == null) {
			user = JSONObject.parseObject(URLDecoder.decode(param, "utf8"), SimpleUser.class);
			jsonOn = true;
		}
		User resultUser = userService.login(user);
		return loginTransaction(resultUser, httpSession, response, model, jsonOn);
	}

	@RequestMapping("/signupCheck")
	public void signupCheck(String username, @RequestBody String param, HttpSession httpSession,
			HttpServletResponse response) throws Exception {
		if (username == null) {
			username = (String) ((JSONObject) JSONObject.parseObject(URLDecoder.decode(param, "utf8")))
					.get(Dictionary.USERNAME_FIELD);
		}

		JSONHelper.sendJSONBoolean(userService.signupCheck(username), response);
	}

	@RequestMapping("/signup")
	public String signup(SimpleUser user, @RequestBody String param, Model model, HttpSession httpSession,
			HttpServletResponse response) throws Exception {
		boolean jsonOn = false;
		if (user.getUsername() == null) {
			user = JSONObject.parseObject(URLDecoder.decode(param, "utf8"), SimpleUser.class);
			jsonOn = true;
		}
		User resultUser = userService.signup(new User(user, Dictionary.RENTER_ROLE));
		return loginTransaction(resultUser, httpSession, response, model, jsonOn);
	}

	@RequestMapping("/toindex")
	public String toindex(Model model) throws Exception {
		return "redirect:/centerHome.html";
	}

	private static final String CENTER_VIEW = "redirect:" + Dictionary.CENTER_VIEW;

	private static String loginTransaction(User resultUser, HttpSession httpSession, HttpServletResponse response,
			Model model, boolean jsonOn) throws IOException {
		boolean status = false;
		if (resultUser != null) {
			httpSession.setAttribute(Dictionary.USER_FIELD, resultUser);
			status = true;
		}

		if (jsonOn) {
			JSONObject result = new JSONObject();
			result.put("status", status);
			result.put("username", resultUser.getUsername());
			JSONHelper.sendJSON(result, response);
			return null;
		} else {
			if (status) {
				return CENTER_VIEW;
			} else {
				String error = "error";
				model.addAttribute("error", error);
				return Dictionary.LOGIN_VIEW;
			}
		}
	}
}
