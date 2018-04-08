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

import net.houselease.pojo.Apply;
import net.houselease.pojo.Houselist;
import net.houselease.pojo.User;
import net.houselease.pojo.Userlist;
import net.houselease.pojo.Zulist;
import net.houselease.service.interfaces.ApplyService;
import net.houselease.service.interfaces.HouselistService;
import net.houselease.service.interfaces.UserlistService;
import net.houselease.staticData.Dictionary;

@Controller
@RequestMapping("")
public class RentRequireController {

	@Resource
	private UserlistService userlistService;

	@Resource
	private HouselistService houselistService;

	@Resource
	private ApplyService applyService;

	// 申请看房
	@RequestMapping("/applycheckuserlist")
	public String applycheckuserlist(HttpSession httpSession, Model model, Integer id) {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		Integer user_id = user1.getId();
		Userlist list = userlistService.findhasuserlist(user_id);
		if (list == null) {
			model.addAttribute("error", "applycheck");
			return "redirect:houselist.action";
		} else {
			Houselist houselist = houselistService.findid(id);
			houselist.setStatus("已被申请");
			houselistService.updatehousestatus(houselist);
			Integer userlist_id = list.getId();
			Apply apply = new Apply();
			apply.setHouse_id(houselist.getHouseid());
			apply.setAddress(houselist.getAddress());
			apply.setPrice(houselist.getPrice());
			apply.setArea(houselist.getArea());
			apply.setStatus("申请中");
			apply.setUserlist_id(userlist_id);
			applyService.insertapply(apply);
			model.addAttribute("error", "applysuccess");
			return "redirect:houselist.action";

		}

	}

	// 管理员查看申请看房列表
	@RequestMapping("/findapplylist")
	public String findapplylist(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
		PageHelper.startPage(page, pageSize);
		List<?> applylist = applyService.findapplylist();
		PageInfo<?> p = new PageInfo<>(applylist);
		model.addAttribute("applylist", applylist);
		model.addAttribute("p", p);
		// model.addAttribute("mainPage", "applylist.jsp");
		return Dictionary.ADMIN_FIELD + "/applylist";// Dictionary.MAIN_VIEW;
	}

	@RequestMapping("/applychangehousestatus")
	public String applychangehousestatus(HttpSession httpSession, Model model, String house_id) throws Exception {
		Houselist houselist = houselistService.findhouseid(house_id);
		houselist.setStatus("已租赁");
		houselistService.updatehousestatus(houselist);
		Zulist zulist = new Zulist();
		zulist.setHouse_id(house_id);
		zulist.setPrice(houselist.getPrice());
		zulist.setAddress(houselist.getAddress());
		return "";
	}

	// 管理员拒绝看房申请
	@RequestMapping("/refuseapply")
	public String refuseapply(String house_id, Model model) {
		Houselist houselist = new Houselist();
		houselist.setHouseid(house_id);
		houselist.setStatus("未租赁");
		applyService.refuseapply(houselist);

		return "redirect:findapplylist.action";
	}

	// 租客查看自己的 看房申请
	@RequestMapping("/getmyapply")
	public String getmyapply(Model model, HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		PageHelper.startPage(page, pageSize);
		Userlist userlist = userlistService.findhasuserlist(user1.getId());
		if (userlist != null) {
			List<?> list = userlistService.getmyapply(userlist.getId());
			if (list != null) {
				PageInfo<?> p = new PageInfo<>(list);
				model.addAttribute("userlist", list);
				model.addAttribute("p", p);
			}
		}
		// model.addAttribute("mainPage", "myapply.jsp");
		return Dictionary.RENTER_FIELD + "/myapply";// Dictionary.MAIN_VIEW;
	}

}
