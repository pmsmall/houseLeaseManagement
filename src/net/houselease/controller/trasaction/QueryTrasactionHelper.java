package net.houselease.controller.trasaction;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import net.houselease.controller.io.JSONHelper;
import net.houselease.pojo.User;
import net.houselease.staticData.Dictionary;

public class QueryTrasactionHelper {

	public static void username(JSONObject json, HttpSession httpSession, HttpServletResponse response) throws IOException {
		User user = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		if (user != null) {
			json.put("username", user.getUsername());
			json.put("status", true);
		} else {
			json.put("status", false);
		}
		JSONHelper.sendJSON(json, response);
	}

}
