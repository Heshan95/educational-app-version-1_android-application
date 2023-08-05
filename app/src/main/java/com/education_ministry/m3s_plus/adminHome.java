package com.education_ministry.m3s_plus;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.ss.formula.functions.T;

public class adminHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);



        findViewById(R.id.updateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotoMain = new Intent(adminHome.this, MainActivity.class);
                startActivity(gotoMain);
                finish();
            }
        });


        findViewById(R.id.adminHomebackBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoMain = new Intent(adminHome.this, MainActivity.class);
                startActivity(gotoMain);
                finish();
            }
        });

        findViewById(R.id.startValueLimitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotoStartActivity = new Intent(adminHome.this, startActivity.class);
                startActivity(gotoStartActivity);
                finish();
            }
        });


        findViewById(R.id.updateValueLimitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateValueLimitBtn = new Intent(adminHome.this, value_limit.class);
                startActivity(updateValueLimitBtn);
                finish();
            }
        });


        findViewById(R.id.updateEntryCountLimitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateEntryCountLimitBtn = new Intent(adminHome.this, entry_limit.class);
                startActivity(updateEntryCountLimitBtn);
                finish();
            }
        });
    }
}