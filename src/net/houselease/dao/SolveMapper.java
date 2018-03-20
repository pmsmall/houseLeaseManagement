package net.houselease.dao;

import java.util.List;

import net.houselease.pojo.QueryVo;
import net.houselease.pojo.Solve;

public interface SolveMapper {
	public List<Solve> selectall(QueryVo vo);
	public Integer selectcount(QueryVo vo);
	public void deletesolve(Integer id);
	public void insertsolve(Solve solve);
}
