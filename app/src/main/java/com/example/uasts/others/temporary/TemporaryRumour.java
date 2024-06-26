package com.example.uasts.others.temporary;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TemporaryRumour {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String RUMOUR_ID = "rumourId";
    private static final String PLAYER_NAME = "player_name";
    private static final String PLAYER_PHOTO = "player_photo";
    private static final String PLAYER_POSITION = "player_position";
    private static final String PLAYER_PRICE = "player_price";
    private static final String FROM_CLUB = "fromclub";
    private static final String DESCRIPTION = "description";
    private static final String FROMCLUBNAME = "fromclubname";
    private static final String RUMOURCLUB_NAME = "rumourclub_name";
    private static final String RUMOURCLUB_PHOTO = "club_photo";

    public TemporaryRumour(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void setRumourData(String playerName, String playerPhoto, String playerPosition, String playerPrice, String fromClub, String description, String fromclubname, String rumourclubName, String rumourclubPhoto, int rumourId) {
        editor.putString(PLAYER_NAME, playerName);
        editor.putString(PLAYER_PHOTO, playerPhoto);
        editor.putString(PLAYER_POSITION, playerPosition);
        editor.putString(PLAYER_PRICE, playerPrice);
        editor.putString(FROM_CLUB, fromClub);
        editor.putString(DESCRIPTION, description);
        editor.putString(FROMCLUBNAME, fromclubname);
        editor.putString(RUMOURCLUB_NAME, rumourclubName);
        editor.putString(RUMOURCLUB_PHOTO, rumourclubPhoto);
        editor.putInt(RUMOUR_ID, rumourId);
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

    public String getDescription() {
        return sharedPreferences.getString(DESCRIPTION, "");
    }

    public String getFromclubname() {
        return sharedPreferences.getString(FROMCLUBNAME, "");
    }
    public String getRumourclubName(){
        return sharedPreferences.getString(RUMOURCLUB_NAME, "");
    }
    public String getRumourclubPhoto(){
        return sharedPreferences.getString(RUMOURCLUB_PHOTO, "");
    }
    public int getRumourId(){
        return sharedPreferences.getInt(RUMOUR_ID, -1);
    }
}
