package com.education_ministry.m3s_plus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class entry_limit extends AppCompatActivity {

    int entryValue=MainActivity.entryLimit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_limit);
        EditText entryVal = findViewById(R.id.entryValueLimit);

        entryVal.setText(String.valueOf(MainActivity.entryLimit));


        findViewById(R.id.entryValueLimit).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView entryValueLimit = findViewById(R.id.entryValueLimit);
                entryValueLimit.setCursorVisible(true);
                return false;
            }
        });


        findViewById(R.id.entryIncrementBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryValue++;
                entryVal.setText(String.valueOf(entryValue));
            }
        });



        findViewById(R.id.entrydecrementbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entryValue == 0){}
                else {entryValue--;}
                entryVal.setText(String.valueOf(entryValue));
            }
        });


        findViewById(R.id.adminEntryLimitBackBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAdminHome = new Intent(entry_limit.this, adminHome.class);
                startActivity(gotoAdminHome);
                finish();
            }
        });

        findViewById(R.id.adminEntryLimitationUpdateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(entryVal.getText().toString().equals("0")){
                    Toast.makeText(getApplicationContext(),"ENTRY VALUE CANNOT BE ZERO",Toast.LENGTH_LONG).show();
                }else{
                    MainActivity.entryLimit = Integer.parseInt(entryVal.getText().toString());
                    MainActivity.entryLimitAssigned=true;
                    Intent gotoHome = new Intent(entry_limit.this, adminHome.class);
                    startActivity(gotoHome);
                    finish();
                }
            }
        });
    }
}