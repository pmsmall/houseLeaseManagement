package net.houselease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.houselease.dao.PaidMapper;
import net.houselease.dao.TopaidMapper;
import net.houselease.pojo.Paid;
import net.houselease.pojo.QueryVo;
import net.houselease.pojo.Topaid;
import net.houselease.service.interfaces.TopaidService;

@Service("topaidService")
@Transactional
public class TopaidServiceImpl implements TopaidService {
	@Autowired
	private TopaidMapper topaidMapper;
	@Autowired
	private PaidMapper paidMapper;

	@Override
	public void inserttopaid(Topaid topaid) {
		topaid.setStatus("租金未缴");
		topaidMapper.inserttopaid(topaid);
	}

	@Override
	public List<Topaid> findtopaid(QueryVo vo) {
		List<Topaid> list = topaidMapper.findtopaid(vo);
		return list;
	}

	@Override
	public Topaid findbyid(Integer id) {
		Topaid topaid = topaidMapper.findbyid(id);
		return topaid;
	}

	@Override
	public void gotopay(Integer id, Paid paid) {
		paidMapper.insertpaid(paid);
		topaidMapper.deletetopaid(id);

	}

}
