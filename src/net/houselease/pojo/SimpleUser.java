package net.houselease.pojo;

public class SimpleUser {

	private String username;

	private String password;

	public SimpleUser() {

	}

	public SimpleUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public SimpleUser(SimpleUser u) {
		this.username = u.username;
		this.password = u.password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
}
