
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

import net.houselease.pojo.Paid;
import net.houselease.pojo.QueryVo;
import net.houselease.pojo.Topaid;
import net.houselease.pojo.User;
import net.houselease.pojo.Userlist;
import net.houselease.pojo.Zulist;
import net.houselease.service.interfaces.PaidService;
import net.houselease.service.interfaces.TopaidService;
import net.houselease.service.interfaces.UserlistService;
import net.houselease.staticData.Dictionary;

import java.text.SimpleDateFormat;
import java.util.Date;;

@Controller
@RequestMapping("/paid")
public class PaidController {
	@Resource
	private PaidService paidService;

	@Resource
	private TopaidService topaidService;

	@Resource
	private UserlistService userlistService;

	// 管理员查找所有已缴租金列表
	@RequestMapping("/selectall")
	public String selectall(Model model, QueryVo vo, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<?> list = paidService.selectall(vo);
		PageInfo<?> p = new PageInfo<>(list);
		Double sum = paidService.selectsum(vo);
		model.addAttribute("paid", list);
		model.addAttribute("sum", sum);
		model.addAttribute("p", p);
		// model.addAttribute("mainPage", "paid.jsp");
		model.addAttribute("vo", vo);
		return Dictionary.ADMIN_FIELD + "/paid";// Dictionary.MAIN_VIEW;
	}

	// 租客查找自己已缴租金列表
	@RequestMapping("/findmypaid")
	public String findmypaid(HttpSession httpSession, Model model, QueryVo vo,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		Userlist userlist = userlistService.findhasuserlist(user1.getId());
		if (userlist != null) {
			vo.setUserlist_id(userlist.getId());
			PageHelper.startPage(page, pageSize);
			List<?> list = paidService.selectall(vo);
			PageInfo<?> p = new PageInfo<>(list);
			Double sum = paidService.selectsum(vo);
			model.addAttribute("paid", list);
			model.addAttribute("sum", sum);
			model.addAttribute("p", p);
			model.addAttribute("vo", vo);
		}
		// model.addAttribute("mainPage", "mypaid.jsp");
		return Dictionary.RENTER_FIELD + "/mypaid";// Dictionary.MAIN_VIEW;
	}

	// 管理员删除已缴租金记录
	@RequestMapping("/deletepaid")
	public String deletepaid(Integer id) {
		paidService.deletepaid(id);
		return "redirect:selectall.action";
	}

	// zuke删除已缴租金记录
	@RequestMapping("/zukedeletepaid")
	public String zukedeletepaid(Integer id) {
		paidService.deletepaid(id);
		return "redirect:findmypaid.action";
	}

	// 跳到我要收租页面
	@RequestMapping("/showaddpaid")
	public String showaddpaid(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
		PageHelper.startPage(page, pageSize);
		List<?> list = paidService.findzuuserlist();
		PageInfo<?> p = new PageInfo<>(list);
		model.addAttribute("zulist", list);
		model.addAttribute("p", p);
		// model.addAttribute("mainPage", "showaddpaid.jsp");
		return Dictionary.ADMIN_FIELD + "/showaddpaid";// Dictionary.MAIN_VIEW;
	}

	// 点击收租后跳转到添加租金信息页面
	@RequestMapping("/addpaid")
	public String addpaid(Integer id, Model model) {
		Zulist zulist = paidService.findzukezulist(id);
		model.addAttribute("zulist", zulist);
		// model.addAttribute("mainPage", "addpaid.jsp");
		return Dictionary.ADMIN_FIELD + "/addpaid";// Dictionary.MAIN_VIEW;
	}

	// 添加租金信息到topaid表
	@RequestMapping("/inserttopaid")
	public String inserttopaid(Topaid topaid, Model model) {
		topaidService.inserttopaid(topaid);
		model.addAttribute("error", "inserttopaid");
		return "redirect:showaddpaid.action";
	}

	// 管理员查看所有未缴租金信息
	@RequestMapping("/topaidlist")
	public String topaidlist(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		QueryVo vo = new QueryVo();
		PageHelper.startPage(page, pageSize);
		List<?> list = topaidService.findtopaid(vo);
		PageInfo<?> p = new PageInfo<>(list);
		model.addAttribute("topaid", list);
		model.addAttribute("p", p);
		// model.addAttribute("mainPage", "topaid.jsp");
		return Dictionary.ADMIN_FIELD + "/topaid";// Dictionary.MAIN_VIEW;
	}

	// 租客查看自己的未缴租金
	@RequestMapping("/mytopaidlist")
	public String mytopaidlist(Model model, HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		Userlist userlist = userlistService.findhasuserlist(user1.getId());
		PageHelper.startPage(page, pageSize);
		if (userlist != null) {
			QueryVo vo = new QueryVo();
			vo.setUserlist_id(userlist.getId());
			List<?> topaid = topaidService.findtopaid(vo);
			PageInfo<?> p = new PageInfo<>(topaid);
			model.addAttribute("p", p);
			model.addAttribute("topaid", topaid);
		}
		// model.addAttribute("mainPage", "mytopaid.jsp");
		return Dictionary.RENTER_FIELD + "/mytopaid";// Dictionary.MAIN_VIEW;
	}

	// 租客进行支付操作
	@RequestMapping("/gotopay")
	public String gotopay(Integer id, Model model) {
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		String paydate = matter1.format(dt);
		Topaid topaid = topaidService.findbyid(id);
		Paid paid = new Paid();
		paid.setHouse_id(topaid.getHouse_id());
		paid.setAddress(topaid.getAddress());
		paid.setPrice(topaid.getPrice());
		paid.setDate(topaid.getDate());
		paid.setPaydate(paydate);
		paid.setName(topaid.getName());
		paid.setUserlist_id(topaid.getUserlist_id());
		paid.setStatus("租金已缴");
		topaidService.gotopay(id, paid);
		model.addAttribute("error", "paysucess");
		return "redirect:findmypaid.action";
	}
}
