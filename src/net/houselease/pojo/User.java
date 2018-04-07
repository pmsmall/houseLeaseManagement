package net.houselease.pojo;

public class User extends SimpleUser {
	private Integer id;

	private String type;

	public User() {

	}

	public User(SimpleUser user) {
		super(user);
	}

	public User(SimpleUser user, String type) {
		this(user);
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + getUsername() + ", password=" + getPassword() + ", type=" + type
				+ "]";
	}

}