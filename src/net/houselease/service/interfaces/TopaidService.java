package net.houselease.service.interfaces;

import java.util.List;

import net.houselease.pojo.Paid;
import net.houselease.pojo.QueryVo;
import net.houselease.pojo.Topaid;

public interface TopaidService {
	public void inserttopaid(Topaid topaid);

	public List<Topaid> findtopaid(QueryVo vo);

	public Topaid findbyid(Integer id);

	public void gotopay(Integer id, Paid paid);
}
