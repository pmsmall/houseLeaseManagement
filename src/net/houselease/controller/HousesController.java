package net.houselease.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.houselease.pojo.Houselist;
import net.houselease.service.interfaces.HouselistService;
import net.houselease.staticData.Dictionary;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller

public class HousesController {
	@Resource
	private HouselistService houselistService;

	@RequestMapping("/houselist")
	public String houselist(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize, HttpSession httpSession) {
		PageHelper.startPage(page, pageSize);
		List<?> houselist = houselistService.selectAll();
		PageInfo<?> p = new PageInfo<>(houselist);

		model.addAttribute("p", p);
		model.addAttribute("houselist", houselist);
		// model.addAttribute("mainPage", "houselist.jsp");
		return Dictionary.RENTER_FIELD + "/houselist";
	}

	@RequestMapping("/ahouselist")
	public String ahouselist(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {

		PageHelper.startPage(page, pageSize);
		List<?> houselist = houselistService.selectAll();
		PageInfo<?> p = new PageInfo<>(houselist);

		model.addAttribute("p", p);
		model.addAttribute("houselist", houselist);
		// model.addAttribute("mainPage", "ahouselist.jsp");
		return Dictionary.ADMIN_FIELD + "/ahouselist";// Dictionary.MAIN_VIEW;
	}

	@RequestMapping("/addhouse")
	public String addhouse(Model model, Houselist houselist) {

		String houseid = houselist.getHouseid();
		Houselist houselist1 = houselistService.findhouseid(houseid);
		if (houselist1 != null) {
			model.addAttribute("error", "该房屋id已存在");
			model.addAttribute("houselist", houselist);
			// model.addAttribute("mainPage", "addhouse.jsp");
			return Dictionary.ADMIN_FIELD + "/addhouse";// Dictionary.MAIN_VIEW;
		} else {
			model.addAttribute("error", "添加成功");
			houselistService.inserthouse(houselist);
			model.addAttribute("houselist", houselist);
			// model.addAttribute("mainPage", "addhouse.jsp");
			return Dictionary.ADMIN_FIELD + "/addhouse";// Dictionary.MAIN_VIEW;
		}
	}

	@RequestMapping("/toaddhouse")
	public String toaddhoust(Model model) {
		// model.addAttribute("mainPage", "addhouse.jsp");

		return Dictionary.ADMIN_FIELD + "/addhouse";// Dictionary.MAIN_VIEW;
	}

	@RequestMapping("/deletehouse")
	public String deletehouse(Integer id) {
		houselistService.deletehouse(id);

		return "redirect:ahouselist.action";
	}

	@RequestMapping("/toahouselist")
	public String toahouselist() {

		return "ahouselist.action";
	}

	@RequestMapping("/findid")
	public String findid(Integer id, Model model) {
		Houselist list = houselistService.findid(id);
		model.addAttribute("houselist", list);
		// model.addAttribute("mainPage", "changehouse.jsp");
		return Dictionary.ADMIN_FIELD + "/changehouse";// Dictionary.MAIN_VIEW;
	}

	@RequestMapping("/findhouseidupdate")
	public String findhouseidupdate(Houselist houselist, Model model) {
		Houselist list = houselistService.findhouseidupdate(houselist);
		if (list != null) {
			model.addAttribute("houselist", houselist);
			// model.addAttribute("mainPage", "changehouse.jsp");
			model.addAttribute("error", "该房屋id已存在");
			return Dictionary.ADMIN_FIELD + "/changehouse";// Dictionary.MAIN_VIEW;
		} else {
			houselistService.updatehouse(houselist);
			model.addAttribute("houselist", houselist);
			// model.addAttribute("mainPage", "changehouse.jsp");
			model.addAttribute("error", "更新成功");
			return Dictionary.ADMIN_FIELD + "/changehouse";// Dictionary.MAIN_VIEW;
		}
	}

}
