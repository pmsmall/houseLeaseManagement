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

import net.houselease.pojo.Applyout;
import net.houselease.pojo.User;
import net.houselease.pojo.Userlist;
import net.houselease.pojo.Zulist;
import net.houselease.service.interfaces.ApplyoutService;
import net.houselease.service.interfaces.UserlistService;
import net.houselease.service.interfaces.ZulistService;
import net.houselease.staticData.Dictionary;

@Controller
@RequestMapping("/applyout")
public class UnrentReplyController {
	@Resource
	private ZulistService zulistService;

	@Resource
	private ApplyoutService applyoutService;

	@Resource
	private UserlistService userlistService;

	// 插入退租信息
	@RequestMapping("/insertapplyout")
	public String insertapplyout(String house_id, Model model) {
		Zulist zulist = zulistService.findzulist(house_id);
		applyoutService.insertapplyout(zulist);
		model.addAttribute("error", "applysuccess");
		return "redirect:/zulist/myzulist.action";
	}

	// 查看退租申请
	@RequestMapping("/findallapplyout")
	public String findallapplyout(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<?> applyout = applyoutService.findallapplyout();
		PageInfo<?> p = new PageInfo<>(applyout);
		model.addAttribute("applyout", applyout);
		model.addAttribute("p", p);
		// model.addAttribute("mainPage", "applyout.jsp");
		return Dictionary.ADMIN_FIELD + "/applyout";// Dictionary.MAIN_VIEW;
	}

	// 管理员拒绝退租申请
	@RequestMapping("/refuseapplyout")
	public String refuseapplyout(Model model, Integer id) {
		Applyout applyout = new Applyout();
		applyout.setId(id);
		applyout.setStatus("已拒绝");
		applyoutService.updateapplyout(applyout);
		model.addAttribute("mainPage", "applyout.jsp");
		return "redirect:findallapplyout.action";
	}

	// 管理员同意退租申请
	@RequestMapping("/agreeapplyout")
	public String agreeapplyout(Model model, Integer id) {
		applyoutService.agreeapplyout(id);
		model.addAttribute("error", "applyoutsucess");
		return "redirect:findallapplyout.action";
	}

	// 删除申请退租列表
	@RequestMapping("/deleteapplyout")
	public String deleteapplyout(Model model, Integer id) {
		applyoutService.deleteapplyout(id);
		model.addAttribute("error", "deletesucess");
		return "redirect:findallapplyout.action";
	}

	// 租客查看自己的 退房申请
	@RequestMapping("/getmyapplyout")
	public String getmyapplyout(Model model, HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		Userlist userlist = userlistService.findhasuserlist(user1.getId());
		PageHelper.startPage(page, pageSize);
		if (userlist != null) {
			List<?> list = userlistService.getmyapplyout(userlist.getId());
			if (list != null) {
				PageInfo<?> p = new PageInfo<>(list);
				model.addAttribute("userlist", list);
				model.addAttribute("p", p);
			}
		}
		// model.addAttribute("mainPage", "myapplyout.jsp");
		return Dictionary.RENTER_FIELD + "/myapplyout";// Dictionary.MAIN_VIEW;
	}
}
