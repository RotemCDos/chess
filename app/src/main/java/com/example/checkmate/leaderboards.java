package com.example.checkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class leaderboards extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnBack;
    ListView lv;
    ArrayList<Player> playerList;
    ScoreAdapter scoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        btnBack = (ImageButton) findViewById(R.id.btnBackL);
        btnBack.setOnClickListener(this);

        playerList = new ArrayList<Player>();
        playerList.add(new Player("Danny", 5));

        playerList.add(new Player("Rotem", 15));
        playerList.add(new Player("Roni", 54));
        playerList.add(new Player("Yossi", 54));

        scoreAdapter = new ScoreAdapter(this, 0, 0, playerList);


        lv = (ListView) findViewById(R.id.lvPlayers);
        lv.setAdapter(scoreAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBackL:
                finish();
        }
    }
}

