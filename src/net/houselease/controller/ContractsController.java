package net.houselease.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.houselease.pojo.Apply;
import net.houselease.pojo.Checkout;
import net.houselease.pojo.Hetong;
import net.houselease.pojo.Houselist;
import net.houselease.pojo.Zulist;
import net.houselease.service.interfaces.ApplyService;
import net.houselease.service.interfaces.CheckoutService;
import net.houselease.service.interfaces.HetongService;
import net.houselease.service.interfaces.HouselistService;
import net.houselease.service.interfaces.ZulistService;
import net.houselease.staticData.Dictionary;

@Controller
@RequestMapping("/hetong")
public class ContractsController {
	@Resource
	private HetongService hetongService;

	@Resource
	private HouselistService houselistService;

	@Resource
	private ApplyService applyService;

	@Resource
	private ZulistService zulistService;

	@Resource
	private CheckoutService checkoutService;

	// 新增合同信息，修改房屋列表的状态，从申请列表中删除，增添到租赁列表当中
	@RequestMapping("/inserthetong")
	public String inserthetong(Model model, Hetong hetong) {
		// 新增合同信息
		hetongService.inserthetong(hetong);
		Hetong hetong1 = hetongService.findhetong(hetong.getHouse_id());
		// 修改房屋列表状态
		Houselist houselist = houselistService.findhouseid(hetong1.getHouse_id());
		houselist.setStatus("已租赁");
		houselistService.updatehousestatus(houselist);
		// 添加到租赁列表当中
		Zulist zulist = new Zulist();
		Apply apply = applyService.findbyhouse_id(hetong.getHouse_id());
		zulist.setHouse_id(hetong.getHouse_id());
		zulist.setUserlist_id(apply.getUserlist_id());
		zulist.setContract_id(hetong1.getId());
		zulist.setPrice(apply.getPrice());
		zulist.setAddress(apply.getAddress());
		zulistService.insertzulist(zulist);
		// 从申请列表中删除
		applyService.deletebyhouse_id(hetong1.getHouse_id());
		model.addAttribute("error", "zusuccess");
		return "redirect:/zulist/findzulist.action";

	}

	@RequestMapping("/seehetong")
	public String seehetong(String house_id, Model model) {
		Hetong hetong = hetongService.findhetong(house_id);
		model.addAttribute("hetong", hetong);
		// model.addAttribute("mainPage", "hetong.jsp");
		return Dictionary.ADMIN_FIELD + "/hetong";// Dictionary.MAIN_VIEW;
	}

	@RequestMapping("/updatehetong")
	public String updatehetong(String house_id, Model model) {
		Hetong hetong = hetongService.findhetong(house_id);
		model.addAttribute("hetong", hetong);
		// model.addAttribute("mainPage", "updatehetong.jsp");
		return Dictionary.ADMIN_FIELD + "/updatehetong";// Dictionary.MAIN_VIEW;
	}

	@RequestMapping("/changehetong")
	public String changehetong(Hetong hetong) {
		hetongService.updatehetong(hetong);

		return "redirect:/zulist/findzulist.action";
	}
	// 终止合同操作：删除合同，插入已退租列表，删除在租列表，删除房屋列表

	@RequestMapping("/deletehetong")
	public String deletehetong(String house_id, Model model) {
		hetongService.deletehetong(house_id);
		Zulist zulist = zulistService.findzulist(house_id);
		Checkout checkout = new Checkout();
		checkout.setHouse_id(house_id);
		checkout.setAddress(zulist.getAddress());
		checkout.setStatus("已退租");
		checkout.setUserlist_id(zulist.getUserlist_id());
		checkoutService.insertcheckout(checkout);
		houselistService.deletehousebyhouseid(house_id);
		zulistService.deletezulist(house_id);

		model.addAttribute("error", "checkoutsuccess");
		return "redirect:/zulist/findzulist.action";
	}

	@RequestMapping("/zukeseehetong")
	public String zukeseehetong(String house_id, Model model) {
		Hetong hetong = hetongService.findhetong(house_id);
		model.addAttribute("hetong", hetong);
		// model.addAttribute("mainPage", "showhetong.jsp");
		return Dictionary.RENTER_FIELD + "/showhetong";// Dictionary.MAIN_VIEW;
	}
}
