package com.example.belajarenamkedua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnL;
    Button btnR;
    Button btnT;
    Button btnS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnL = (Button) findViewById(R.id.btn1);
        btnR = (Button) findViewById(R.id.btn2);
        btnT = (Button) findViewById(R.id.btn3);
        btnS = (Button) findViewById(R.id.btn4);

        btnL.setOnClickListener(this);
        btnR.setOnClickListener(this);
        btnT.setOnClickListener(this);
        btnS.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if( view == btn1){
            Intent i = new Intent(getApplicationContext(), LinearLayoutActivity.class);
            startActivities(i);
        } else if( view == btn2) {
            Intent i = new Intent(getApplicationContext(), RelativeLayoutActivity.class);
            startActivities(i);
        } else if( view == btn3) {
            Intent i = new Intent(getApplicationContext(), TableLayoutActivity.class);
            startActivities(i);
        }else if( view == btn4) {
            Intent i = new Intent(getApplicationContext(), ScrollerViewActivity.class);
            startActivities(i);
        }
    }
}


