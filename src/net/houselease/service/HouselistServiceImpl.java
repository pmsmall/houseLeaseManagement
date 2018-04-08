package net.houselease.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import net.houselease.dao.HouselistMapper;
import net.houselease.pojo.Houselist;
import net.houselease.service.interfaces.HouselistService;

@Service("houselistService")
public class HouselistServiceImpl implements HouselistService {

	@Autowired
	private HouselistMapper houselistMapper;

	@Override
	public List<Houselist> selectAll() {
		List<Houselist> houselist = houselistMapper.selectAll();
		return houselist;
	}

	@Override
	public Houselist findhouseid(String houseid) {
		Houselist houselist = houselistMapper.findhouseid(houseid);
		return houselist;
	}

	@Override
	public void inserthouse(Houselist houselist) {
		houselistMapper.inserthouse(houselist);
	}

	@Override
	public void deletehouse(int id) {
		houselistMapper.deletehouse(id);
	}

	@Override
	public Houselist findhouseidupdate(Houselist houselist) {
		Houselist list = houselistMapper.findhouseidupdate(houselist);
		return list;
	}

	@Override
	public void updatehouse(Houselist houselist) {
		houselistMapper.updatehouse(houselist);

	}

	@Override
	public Houselist findid(int id) {
		Houselist list = houselistMapper.findid(id);
		return list;
	}

	@Override
	public void updatehousestatus(Houselist houselist) {
		houselistMapper.updatehousestatus(houselist);
	}

	@Override
	public void deletehousebyhouseid(String house_id) {
		houselistMapper.deletehousebyhouseid(house_id);

	}

}
