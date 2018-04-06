package net.houselease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.houselease.dao.UserMapper;
import net.houselease.pojo.*;
import net.houselease.service.interfaces.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<?> userList() throws Exception {
		UserExample example = new UserExample();
		List<?> list = userMapper.selectByExample(example);
		System.out.println("123" + list);
		return list;
	}

	@Override
	public User login(SimpleUser user) throws Exception {
		return userMapper.selectByUser(user);
	}

}
