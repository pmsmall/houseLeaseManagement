package net.houselease.service.interfaces;

import java.util.List;

import net.houselease.pojo.User;

public interface UserService {

	public List<User> userList() throws Exception;

	public User login(User user) throws Exception;

}
