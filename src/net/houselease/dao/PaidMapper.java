package net.houselease.dao;

import java.util.List;

import net.houselease.pojo.Paid;
import net.houselease.pojo.QueryVo;

public interface PaidMapper {
	public List<Paid> selectall(QueryVo vo);

	public Double selectsum(QueryVo vo);

	public void deletepaid(Integer id);

	public void insertpaid(Paid paid);
}
