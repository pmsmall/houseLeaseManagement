package net.houselease.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import net.houselease.pojo.Applyout;

@Service("apploutMapper")
public interface ApplyoutMapper {
	public void insertapplyout(Applyout applyout);

	List<Applyout> findallapplyout();

	public void updateapplyout(Applyout applyout);

	public void updateapplyoutbyhouse(Applyout applyout);

	public Applyout findbyid(Integer id);

	public void deleteapplyout(Integer id);
}
