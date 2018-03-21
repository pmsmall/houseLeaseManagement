package net.houselease.service.interfaces;

import java.util.List;

import net.houselease.pojo.Schedule;

public interface ScheduleService {
	public void insertschedule(Schedule schedule);

	public List<Schedule> selectAll();

	public void deleteschedule(Integer id);

	public void updateschedule(Schedule schedule);

	public Schedule selectbyid(Integer id);
}
