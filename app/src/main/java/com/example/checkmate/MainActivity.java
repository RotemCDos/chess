package com.example.checkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.podcopic.animationlib.library.AnimationType;
import com.podcopic.animationlib.library.StartSmartAnimation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private Button btnExt;
    private Button btnLdb;
    private Button btnRules;


    private androidx.constraintlayout.widget.ConstraintLayout ly;
    boolean ok;

    static MediaPlayer music;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnPlay);
        btnStart.setOnClickListener(this);

        btnLdb = (Button) findViewById(R.id.btnLdb);
        btnLdb.setOnClickListener(this);

        btnExt = (Button) findViewById(R.id.btnExt);
        btnExt.setOnClickListener(this);

        btnRules = (Button) findViewById(R.id.btnRules);
        btnRules.setOnClickListener(this);

        music = MediaPlayer.create(getApplicationContext(), R.raw.music);
        music.start();
        music.setLooping(true);

        ok = true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;

        switch (id) {

            case R.id.exit:
//                Save();
                startActivity(new Intent(this, splash.class));
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);


            case R.id.Item2:
                if(music.isPlaying())
                {
                    music.stop();
                    break;
                }

                music = MediaPlayer.create(MainActivity.this, R.raw.music);
                music.start();
                music.setLooping(true);
                break;

            case R.id.Test:
                startActivity(new Intent(MainActivity.this, leaderboards.class));
                break;

            case R.id.call:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "));
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if (v.getId() == R.id.btnExt)
        {
            finish();
            startActivity(new Intent(this, splash.class));
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else
        if(v.getId()==R.id.btnPlay){
            i= new Intent(this, enterinfo.class);
            startActivity(i);
        }
        else
            if (v.getId() == R.id.btnRules){
                i = new Intent(this, rules.class);
                startActivity(i);
            }
            else {
                i = new Intent(this, leaderboards.class);
                startActivity(i);
            }
    }
}
