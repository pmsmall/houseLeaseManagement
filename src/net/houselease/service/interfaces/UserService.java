package net.houselease.service.interfaces;

import java.util.List;

import net.houselease.pojo.SimpleUser;
import net.houselease.pojo.User;

public interface UserService {

	public List<?> userList() throws Exception;

	public User login(SimpleUser user) throws Exception;

}
