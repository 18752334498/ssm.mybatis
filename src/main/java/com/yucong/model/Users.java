package com.yucong.model;

public class Users {

	private int id;
	private String username;
	private String password;

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + "]";
	}

	public Users() {
		super();
	}

	public Users(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

}
