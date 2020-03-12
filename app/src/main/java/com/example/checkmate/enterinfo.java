package com.example.checkmate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class enterinfo extends AppCompatActivity implements View.OnClickListener {

    private TextView tvHello;
    private EditText etName1;
    private EditText etName2;
    private Button btnStart;
    private ImageButton btnBackInfo;
    static String name1, name2;

    private androidx.constraintlayout.widget.ConstraintLayout ly;
    boolean ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterinfo);

        ok = true;

        etName1 = (EditText) findViewById(R.id.etName1);
        etName2 = (EditText) findViewById(R.id.etName2);

        btnBackInfo = (ImageButton) findViewById(R.id.btnBackInfo);
        btnBackInfo.setOnClickListener(this);

        btnStart = (Button) findViewById(R.id.btnPlayInfo);
        btnStart.setOnClickListener(this);

        ly = (androidx.constraintlayout.widget.ConstraintLayout) findViewById(R.id.ly);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if(v.getId() == R.id.btnBackInfo) {
            finish();
        }
        else
            if (etName1.getText().toString().trim().equals("") || etName2.getText().toString().trim().equals(""))//if null
            {
                new AlertDialog.Builder(this)
                        .setTitle("ERROR!")
                        .setMessage("Please enter a player name ☺")
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }

                        })
                        .setIcon(R.drawable.errorchess)
                        .setCancelable(false)
                        .show(); //error icon

            } else {
                if (v.getId() == R.id.btnPlayInfo) {
                    name1 = this.etName1.getText().toString();
                    name2 = this.etName2.getText().toString();
                    i = new Intent(this, GameActivity.class);
                    startActivity(i);


                }
            }

        }
        }


