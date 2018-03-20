package net.houselease.service;

import java.util.List;

import net.houselease.pojo.User;
import net.houselease.pojo.Userlist;

public interface UserService {

	public List<User> userList() throws Exception;
	public User login(User user) throws Exception;
	
}
