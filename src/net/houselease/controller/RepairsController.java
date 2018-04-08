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

import net.houselease.pojo.QueryVo;
import net.houselease.pojo.Solve;
import net.houselease.pojo.User;
import net.houselease.pojo.Userlist;
import net.houselease.pojo.Wrong;
import net.houselease.pojo.Zulist;
import net.houselease.service.interfaces.PaidService;
import net.houselease.service.interfaces.SolveService;
import net.houselease.service.interfaces.UserlistService;
import net.houselease.service.interfaces.ZulistService;
import net.houselease.staticData.Dictionary;

@Controller
@RequestMapping("/wrong")
public class RepairsController {
	@Resource
	private SolveService solveService;

	@Resource
	private UserlistService userlistService;

	@Resource
	private PaidService paidService;

	@Resource
	private ZulistService zulistService;

	// 管理员查找所有已处理的报障
	@RequestMapping("/selectall")
	public String selectall(Model model, QueryVo vo, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<?> list = solveService.selectall(vo);
		PageInfo<?> p = new PageInfo<>(list);
		Integer count = solveService.selectcount(vo);
		model.addAttribute("solve", list);
		model.addAttribute("count", count);
		model.addAttribute("p", p);
		// model.addAttribute("mainPage", "solve.jsp");
		model.addAttribute("vo", vo);
		return Dictionary.ADMIN_FIELD + "/solve";// Dictionary.MAIN_VIEW;
	}

	// 租客查找自己已处理的报障
	@RequestMapping("/findmysolve")
	public String findmysolve(HttpSession httpSession, Model model, QueryVo vo,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		Userlist userlist = userlistService.findhasuserlist(user1.getId());
		PageHelper.startPage(page, pageSize);
		if (userlist != null) {
			vo.setUserlist_id(userlist.getId());
			List<?> list = solveService.selectall(vo);
			if (list != null) {
				PageInfo<?> p = new PageInfo<>(list);
				Integer count = solveService.selectcount(vo);
				model.addAttribute("solve", list);
				model.addAttribute("count", count);
				model.addAttribute("p", p);
			}
			model.addAttribute("vo", vo);
		}
		// model.addAttribute("mainPage", "mysolve.jsp");

		return Dictionary.RENTER_FIELD + "/mysolve";// Dictionary.MAIN_VIEW;
	}

	// 管理员删除已处理报障记录
	@RequestMapping("/deletesolve")
	public String deletesolve(Integer id) {
		solveService.deletesolve(id);
		return "redirect:selectall.action";
	}

	// zuke删除自己的已处理报障记录
	@RequestMapping("/zukedeletesolve")
	public String zukedeletesolve(Integer id) {
		solveService.deletesolve(id);
		return "redirect:findmypaid.action";
	}

	// 租客跳到我要报障页面
	@RequestMapping("/showaddwrong")
	public String showaddwrong(HttpSession httpSession, Model model,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) throws Exception {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		Userlist userlist = userlistService.findhasuserlist(user1.getId());
		PageHelper.startPage(page, pageSize);
		if (userlist != null) {
			List<?> list = zulistService.findzulistbyuid(userlist.getId());
			if (list != null) {
				PageInfo<?> p = new PageInfo<>(list);
				model.addAttribute("zulist", list);
				model.addAttribute("p", p);
			}
		}
		// model.addAttribute("mainPage", "showaddwrong.jsp");
		return Dictionary.RENTER_FIELD + "/showaddwrong";// Dictionary.MAIN_VIEW;
	}

	// 点击报障后跳转到添加报障信息页面
	@RequestMapping("/addwrong")
	public String addwrong(Integer id, Model model) {
		Zulist zulist = paidService.findzukezulist(id);
		model.addAttribute("zulist", zulist);
		// model.addAttribute("mainPage", "addwrong.jsp");
		return Dictionary.RENTER_FIELD + "/addwrong";// Dictionary.MAIN_VIEW;
	}

	// 添加报障信息到wrong表
	@RequestMapping("/insertwrong")
	public String insertwrong(Wrong wrong, Model model) {
		solveService.insertwrong(wrong);
		model.addAttribute("error", "insertwrong");

		return "redirect:showaddwrong.action";
	}

	// 管理员查看所有未处理报障
	@RequestMapping("/wronglist")
	public String wronglist(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		QueryVo vo = new QueryVo();
		PageHelper.startPage(page, pageSize);
		List<?> list = solveService.findwrong(vo);
		PageInfo<?> p = new PageInfo<>(list);
		model.addAttribute("wrong", list);
		model.addAttribute("p", p);
		// model.addAttribute("mainPage", "wrong.jsp");
		return Dictionary.ADMIN_FIELD + "/wrong";// Dictionary.MAIN_VIEW;
	}

	// 租客查看自己的未处理报障
	@RequestMapping("/mywronglist")
	public String mywronglist(Model model, HttpSession httpSession,
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		User user1 = (User) httpSession.getAttribute(Dictionary.USER_FIELD);
		Userlist userlist = userlistService.findhasuserlist(user1.getId());
		QueryVo vo = new QueryVo();
		PageHelper.startPage(page, pageSize);
		if (userlist != null) {
			vo.setUserlist_id(userlist.getId());
			if (vo != null) {
				List<?> list = solveService.findwrong(vo);
				if (list != null) {
					PageInfo<?> p = new PageInfo<>(list);
					model.addAttribute("p", p);
					model.addAttribute("wrong", list);
				}
			}
		}
		// model.addAttribute("mainPage", "mywrong.jsp");
		return Dictionary.RENTER_FIELD + "/mywrong";// Dictionary.MAIN_VIEW;
	}

	// 管理员处理报障
	@RequestMapping("/gotosolve")
	public String gotosolve(Integer id, Model model) {

		Wrong wrong = solveService.findbyid(id);
		Solve solve = new Solve();
		solve.setHouse_id(wrong.getHouse_id());
		solve.setAddress(wrong.getAddress());
		solve.setDate(wrong.getDate());
		solve.setDetail(wrong.getDetail());
		solve.setName(wrong.getName());
		solve.setUserlist_id(wrong.getUserlist_id());
		solve.setStatus("已处理");
		solveService.gotosolve(id, solve);
		model.addAttribute("error", "duesucess");
		return "redirect:selectall.action";
	}
}
