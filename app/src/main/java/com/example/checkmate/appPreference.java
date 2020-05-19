package com.example.checkmate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class appPreference {
    private static final String APP_SHARED_PREFS = appPreference.class.getSimpleName(); // Name of the file -.xml
    private SharedPreferences pref;
    private SharedPreferences.Editor edit;

    public appPreference(Context context)
    {

        this.pref =context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.edit = pref.edit();

        edit.putInt(MainActivity.COUNT,pref.getInt(MainActivity.COUNT,0));
        edit.apply();
    }

    public int getNumLine()
    { // num of lines of players in prefernce
        System.out.println(pref.getInt(MainActivity.COUNT,0)+" why");
        return this.pref.getInt(MainActivity.COUNT,0);
    }

    public String getPlayerName(int i, char color)
    { // get name of player in clone i of preference
        if(color == 'w') {
            return pref.getString(MainActivity.NAME + i, "");
        }
        else{
            return pref.getString(MainActivity.NAME2 + i, "");
        }
    }

    public String getPlayerColor(int i, char color)
    { // get name of player in clone i of preference
        if(color == 'w') {
            return pref.getString(MainActivity.COLOR + i, "");
        }
        else{
            return pref.getString(MainActivity.COLOR2 + i, "");
        }
    }

    public int getScore (int i, char color)
    {
        if(color == 'w') {
            return pref.getInt(MainActivity.SCORE + i, 0);
        }
        else{
            return pref.getInt(MainActivity.SCORE2 + i, 0);
        }
    }

    public void update(String playerName, int score, String playerName2, int score2)
    {
        // update data in preferenc
        int i; // loop counter, COUNT is the length of prefernce

        i = pref.getInt(MainActivity.COUNT, 0);
        edit.putString(MainActivity.NAME+i ,playerName);
        edit.putInt(MainActivity.SCORE+i,score);
        edit.putString(MainActivity.COLOR+i, "White");

        edit.putString(MainActivity.NAME2+i ,playerName2);
        edit.putInt(MainActivity.SCORE2+i,score2);
        edit.putString(MainActivity.COLOR2+i, "Black");

        if(pref.getInt(MainActivity.COUNT,0)!=0)
            edit.putInt(MainActivity.COUNT,pref.getInt(MainActivity.COUNT,0)+1);
        else
            edit.putInt(MainActivity.COUNT,1);

        edit.apply();
    }
}