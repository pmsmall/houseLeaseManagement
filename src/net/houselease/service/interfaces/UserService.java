package net.houselease.service.interfaces;

import java.util.List;

import net.houselease.pojo.SimpleUser;
import net.houselease.pojo.User;

public interface UserService {

	public List<User> userList() throws Exception;

	public User login(SimpleUser user) throws Exception;

	public User signup(User user) throws Exception;

	public boolean signupCheck(SimpleUser user) throws Exception;

	public boolean signupCheck(String username) throws Exception;

}
