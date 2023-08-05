package com.education_ministry.m3s_plus;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class startActivity extends AppCompatActivity {

    int defaultValue=MainActivity.iterationCount;
    int startingValue=MainActivity.newValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        EditText value = findViewById(R.id.valueField);

        value.setText(String.valueOf(MainActivity.newValue));


        findViewById(R.id.valueIncrementBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startingValue++;
                value.setText(String.valueOf(startingValue));
            }
        });


        findViewById(R.id.valuedecrementbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(startingValue == 1){

                }
                else {
                    startingValue--;
                }
                value.setText(String.valueOf(startingValue));
            }
        });

        findViewById(R.id.valueField).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                TextView startingvalueField = findViewById(R.id.valueField);
                startingvalueField.setCursorVisible(true);
                return false;
            }
        });

        findViewById(R.id.backStartBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAdminHome = new Intent(startActivity.this, adminHome.class);
                startActivity(gotoAdminHome);
                finish();
            }
        });

        findViewById(R.id.updateStartValueButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(value.getText().toString().equals("0")){
                    Toast.makeText(getApplicationContext(),"STARTING VALUE CANNOT BE ZERO",Toast.LENGTH_LONG).show();
                }else{
                    MainActivity.newValue = Integer.parseInt(value.getText().toString());
                    Intent gotoHome = new Intent(startActivity.this, adminHome.class);
                    startActivity(gotoHome);
                    finish();
                }
            }
        });
    }
}