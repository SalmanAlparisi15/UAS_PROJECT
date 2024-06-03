package com.example.uasts;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.uasts.model.login.LoginData;

import java.util.HashMap;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String IS_LOGGED_IN = "IsLoggedIn";
    private static final String USER_ID = "user_id";
    public static final String NAME = "name";
    public static final String IS_ADMIN = "is_admin";
    public static final String USERNAME = "username";

    public SessionManager(Context context) {
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user) {
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putInt(USER_ID, user.getUserId());
        editor.putString(NAME, user.getName());
        editor.putBoolean(IS_ADMIN, user.isIsAdmin());
        editor.putString(USERNAME, user.getUsername());
        editor.commit();
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(USER_ID, String.valueOf(sharedPreferences.getInt(USER_ID, -1)));
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(IS_ADMIN, String.valueOf(sharedPreferences.getBoolean(IS_ADMIN, false)));
        return user;
    }
    public void setIsAdmin(boolean isAdmin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_ADMIN, isAdmin);
        editor.commit();
    }

    public void LogoutSession() {
        editor.clear();
        editor.commit();
    }

    public boolean IsLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}
