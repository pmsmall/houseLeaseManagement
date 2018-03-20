package net.houselease.service;

import java.util.List;

import net.houselease.pojo.Houselist;
import net.houselease.pojo.QueryVo;

public interface HouselistService {
	List<Houselist> selectAll();
	Houselist findhouseid(String houseid);
	void inserthouse(Houselist houselist);
	void deletehouse(int id);
	Houselist findid(int id);
	Houselist findhouseidupdate(Houselist houselist);
	void updatehouse(Houselist houselist);
	void updatehousestatus(Houselist houselist);
	public void deletehousebyhouseid(String house_id);
}
