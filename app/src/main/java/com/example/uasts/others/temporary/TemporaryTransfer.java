package com.example.uasts.others.temporary;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TemporaryTransfer {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PLAYER_NAME = "player_name";
    private static final String PLAYER_PHOTO = "player_photo";
    private static final String PLAYER_POSITION = "player_position";
    private static final String PLAYER_PRICE = "player_price";
    private static final String CLUB_PHOTO = "club_photo";

    public TemporaryTransfer(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void setTransferData(String playerName, String playerPhoto, String playerPosition, String playerPrice, String clubPhoto) {
        editor.putString(PLAYER_NAME, playerName);
        editor.putString(PLAYER_PHOTO, playerPhoto);
        editor.putString(PLAYER_POSITION, playerPosition);
        editor.putString(PLAYER_PRICE, playerPrice);
        editor.putString(CLUB_PHOTO, clubPhoto);
        editor.apply();
    }

    public String getPlayerName() {
        return sharedPreferences.getString(PLAYER_NAME, "");
    }

    public String getPlayerPhoto() {
        return sharedPreferences.getString(PLAYER_PHOTO, "");
    }

    public String getPlayerPosition() {
        return sharedPreferences.getString(PLAYER_POSITION, "");
    }

    public String getPlayerPrice() {
        return sharedPreferences.getString(PLAYER_PRICE, "");
    }
    public String getClubPhoto() {
        return sharedPreferences.getString(CLUB_PHOTO, "");
    }

}
