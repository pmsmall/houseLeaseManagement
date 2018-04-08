package net.houselease.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.houselease.pojo.User;
import net.houselease.pojo.Userlist;
import net.houselease.service.interfaces.UserlistService;
import net.houselease.staticData.Dictionary;

@Controller
public class UsersController {

	@Resource
	private UserlistService userlistService;

	@RequestMapping("/findhasuserlist")
	public String findhasuserlist(HttpSession httpSession, Model model) throws Exception {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		Integer user_id = user1.getId();
		Userlist userlist = userlistService.findhasuserlist(user_id);
		if (userlist != null) {
			model.addAttribute("userlist", userlist);
		}
		// model.addAttribute("mainPage", "updateuserlist.jsp");
		return Dictionary.RENTER_FIELD + "/updateuserlist";// Dictionary.MAIN_VIEW;

	}

	// 查找并更新用户信息
	@RequestMapping("/checkuserlist")
	public String checkuserlist(Model model, Userlist userlist, HttpSession httpSession) throws Exception {
		if (userlist.getId() == null) {
			String idcard = userlist.getIdcard();
			Userlist list = userlistService.checkuserlist(idcard);

			if (list != null) {
				model.addAttribute("error", "该身份证已被绑定,一个身份证号码只能被一个账户绑定！");
				// model.addAttribute("mainPage", "updateuserlist.jsp");
				model.addAttribute("userlist", userlist);
			} else {
				User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
				Integer user_id = user1.getId();
				userlist.setUser_id(user_id);
				userlistService.insertuserlist(userlist);
				Userlist list1 = userlistService.checkuserlist(idcard);
				model.addAttribute("error", "资料完善成功");
				// model.addAttribute("mainPage", "updateuserlist.jsp");
				model.addAttribute("userlist", list1);
			}

		} else {

			Userlist list = userlistService.finduserlistupdate(userlist);
			if (list != null) {
				model.addAttribute("error", "该身份证号码已被绑定");
				// model.addAttribute("mainPage", "updateuserlist.jsp");
				model.addAttribute("userlist", userlist);
			} else {
				userlistService.updateuserlist(userlist);
				model.addAttribute("error", "更新成功");
				// model.addAttribute("mainPage", "updateuserlist.jsp");
				model.addAttribute("userlist", userlist);
			}

		}
		return Dictionary.RENTER_FIELD + "/updateuserlist";// Dictionary.MAIN_VIEW;
	}

	@RequestMapping("/findalluserlist")
	public String findalluserlist(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<?> userlist = userlistService.findalluserlist();
		PageInfo<?> p = new PageInfo<>(userlist);
		model.addAttribute("userlist", userlist);
		model.addAttribute("p", p);
		// model.addAttribute("mainPage", "userlist.jsp");
		return Dictionary.ADMIN_FIELD + "/userlist";// Dictionary.MAIN_VIEW;

	}

	// 删除用户信息
	@RequestMapping("/deleteuserlist")
	public String deleteuserlist(Model model, Integer id) {
		userlistService.deleteuserlist(id);
		model.addAttribute("error", "deletesuccess");
		return "redirect:findalluserlist.action";
	}
}
