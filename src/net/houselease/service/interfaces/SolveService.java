package net.houselease.service.interfaces;

import java.util.List;

import net.houselease.pojo.QueryVo;
import net.houselease.pojo.Solve;
import net.houselease.pojo.Wrong;

public interface SolveService {
	public List<Solve> selectall(QueryVo vo);

	public Integer selectcount(QueryVo vo);

	public void deletesolve(Integer id);

	public List<Wrong> findwrong(QueryVo vo);

	public Wrong findbyid(Integer id);

	public void insertwrong(Wrong wrong);

	public void gotosolve(Integer id, Solve solve);
}
