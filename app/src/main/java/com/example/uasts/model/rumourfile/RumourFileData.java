package com.example.uasts.model.rumourfile;

import com.google.gson.annotations.SerializedName;

public class RumourFileData {

	@SerializedName("rumourplayer_name")
	private String rumourplayerName;

	@SerializedName("rumour_price")
	private String rumourPrice;

	@SerializedName("rumourplayer_photo")
	private String rumourplayerPhoto;

	@SerializedName("fromclubname")
	private String fromclubname;

	@SerializedName("fromclub")
	private String fromclub;

	@SerializedName("rumourclub_name")
	private String rumourclubName;

	@SerializedName("rumourclub_photo")
	private String rumourclubPhoto;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("rumourplayer_position")
	private String rumourplayerPosition;

	public void setRumourplayerName(String rumourplayerName){
		this.rumourplayerName = rumourplayerName;
	}

	public String getRumourplayerName(){
		return rumourplayerName;
	}

	public void setRumourPrice(String rumourPrice){
		this.rumourPrice = rumourPrice;
	}

	public String getRumourPrice(){
		return rumourPrice;
	}

	public void setRumourplayerPhoto(String rumourplayerPhoto){
		this.rumourplayerPhoto = rumourplayerPhoto;
	}

	public String getRumourplayerPhoto(){
		return rumourplayerPhoto;
	}

	public void setFromclubname(String fromclubname){
		this.fromclubname = fromclubname;
	}

	public String getFromclubname(){
		return fromclubname;
	}

	public void setFromclub(String fromclub){
		this.fromclub = fromclub;
	}

	public String getFromclub(){
		return fromclub;
	}

	public void setRumourclubName(String rumourclubName){
		this.rumourclubName = rumourclubName;
	}

	public String getRumourclubName(){
		return rumourclubName;
	}

	public void setRumourclubPhoto(String rumourclubPhoto){
		this.rumourclubPhoto = rumourclubPhoto;
	}

	public String getRumourclubPhoto(){
		return rumourclubPhoto;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setRumourplayerPosition(String rumourplayerPosition){
		this.rumourplayerPosition = rumourplayerPosition;
	}

	public String getRumourplayerPosition(){
		return rumourplayerPosition;
	}
}