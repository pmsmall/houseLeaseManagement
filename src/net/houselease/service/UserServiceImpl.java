package net.houselease.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.houselease.dao.UserMapper;
import net.houselease.pojo.*;
import net.houselease.service.interfaces.UserService;
import net.houselease.staticData.Dictionary;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> userList() throws Exception {
		UserExample example = new UserExample();
		List<User> list = userMapper.selectByExample(example);
		return list;
	}

	@Override
	public User login(SimpleUser user) throws Exception {
		return userMapper.selectByUser(user);
	}

	@Override
	public User signup(User user) throws Exception {
		User registedUser = new User(user, Dictionary.RENTER_FIELD);
		int result = userMapper.insert(registedUser);
		if (result == 1)
			return registedUser;
		return null;
	}

	@Override
	public boolean signupCheck(SimpleUser user) throws Exception {
		return signupCheck(user.getUsername());
	}

	@Override
	public boolean signupCheck(String username) throws Exception {
		return userMapper.selectByUsername(username) == 0;
	}

}
