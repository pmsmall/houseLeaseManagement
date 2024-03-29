package net.houselease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.houselease.dao.ApplyMapper;
import net.houselease.dao.HouselistMapper;
import net.houselease.pojo.Apply;
import net.houselease.pojo.Houselist;
import net.houselease.service.interfaces.ApplyService;

@Service("applyService")
@Transactional
public class ApplyServiceImpl implements ApplyService {

	@Autowired
	private ApplyMapper applyMapper;
	@Autowired
	private HouselistMapper houselistMapper;

	@Override
	public void insertapply(Apply apply) {
		applyMapper.insertapply(apply);

	}

	@Override
	public List<Apply> findapplylist() throws Exception {
		List<Apply> apply = applyMapper.findapplylist();
		return apply;
	}

	@Override
	public Apply findbyhouse_id(String house_id) {
		Apply apply = applyMapper.findbyhouse_id(house_id);
		return apply;
	}

	@Override
	public void deletebyhouse_id(String house_id) {
		applyMapper.deletebyhouse_id(house_id);

	}

	@Override
	public void refuseapply(Houselist houselist) {
		houselistMapper.updatestatus(houselist);
		applyMapper.deletebyhouse_id(houselist.getHouseid());
	}

}
