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

import net.houselease.pojo.Hetong;
import net.houselease.pojo.User;
import net.houselease.pojo.Userlist;
import net.houselease.service.interfaces.UserlistService;
import net.houselease.service.interfaces.ZulistService;
import net.houselease.staticData.Dictionary;

@Controller
@RequestMapping("/zulist")
public class RentReplyController {
	@Resource
	private ZulistService zulistService;

	@Resource
	private UserlistService userlistService;

	// 跳到增添合同的页面
	@RequestMapping("/toaddhetong")
	public String toaddhetong(Model model, String house_id) {
		Hetong hetong = new Hetong();
		hetong.setHouse_id(house_id);
		model.addAttribute("hetong", hetong);
		// model.addAttribute("mainPage", "addhetong.jsp");

		return Dictionary.ADMIN_FIELD + "/addhetong";// Dictionary.MAIN_VIEW;
	}

	// 管理员查看所有在租列表
	@RequestMapping("/findzulist")
	public String findzulist(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
		PageHelper.startPage(page, pageSize);
		List<?> zulist = zulistService.findzuuserlist();
		PageInfo<?> p = new PageInfo<>(zulist);
		model.addAttribute("p", p);
		model.addAttribute("zulist", zulist);
		// model.addAttribute("mainPage", "zulist.jsp");
		return Dictionary.ADMIN_FIELD + "/zulist";// Dictionary.MAIN_VIEW;
	}

	// 查看我的在租列表
	@RequestMapping("/myzulist")
	public String myzulist(Model model, HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		if (user1 == null)
			return "direct:/login";
		Userlist userlist = userlistService.findhasuserlist(user1.getId());
		if (userlist != null) {
			PageHelper.startPage(page, pageSize);
			List<?> list = userlistService.getUserzuList(userlist.getId());
			if (list != null) {
				PageInfo<?> p = new PageInfo<>(list);
				model.addAttribute("userlistzu", list);
				model.addAttribute("p", p);
			}
		}
		// model.addAttribute("mainPage", "myzulist.jsp");
		return Dictionary.RENTER_FIELD + "/myzulist";// Dictionary.MAIN_VIEW;
	}

}
