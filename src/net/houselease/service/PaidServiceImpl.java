package net.houselease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.houselease.dao.PaidMapper;
import net.houselease.dao.ZulistMapper;
import net.houselease.pojo.Paid;
import net.houselease.pojo.QueryVo;
import net.houselease.pojo.Zulist;
import net.houselease.service.interfaces.PaidService;

@Service("paidService")
@Transactional
public class PaidServiceImpl implements PaidService {
	@Autowired
	private PaidMapper paidMapper;
	@Autowired
	private ZulistMapper zulistMapper;

	@Override
	public List<Paid> selectall(QueryVo vo) {
		List<Paid> list = (paidMapper.selectall(vo));
		return list;
	}

	@Override
	public Double selectsum(QueryVo vo) {
		Double sum = paidMapper.selectsum(vo);
		return sum;
	}

	@Override
	public void deletepaid(Integer id) {
		paidMapper.deletepaid(id);

	}

	@Override
	public List<Zulist> findzuuserlist() throws Exception {
		List<Zulist> list = zulistMapper.findzuuserlist();
		return list;
	}

	@Override
	public Zulist findzukezulist(Integer id) {
		Zulist zulist = zulistMapper.findzukezulist(id);
		return zulist;
	}

}
