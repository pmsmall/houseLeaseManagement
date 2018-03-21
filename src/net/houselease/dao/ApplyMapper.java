package net.houselease.dao;

import java.util.List;

import net.houselease.pojo.Apply;
import net.houselease.pojo.Applyout;

public interface ApplyMapper {
	void insertapply(Apply apply);

	public List<Apply> findapplylist() throws Exception;

	Apply findbyhouse_id(String house_id);

	public void deletebyhouse_id(String house_id);

	public void updateapplyout(Applyout applyout);
}
