package com.example.uasts.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("is_admin")
	public boolean isAdmin;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("name")
	private String name;

	@SerializedName("level_user")
	private int levelUser;

	@SerializedName("username")
	private String username;

	public void setIsAdmin(boolean isAdmin){
		this.isAdmin = isAdmin;
	}

	public boolean isIsAdmin(){
		return isAdmin;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLevelUser(int levelUser){
		this.levelUser = levelUser;
	}

	public int getLevelUser(){
		return levelUser;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}