package com.example.uasts.model.transferfile;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TransferFile{

	@SerializedName("data")
	private List<TransferFileData> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public void setData(List<TransferFileData> data){
		this.data = data;
	}

	public List<TransferFileData> getData(){
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