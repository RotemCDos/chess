package com.example.checkmate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Lenovo on 03/25/2019.
 */

public class appPreference {
    private String playerName;
    private int score;
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

    public String getPlayerName(int i)
    { // get name of player in clone i of preference
        return pref.getString(MainActivity.NAME+i,"");
    }

    public String getPlayerColor(int i)
    { // get name of player in clone i of preference
        return pref.getString(MainActivity.COLOR+i,"");
    }

    public int getScore (int i)
    {
        return pref.getInt(MainActivity.SCORE+i,0);
    }

    public void update(String playerName, int score, char color)
    {
        // update data in preferenc
        int i; // loop counter, COUNT is the length of prefernce

        i = pref.getInt(MainActivity.COUNT, 0);
        edit.putString(MainActivity.NAME+i ,playerName);
        edit.putInt(MainActivity.SCORE+i,score);
        if(color == 'w'){
            edit.putString(MainActivity.COLOR+i, "White");
        }
        else{
            edit.putString(MainActivity.COLOR+i, "Black");
        }
        if(pref.getInt(MainActivity.COUNT,0)!=0)
            edit.putInt(MainActivity.COUNT,pref.getInt(MainActivity.COUNT,0)+1);
        else
            edit.putInt(MainActivity.COUNT,1);

        edit.apply();
    }
}