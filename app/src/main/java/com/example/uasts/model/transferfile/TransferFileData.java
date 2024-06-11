package com.example.uasts.model.transferfile;

import com.google.gson.annotations.SerializedName;

public class TransferFileData {

	@SerializedName("player_photo")
	private String playerPhoto;

	@SerializedName("club_photo")
	private String clubPhoto;

	@SerializedName("transfer_price")
	private String transferPrice;

	@SerializedName("player_positions")
	private String playerPositions;

	@SerializedName("fromclubtransfer")
	private String fromclubtransfer;

	@SerializedName("id")
	private int id;

	@SerializedName("player_name")
	private String playerName;

	@SerializedName("club_name")
	private String clubName;

	@SerializedName("transfer_description")
	private String transferDescription;

	public void setPlayerPhoto(String playerPhoto){
		this.playerPhoto = playerPhoto;
	}

	public String getPlayerPhoto(){
		return playerPhoto;
	}

	public void setClubPhoto(String clubPhoto){
		this.clubPhoto = clubPhoto;
	}

	public String getClubPhoto(){
		return clubPhoto;
	}

	public void setTransferPrice(String transferPrice){
		this.transferPrice = transferPrice;
	}

	public String getTransferPrice(){
		return transferPrice;
	}

	public void setPlayerPositions(String playerPositions){
		this.playerPositions = playerPositions;
	}

	public String getPlayerPositions(){
		return playerPositions;
	}

	public void setFromclubtransfer(String fromclubtransfer){
		this.fromclubtransfer = fromclubtransfer;
	}

	public String getFromclubtransfer(){
		return fromclubtransfer;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}

	public String getPlayerName(){
		return playerName;
	}

	public void setClubName(String clubName){
		this.clubName = clubName;
	}

	public String getClubName(){
		return clubName;
	}

	public void setTransferDescription(String transferDescription){
		this.transferDescription = transferDescription;
	}

	public String getTransferDescription(){
		return transferDescription;
	}
}