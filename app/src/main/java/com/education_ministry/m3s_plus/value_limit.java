package com.education_ministry.m3s_plus;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class value_limit extends AppCompatActivity {

    int defaultValue=MainActivity.iterationCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_limit);
        EditText powerval = findViewById(R.id.powerValue);
        powerval.setText(String.valueOf(MainActivity.iterationCount));

        findViewById(R.id.powerIncrementBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultValue++;
                powerval.setText(String.valueOf(defaultValue));
            }
        });

        findViewById(R.id.powerdecrementbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(defaultValue == 0){}
                else {defaultValue--;}
                powerval.setText(String.valueOf(defaultValue));
            }
        });

        findViewById(R.id.powerValue).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView powerValueField = findViewById(R.id.powerValue);
                powerValueField.setCursorVisible(true);
                return false;
            }
        });

        findViewById(R.id.adminLimitationBackbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAdminHome = new Intent(value_limit.this, adminHome.class);
                startActivity(gotoAdminHome);
                finish();
            }
        });

        findViewById(R.id.adminLimitationUpdateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(powerval.getText().toString().equals("0")){
                    Toast.makeText(getApplicationContext(),"LIMITING VALUE CANNOT BE ZERO",Toast.LENGTH_LONG).show();
                }else{
                    MainActivity.iterationCount = Integer.parseInt(powerval.getText().toString());
                    MainActivity.entryLimitAssigned=true;
                    Intent gotoHome = new Intent(value_limit.this, adminHome.class);
                    startActivity(gotoHome);
                    finish();
                }
            }
        });
    }

}