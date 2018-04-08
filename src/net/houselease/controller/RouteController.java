package net.houselease.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

import net.houselease.controller.io.JSONHelper;
import net.houselease.controller.trasaction.QueryTrasactionHelper;
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
				return "redirect:/" + Dictionary.RENTER_FIELD + Dictionary.MAIN_VIEW;
			} else if (user.getType().equals(Dictionary.ADMIN_ROLE)) {
				return "redirect:/" + Dictionary.ADMIN_FIELD + Dictionary.MAIN_VIEW;
			}
		}
		return Dictionary.LOGIN_VIEW;
	}

	@RequestMapping(value = "/query")
	public void query(@RequestBody String param, HttpSession httpSession, HttpServletResponse response)
			throws IOException {
		JSONObject json = JSONObject.parseObject(param);
		JSONObject result = new JSONObject();
		if (json == null) {
			result.put("status", false);
		} else {
			String remoteQuery = json.getString("query");
			if (remoteQuery == null) {
				result.put("status", false);
			} else {
				Method[] methods = QueryTrasactionHelper.class.getMethods();
				for (Method method : methods) {
					method.getName().equals(remoteQuery);
					try {
						method.invoke(null, result, httpSession, response);
						break;
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						LogManager.getLogger("queryLogger").log(Level.ERROR, "query method invoke failed!",
								(Throwable) e);
						result.put("status", false);
					}
				}
			}
		}
		JSONHelper.sendJSON(result, response);
	}
}
