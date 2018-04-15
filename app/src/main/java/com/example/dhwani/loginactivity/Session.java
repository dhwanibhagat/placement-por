package com.example.dhwani.loginactivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences sharedPreferences;

    public Session(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getId() {
        return sharedPreferences.getInt("Id", 0);
    }

    public void setId(int Id) {
        sharedPreferences.edit().putInt("Id", Id).apply();

    }
}
