package net.houselease.service.interfaces;

import java.util.List;

import net.houselease.pojo.Paid;
import net.houselease.pojo.QueryVo;
import net.houselease.pojo.Zulist;

public interface PaidService {
	public List<Paid> selectall(QueryVo vo);

	public Double selectsum(QueryVo vo);

	public void deletepaid(Integer id);

	public List<Zulist> findzuuserlist() throws Exception;

	public Zulist findzukezulist(Integer id);

}
