package net.houselease.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import net.houselease.pojo.SimpleUser;
import net.houselease.pojo.User;
import net.houselease.pojo.UserExample;

public interface UserMapper {
	int countByExample(UserExample example);

	int deleteByExample(UserExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(Integer id);

	User selectByUser(SimpleUser user);
	
	int selectByUsername(String username);

	int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

	int updateByExample(@Param("record") User record, @Param("example") UserExample example);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}