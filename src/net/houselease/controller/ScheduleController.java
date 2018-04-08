package net.houselease.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.houselease.pojo.Schedule;
import net.houselease.service.interfaces.ScheduleService;
import net.houselease.staticData.Dictionary;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	@Resource
	private ScheduleService scheduleService;

	@RequestMapping("/selectAll")
	public String selectAll(Model model, @RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "2") Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<?> schedule = scheduleService.selectAll();
		PageInfo<?> p = new PageInfo<>(schedule);
		model.addAttribute("schedule", schedule);
		model.addAttribute("p", p);
		// model.addAttribute("mainPage", "schedule.jsp");
		return Dictionary.ADMIN_FIELD + "/schedule";// Dictionary.MAIN_VIEW;
	}

	@RequestMapping("/deleteschedule")
	public String deleteschedule(Integer id) {
		scheduleService.deleteschedule(id);
		return "redirect:selectAll.action";
	}

	@RequestMapping("/insertschedule")
	public String insertschedule(Schedule schedule, Model model) {
		scheduleService.insertschedule(schedule);

		return "redirect:selectAll.action";

	}

	@RequestMapping("/updateschedule")
	public String updateschedule(Schedule schedule, Model model) {
		scheduleService.updateschedule(schedule);
		model.addAttribute("error", "更新成功");
		model.addAttribute("schedule", schedule);
		// model.addAttribute("mainPage", "updateschedule.jsp");
		return Dictionary.ADMIN_FIELD + "/updateschedule";// Dictionary.MAIN_VIEW;

	}

	@RequestMapping("/toinsert")
	public String toinsert(Model model) {
		// model.addAttribute("mainPage", "addschedule.jsp");
		return Dictionary.ADMIN_FIELD + "/addschedule";// Dictionary.MAIN_VIEW;

	}

	@RequestMapping("/toupdate")
	public String toupdate(Model model, Integer id) {
		Schedule schedule = scheduleService.selectbyid(id);
		model.addAttribute("schedule", schedule);

		// model.addAttribute("mainPage", "updateschedule.jsp");

		return Dictionary.ADMIN_FIELD + "/updateschedule";// Dictionary.MAIN_VIEW;

	}
}
