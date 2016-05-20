package com.mitrais.scrummit.dao.model;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	private String id;
	
	private String username;
	private String email;
	private String fullname;
	private String password;
	
	public User() {
		
	}
	
	public User(String username, String email, String fullname, String password) {
		super();
		this.username = username;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
