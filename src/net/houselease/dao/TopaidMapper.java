package net.houselease.dao;

import java.util.List;

import net.houselease.pojo.QueryVo;
import net.houselease.pojo.Topaid;

public interface TopaidMapper {
	public void inserttopaid(Topaid topaid);

	public List<Topaid> findtopaid(QueryVo vo);

	public Topaid findbyid(Integer id);

	public void deletetopaid(Integer id);
}
