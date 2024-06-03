package com.example.uasts.model.rumourfile;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RumourFile{

	@SerializedName("data")
	private List<RumourFileData> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<RumourFileData> data){
		this.data = data;
	}

	public List<RumourFileData> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}