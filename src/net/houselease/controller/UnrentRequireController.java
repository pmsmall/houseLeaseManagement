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
import net.houselease.service.interfaces.CheckoutService;
import net.houselease.service.interfaces.UserlistService;
import net.houselease.staticData.Dictionary;

@Controller
@RequestMapping("/checkout")
public class UnrentRequireController {
	@Resource
	private CheckoutService checkoutService;

	@Resource
	private UserlistService userlistService;

	@RequestMapping("/getallcheckout")
	public String getallcheckout(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize, HttpSession httpSession) {
		PageHelper.startPage(page, pageSize);
		List<?> checkout = checkoutService.getallcheckout();
		PageInfo<?> p = new PageInfo<>(checkout);
		model.addAttribute("p", p);
		model.addAttribute("checkout", checkout);
		// model.addAttribute("mainPage", "checkout.jsp");
		return Dictionary.ADMIN_FIELD + "/checkout";// Dictionary.MAIN_VIEW;
	}

	// 租客删除自己已退租列表
	@RequestMapping("/deletecheckout")
	public String deletecheckout(Integer id) {
		checkoutService.deletecheckout(id);
		return "redirect:/checkout/getmycheckout.action";
	}

	// 租客删除自己已退租列表
	@RequestMapping("/admindeletecheckout")
	public String admindeletecheckout(Integer id) {
		checkoutService.deletecheckout(id);
		return "redirect:/checkout/getallcheckout.action";
	}

	@RequestMapping("/getmycheckout")
	public String getmycheckout(Model model, HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		PageHelper.startPage(page, pageSize);

		Userlist userlist = userlistService.findhasuserlist(user1.getId());
		if (userlist != null) {
			List<?> list = userlistService.getmycheckout(userlist.getId());
			if (list != null) {
				PageInfo<?> p = new PageInfo<>(list);
				model.addAttribute("p", p);
				model.addAttribute("userlistcheck", list);
			}
		}

		// model.addAttribute("mainPage", "mycheckout.jsp");
		return Dictionary.RENTER_FIELD + "/mycheckout";// Dictionary.MAIN_VIEW;
	}
}
