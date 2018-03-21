package net.houselease.service.interfaces;

import java.util.List;

import net.houselease.pojo.Applyout;
import net.houselease.pojo.Zulist;

public interface ApplyoutService {
	public void insertapplyout(Zulist zulist);

	List<Applyout> findallapplyout();

	public void updateapplyout(Applyout applyout);

	public void agreeapplyout(Integer id);

	public void deleteapplyout(Integer id);
}
