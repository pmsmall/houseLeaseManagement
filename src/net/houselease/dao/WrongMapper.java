package net.houselease.dao;

import java.util.List;

import net.houselease.pojo.QueryVo;
import net.houselease.pojo.Wrong;

public interface WrongMapper {
	public List<Wrong> findwrong(QueryVo vo);

	public Wrong findbyid(Integer id);

	public void insertwrong(Wrong wrong);

	public void deletewrong(Integer id);
}
