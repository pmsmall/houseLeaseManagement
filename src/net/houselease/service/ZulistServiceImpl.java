package net.houselease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.houselease.dao.ZulistMapper;
import net.houselease.pojo.Zulist;
import net.houselease.service.interfaces.ZulistService;

@Service("zulistService")
public class ZulistServiceImpl implements ZulistService {

	@Autowired
	private ZulistMapper zulistMapper;

	@Override
	public void insertzulist(Zulist zulist) {
		zulistMapper.insertzulist(zulist);

	}

	@Override
	public List<Zulist> findzuuserlist() throws Exception {
		List<Zulist> zulist = zulistMapper.findzuuserlist();

		return zulist;
	}

	@Override
	public Zulist findzulist(String house_id) {
		Zulist zulist = zulistMapper.findzulist(house_id);
		return zulist;
	}

	@Override
	public void deletezulist(String house_id) {
		zulistMapper.deletezulist(house_id);

	}

	@Override
	public List<Zulist> findzulistbyuid(Integer userlist_id) {
		List<Zulist> zulist = zulistMapper.findzulistbyuid(userlist_id);
		return zulist;
	}

}
