package com.example.checkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
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
    private Button btnGallery;

    public static final String COUNT = "COUNT";
    public static final String NAME = "NAME";
    public static final String COLOR = "COLOR";
    public static final String SCORE = "SCORE";
    public static final String NAME2 = "NAME2";
    public static final String COLOR2 = "COLOR2";
    public static final String SCORE2 = "SCORE2";


    private androidx.constraintlayout.widget.ConstraintLayout ly;
    boolean ok;

//    static MediaPlayer music;


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

        btnGallery = (Button) findViewById(R.id.btnGallery);
        btnGallery.setOnClickListener(this);

//        music = MediaPlayer.create(getApplicationContext(), R.raw.music);
//        music.start();
//        music.setLooping(true);

//        startService(new Intent(this, MyService.class));

        ok = true;

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
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
                if (isMyServiceRunning(MyService.class)) {
                    stopService(new Intent(this, MyService.class));
                    break;
                }

                startService(new Intent(this, MyService.class));
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
        if (v.getId() == R.id.btnExt) {
            stopService(new Intent(this, MyService.class));
            finish();
            startActivity(new Intent(this, splash.class));
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else {
            if (v.getId() == R.id.btnPlay) {
                i = new Intent(this, enterinfo.class);
                startActivity(i);
            }
            else {
                if (v.getId() == R.id.btnRules) {
                    i = new Intent(this, rules.class);
                    startActivity(i);
                }
                else{
                    if (v.getId() == R.id.btnLdb) {
                        i = new Intent(this, leaderboards.class);
                        startActivity(i);
                    }
                    else {
                        i = new Intent(this, gallery.class);
                        startActivity(i);
                    }
                }
            }
        }
    }
}
