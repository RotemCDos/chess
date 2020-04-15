package com.example.checkmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class leaderboards extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{

    private ArrayList<HashMap<String,String>> statisticList; //list score
    private ListView lvStati; // the list view
    private AdapterListView adapter;
    private appPreference preference;
    private int stop; // num of lines in share preference
    private int score;
    ImageButton btnBack; //back button

    // list view finals
    public static final String FIRST_COLUMN="Score";
    public static final String SECOND_COLUMN="Player Name";
    public static final String THIRD_COLUMN="Player Color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);
        lvStati = (ListView)findViewById(R.id.lvStati);
//        getApplicationContext().getSharedPreferences("APP_SHARED_PREFS",0).edit().clear().commit();
        statisticList = new ArrayList<HashMap<String,String>>();
        preference = new appPreference(getApplicationContext());



        btnBack = (ImageButton) findViewById(R.id.btnBackLdb);
        btnBack.setOnClickListener(this);

        AddSortArrayList();//adds players to the list in the right order

        // first row
        HashMap<String,String> temp = new HashMap<String, String>();
        temp.put(FIRST_COLUMN,"Score:");
        temp.put(SECOND_COLUMN,"Name:");
        temp.put(THIRD_COLUMN,"Color:");
        statisticList.add(0,temp);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if(view.getId()==R.id.btnBackLdb)
        {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        for(int i=1;i<preference.getNumLine();i++)
        {
            if (v.getId() == lvStati.getItemIdAtPosition(i)){
                statisticList.remove(i);
            }
        }
        return false;
    }
    //sorts the arraylist in the correct order of scores
    public void AddSortArrayList(){
        int count;
        HashMap<String,String> temp;
        if(preference.getNumLine()==0)
            return;
        stop = preference.getNumLine();
        //adds all preference to array list
        for(count = 0 ; count < stop ; count++) {
            temp = new HashMap<String, String>();
            temp.put(FIRST_COLUMN, preference.getScore(count) + "");
            temp.put(SECOND_COLUMN, preference.getPlayerName(count));
            temp.put(THIRD_COLUMN, preference.getPlayerColor(count));
            statisticList.add(0, temp);
        }
//        this.score = preference.getScore(0);
        keepFirstTen();
    }

    //keeps ony the first ten in the leaderBoard
    public void keepFirstTen()
    {
        for(int ik = statisticList.size()-1; ik > 9; ik--)
        {
            statisticList.remove(ik);
        }

        adapter = new AdapterListView(this, statisticList);
        lvStati.setAdapter(adapter);
    }

}