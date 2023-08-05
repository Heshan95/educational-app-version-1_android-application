package com.education_ministry.m3s_plus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static int iterationCount=6;
    public static int newValue=1;
    public static int entryLimit=45;
    public static boolean entryLimitAssigned=true;
    public static String fileAbsolutepath;
    //group1
    int grp1_btn1_count=0;
    int grp1_btn2_count=0;
    int grp1_btn3_count=0;
    int group1_total=0;
    boolean group1_availability=true;

    //group2
    int grp2_btn1_count=0;
    int grp2_btn2_count=0;
    int grp2_btn3_count=0;
    int group2_total=0;
    boolean group2_availability=true;
    //group3
    int grp3_btn1_count=0;
    int grp3_btn2_count=0;
    int grp3_btn3_count=0;
    int group3_total=0;
    boolean group3_availability=true;
    //group4
    int grp4_btn1_count=0;
    int grp4_btn2_count=0;
    int grp4_btn3_count=0;
    int group4_total=0;
    boolean group4_availability=true;

    public static String input1="null";
    public static String input2="null";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.infoBtnGame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.move_animation);
                findViewById(R.id.messagePopupboxGame).startAnimation(animation);
                findViewById(R.id.messagePopupboxGame).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.closeGame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation2= AnimationUtils.loadAnimation(MainActivity.this,R.anim.move_animation_down);
                findViewById(R.id.messagePopupboxGame).startAnimation(animation2);
                findViewById(R.id.messagePopupboxGame).setVisibility(View.GONE);
            }
        });

        //grp1 -> START
        Button grp1_btn1 = findViewById(R.id.group1_btn_1);
        Button grp1_btn2 = findViewById(R.id.group1_btn_2);
        Button grp1_btn3 = findViewById(R.id.group1_btn_3);
        Button grp1_btn4 = findViewById(R.id.group1_btn_4);
        Button grp1_btn5 = findViewById(R.id.group1_btn_5);
        //grp1 -> END

        grp1_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grp1_btn1_count == 0 && group1_total < 2 && group1_availability==true) {
                    grp1_btn1.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp1_btn1.setTextColor(Color.parseColor("#FFFFFF"));
                    grp1_btn1_count++;
                    group1_total = group1_total + 1;
                    group2_availability=false;
                    group3_availability=false;
                    group4_availability=false;
                    if(grp1_btn4.getText().toString().equals("")) {
                        grp1_btn4.setText(grp1_btn1.getText().toString());
                    }else if(grp1_btn5.getText().toString().equals("")) {
                        grp1_btn5.setText(grp1_btn1.getText().toString());
                    }
                } else if (grp1_btn1_count == 1) {
                    grp1_btn1.setBackgroundResource(R.drawable.circle_radius);
                    grp1_btn1.setTextColor(Color.parseColor("#B2298E8B"));
                    grp1_btn1_count--;
                    group1_total = group1_total - 1;
                    if(grp1_btn4.getText().toString().equals(grp1_btn1.getText().toString())) {
                        grp1_btn4.setText("");
                    }else if(grp1_btn5.getText().toString().equals(grp1_btn1.getText().toString())) {
                        grp1_btn5.setText("");
                    }

                    if(group1_total == 0){
                        group2_availability=true;
                        group3_availability=true;
                        group4_availability=true;
                    }
                }


            }
        });

        grp1_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grp1_btn2_count == 0 && group1_total < 2 && group1_availability==true ) {
                    grp1_btn2.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp1_btn2.setTextColor(Color.parseColor("#FFFFFF"));
                    grp1_btn2_count++;
                    group1_total=group1_total+1;
                    group2_availability=false;
                    group3_availability=false;
                    group4_availability=false;
                    if(grp1_btn4.getText().toString().equals("")) {
                        grp1_btn4.setText(grp1_btn2.getText().toString());
                    }else if(grp1_btn5.getText().toString().equals("")) {
                        grp1_btn5.setText(grp1_btn2.getText().toString());
                    }
                }else if(grp1_btn2_count == 1){
                    grp1_btn2.setBackgroundResource(R.drawable.circle_radius);
                    grp1_btn2.setTextColor(Color.parseColor("#B2298E8B"));
                    grp1_btn2_count--;
                    group1_total=group1_total-1;
                    if(grp1_btn4.getText().toString().equals(grp1_btn2.getText().toString())) {
                        grp1_btn4.setText("");
                    }else if(grp1_btn5.getText().toString().equals(grp1_btn2.getText().toString())) {
                        grp1_btn5.setText("");
                    }
                    if(group1_total == 0){
                        group2_availability=true;
                        group3_availability=true;
                        group4_availability=true;
                    }
                }

            }
        });

        grp1_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grp1_btn3_count == 0 && group1_total < 2 && group1_availability==true) {
                    grp1_btn3.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp1_btn3.setTextColor(Color.parseColor("#FFFFFF"));
                    grp1_btn3_count++;
                    group1_total = group1_total + 1;
                    group2_availability=false;
                    group3_availability=false;
                    group4_availability=false;
                    if(grp1_btn4.getText().toString().equals("")) {
                        grp1_btn4.setText(grp1_btn3.getText().toString());
                    }else if(grp1_btn5.getText().toString().equals("")) {
                        grp1_btn5.setText(grp1_btn3.getText().toString());
                    }
                } else if (grp1_btn3_count == 1) {
                    grp1_btn3.setBackgroundResource(R.drawable.circle_radius);
                    grp1_btn3.setTextColor(Color.parseColor("#B2298E8B"));
                    grp1_btn3_count--;
                    group1_total = group1_total - 1;
                    if(grp1_btn4.getText().toString().equals(grp1_btn3.getText().toString())) {
                        grp1_btn4.setText("");
                    }else if(grp1_btn5.getText().toString().equals(grp1_btn3.getText().toString())) {
                        grp1_btn5.setText("");
                    }

                    if(group1_total == 0){
                        group2_availability=true;
                        group3_availability=true;
                        group4_availability=true;
                    }
                }

            }
        });



        //grp2 -> START
        Button grp2_btn1 = findViewById(R.id.group2_btn_1);
        Button grp2_btn2 = findViewById(R.id.group2_btn_2);
        Button grp2_btn3 = findViewById(R.id.group2_btn_3);
        Button grp2_btn4 = findViewById(R.id.group2_btn_4);
        Button grp2_btn5 = findViewById(R.id.group2_btn_5);
        //grp2 -> END

        grp2_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grp2_btn1_count == 0 && group2_total < 2 && group2_availability==true) {
                    grp2_btn1.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp2_btn1.setTextColor(Color.parseColor("#FFFFFF"));
                    grp2_btn1_count++;
                    group2_total=group2_total+1;
                    group1_availability=false;
                    group3_availability=false;
                    group4_availability=false;
                    if(grp2_btn4.getText().toString().equals("")) {
                        grp2_btn4.setText(grp2_btn1.getText().toString());
                    }else if(grp2_btn5.getText().toString().equals("")) {
                        grp2_btn5.setText(grp2_btn1.getText().toString());
                    }
                }else if(grp2_btn1_count == 1){
                    grp2_btn1.setBackgroundResource(R.drawable.circle_radius);
                    grp2_btn1.setTextColor(Color.parseColor("#B2298E8B"));
                    grp2_btn1_count--;
                    group2_total=group2_total-1;
                    if(grp2_btn4.getText().toString().equals(grp2_btn1.getText().toString())) {
                        grp2_btn4.setText("");
                    }else if(grp2_btn5.getText().toString().equals(grp2_btn1.getText().toString())) {
                        grp2_btn5.setText("");
                    }

                    if(group2_total == 0){
                        group1_availability=true;
                        group3_availability=true;
                        group4_availability=true;
                    }
                }
            }
        });

        grp2_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grp2_btn2_count == 0 && group2_total < 2 && group2_availability==true) {
                    grp2_btn2.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp2_btn2.setTextColor(Color.parseColor("#FFFFFF"));
                    grp2_btn2_count++;
                    group2_total=group2_total+1;
                    group1_availability=false;
                    group3_availability=false;
                    group4_availability=false;
                    if(grp2_btn4.getText().toString().equals("")) {
                        grp2_btn4.setText(grp2_btn2.getText().toString());
                    }else if(grp2_btn5.getText().toString().equals("")) {
                        grp2_btn5.setText(grp2_btn2.getText().toString());
                    }
                }else if(grp2_btn2_count == 1){
                    grp2_btn2.setBackgroundResource(R.drawable.circle_radius);
                    grp2_btn2.setTextColor(Color.parseColor("#B2298E8B"));
                    grp2_btn2_count--;
                    group2_total=group2_total-1;
                    if(grp2_btn4.getText().toString().equals(grp2_btn2.getText().toString())) {
                        grp2_btn4.setText("");
                    }else if(grp2_btn5.getText().toString().equals(grp2_btn2.getText().toString())) {
                        grp2_btn5.setText("");
                    }

                    if(group2_total == 0){
                        group1_availability=true;
                        group3_availability=true;
                        group4_availability=true;
                    }
                }
            }
        });

        grp2_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grp2_btn3_count == 0 && group2_total < 2  && group2_availability==true) {
                    grp2_btn3.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp2_btn3.setTextColor(Color.parseColor("#FFFFFF"));
                    grp2_btn3_count++;
                    group2_total=group2_total+1;
                    group1_availability=false;
                    group3_availability=false;
                    group4_availability=false;
                    if(grp2_btn4.getText().toString().equals("")) {
                        grp2_btn4.setText(grp2_btn3.getText().toString());
                    }else if(grp2_btn5.getText().toString().equals("")) {
                        grp2_btn5.setText(grp2_btn3.getText().toString());
                    }
                }else if(grp2_btn3_count == 1){
                    grp2_btn3.setBackgroundResource(R.drawable.circle_radius);
                    grp2_btn3.setTextColor(Color.parseColor("#B2298E8B"));
                    grp2_btn3_count--;
                    group2_total=group2_total-1;
                    if(grp2_btn4.getText().toString().equals(grp2_btn3.getText().toString())) {
                        grp2_btn4.setText("");
                    }else if(grp2_btn5.getText().toString().equals(grp2_btn3.getText().toString())) {
                        grp2_btn5.setText("");
                    }

                    if(group2_total == 0){
                        group1_availability=true;
                        group3_availability=true;
                        group4_availability=true;
                    }
                }
            }
        });


        //grp3 -> START
        Button grp3_btn1 = findViewById(R.id.group3_btn_1);
        Button grp3_btn2 = findViewById(R.id.group3_btn_2);
        Button grp3_btn3 = findViewById(R.id.group3_btn_3);
        Button grp3_btn4 = findViewById(R.id.group3_btn_4);
        Button grp3_btn5 = findViewById(R.id.group3_btn_5);
        //grp3 -> END

        grp3_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grp3_btn1_count == 0 && group3_total < 2   && group3_availability==true) {
                    grp3_btn1.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp3_btn1.setTextColor(Color.parseColor("#FFFFFF"));
                    grp3_btn1_count++;
                    group3_total=group3_total+1;
                    group1_availability=false;
                    group2_availability=false;
                    group4_availability=false;
                    if(grp3_btn4.getText().toString().equals("")) {
                        grp3_btn4.setText(grp3_btn1.getText().toString());
                        grp3_btn4.setBackgroundResource(R.drawable.square_radius_fill_red);
                    }else if(grp3_btn5.getText().toString().equals("")) {
                        grp3_btn5.setText(grp3_btn1.getText().toString());
                        grp3_btn5.setBackgroundResource(R.drawable.square_radius_fill_red);
                    }
                }else if(grp3_btn1_count > 0){
                    grp3_btn1.setBackgroundResource(R.drawable.circle_radius);
                    grp3_btn1.setTextColor(Color.parseColor("#B2298E8B"));
                    grp3_btn1_count--;
                    group3_total=group3_total-1;
                    if(grp3_btn4.getText().toString().equals(grp3_btn1.getText().toString())) {
                        grp3_btn4.setText("");
                        grp3_btn4.setBackgroundResource(R.drawable.square_radius_fill);
                    }else if(grp3_btn5.getText().toString().equals(grp3_btn1.getText().toString())) {
                        grp3_btn5.setText("");
                        grp3_btn5.setBackgroundResource(R.drawable.square_radius_fill);
                    }

                    if(group3_total == 0){
                        group1_availability=true;
                        group2_availability=true;
                        group4_availability=true;
                    }
                }
            }
        });

        grp3_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grp3_btn2_count == 0 && group3_total < 2   && group3_availability==true) {
                    grp3_btn2.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp3_btn2.setTextColor(Color.parseColor("#FFFFFF"));
                    grp3_btn2_count++;
                    group3_total++;
                    group1_availability=false;
                    group2_availability=false;
                    group4_availability=false;
                    if(grp3_btn4.getText().toString().equals("")) {
                        grp3_btn4.setText(grp3_btn2.getText().toString());
                        grp3_btn4.setBackgroundResource(R.drawable.square_radius_fill_green);
                    }else if(grp3_btn5.getText().toString().equals("")) {
                        grp3_btn5.setText(grp3_btn2.getText().toString());
                        grp3_btn5.setBackgroundResource(R.drawable.square_radius_fill_green);
                    }
                }else if(grp3_btn2_count > 0){
                    grp3_btn2.setBackgroundResource(R.drawable.circle_radius);
                    grp3_btn2.setTextColor(Color.parseColor("#B2298E8B"));
                    grp3_btn2_count--;
                    group3_total=group3_total-1;
                    if(grp3_btn4.getText().toString().equals(grp3_btn2.getText().toString())) {
                        grp3_btn4.setText("");
                        grp3_btn4.setBackgroundResource(R.drawable.square_radius_fill);
                    }else if(grp3_btn5.getText().toString().equals(grp3_btn2.getText().toString())) {
                        grp3_btn5.setText("");
                        grp3_btn5.setBackgroundResource(R.drawable.square_radius_fill);
                    }

                    if(group3_total == 0){
                        group1_availability=true;
                        group2_availability=true;
                        group4_availability=true;
                    }
                }
            }
        });

        grp3_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grp3_btn3_count == 0 && group3_total < 2   && group3_availability==true) {
                    grp3_btn3.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp3_btn3.setTextColor(Color.parseColor("#FFFFFF"));
                    grp3_btn3_count++;
                    group3_total++;
                    group1_availability=false;
                    group2_availability=false;
                    group4_availability=false;
                    if(grp3_btn4.getText().toString().equals("")) {
                        grp3_btn4.setText(grp3_btn3.getText().toString());
                        grp3_btn4.setBackgroundResource(R.drawable.square_radius_fill_blue);
                    }else if(grp3_btn5.getText().toString().equals("")) {
                        grp3_btn5.setText(grp3_btn3.getText().toString());
                        grp3_btn5.setBackgroundResource(R.drawable.square_radius_fill_blue);
                    }
                }else if(grp3_btn3_count > 0){
                    grp3_btn3.setBackgroundResource(R.drawable.circle_radius);
                    grp3_btn3.setTextColor(Color.parseColor("#B2298E8B"));
                    grp3_btn3_count--;
                    group3_total=group3_total-1;
                    if(grp3_btn4.getText().toString().equals(grp3_btn3.getText().toString())) {
                        grp3_btn4.setText("");
                        grp3_btn4.setBackgroundResource(R.drawable.square_radius_fill);
                    }else if(grp3_btn5.getText().toString().equals(grp3_btn3.getText().toString())) {
                        grp3_btn5.setText("");
                        grp3_btn5.setBackgroundResource(R.drawable.square_radius_fill);
                    }

                    if(group3_total == 0){
                        group1_availability=true;
                        group2_availability=true;
                        group4_availability=true;
                    }
                }
            }
        });



        //grp4 -> START
        Button grp4_btn1 = findViewById(R.id.group4_btn_1);
        Button grp4_btn2 = findViewById(R.id.group4_btn_2);
        Button grp4_btn3 = findViewById(R.id.group4_btn_3);
        Button grp4_btn4 = findViewById(R.id.group4_btn_4);
        Button grp4_btn5 = findViewById(R.id.group4_btn_5);
        //grp4 -> END

        grp4_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grp4_btn1_count == 0 && group4_total < 2   && group4_availability==true) {
                    grp4_btn1.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp4_btn1.setTextColor(Color.parseColor("#FFFFFF"));
                    grp4_btn1_count++;
                    group4_total=group4_total+1;
                    group1_availability=false;
                    group2_availability=false;
                    group3_availability=false;
                    if(grp4_btn4.getText().toString().equals("")) {
                        grp4_btn4.setText(grp4_btn1.getText().toString());
                    }else if(grp4_btn5.getText().toString().equals("")) {
                        grp4_btn5.setText(grp4_btn1.getText().toString());
                    }
                }else if(grp4_btn1_count > 0){
                    grp4_btn1.setBackgroundResource(R.drawable.circle_radius);
                    grp4_btn1.setTextColor(Color.parseColor("#B2298E8B"));
                    grp4_btn1_count--;
                    group4_total=group4_total-1;
                    if(grp4_btn4.getText().toString().equals(grp4_btn1.getText().toString())) {
                        grp4_btn4.setText("");
                    }else if(grp4_btn5.getText().toString().equals(grp4_btn1.getText().toString())) {
                        grp4_btn5.setText("");
                    }

                    if(group4_total == 0){
                        group1_availability=true;
                        group2_availability=true;
                        group3_availability=true;
                    }
                }
            }
        });

        grp4_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grp4_btn2_count == 0 && group4_total < 2   && group4_availability==true) {
                    grp4_btn2.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp4_btn2.setTextColor(Color.parseColor("#FFFFFF"));
                    grp4_btn2_count++;
                    group4_total++;
                    group1_availability=false;
                    group2_availability=false;
                    group3_availability=false;
                    if(grp4_btn4.getText().toString().equals("")) {
                        grp4_btn4.setText(grp4_btn2.getText().toString());
                    }else if(grp4_btn5.getText().toString().equals("")) {
                        grp4_btn5.setText(grp4_btn2.getText().toString());
                    }
                }else if(grp4_btn2_count > 0){
                    grp4_btn2.setBackgroundResource(R.drawable.circle_radius);
                    grp4_btn2.setTextColor(Color.parseColor("#B2298E8B"));
                    grp4_btn2_count--;
                    group4_total=group4_total-1;
                    if(grp4_btn4.getText().toString().equals(grp4_btn2.getText().toString())) {
                        grp4_btn4.setText("");
                    }else if(grp4_btn5.getText().toString().equals(grp4_btn2.getText().toString())) {
                        grp4_btn5.setText("");
                    }

                    if(group4_total == 0){
                        group1_availability=true;
                        group2_availability=true;
                        group3_availability=true;
                    }
                }
            }
        });

        grp4_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(grp4_btn3_count == 0 && group4_total < 2   && group4_availability==true) {
                    grp4_btn3.setBackgroundResource(R.drawable.circle_radius_fill);
                    grp4_btn3.setTextColor(Color.parseColor("#FFFFFF"));
                    grp4_btn3_count++;
                    group4_total++;
                    group1_availability=false;
                    group2_availability=false;
                    group3_availability=false;
                    if(grp4_btn4.getText().toString().equals("")) {
                        grp4_btn4.setText(grp4_btn3.getText().toString());
                    }else if(grp4_btn5.getText().toString().equals("")) {
                        grp4_btn5.setText(grp4_btn3.getText().toString());
                    }
                }else if(grp4_btn3_count > 0){
                    grp4_btn3.setBackgroundResource(R.drawable.circle_radius);
                    grp4_btn3.setTextColor(Color.parseColor("#B2298E8B"));
                    grp4_btn3_count--;
                    group4_total=group4_total-1;
                    if(grp4_btn4.getText().toString().equals(grp4_btn3.getText().toString())) {
                        grp4_btn4.setText("");
                    }else if(grp4_btn5.getText().toString().equals(grp4_btn3.getText().toString())) {
                        grp4_btn5.setText("");
                    }

                    if(group4_total == 0){
                        group1_availability=true;
                        group2_availability=true;
                        group3_availability=true;
                    }
                }
            }
        });





        findViewById(R.id.gotoGameBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!grp1_btn4.getText().toString().equals("") && !grp1_btn5.getText().toString().equals("")){

                    input1=grp1_btn4.getText().toString();
                    input2=grp1_btn5.getText().toString();

                }


                if(!grp4_btn4.getText().toString().equals("") && !grp4_btn5.getText().toString().equals("")){

                    input1=grp4_btn4.getText().toString();
                    input2=grp4_btn5.getText().toString();

                }
                if(!grp2_btn4.getText().toString().equals("") && !grp2_btn5.getText().toString().equals("")){

                    input1=grp2_btn4.getText().toString();
                    input2=grp2_btn5.getText().toString();

                }

                if(!grp3_btn4.getText().toString().equals("") && !grp3_btn5.getText().toString().equals("")){

                    String btn4Clicked=null;
                    String btn5Clicked=null;

                    btn4Clicked=grp3_btn4.getText().toString();
                    btn5Clicked=grp3_btn5.getText().toString();

                    if(btn4Clicked.equals("R")){
                        input1="#FB2D5E";
                    }else if(btn4Clicked.equals("G")){
                        input1="#4CCC51";
                    }else if(btn4Clicked.equals("B")){
                        input1="#2A7BF4";
                    }


                    if(btn5Clicked.equals("R")){
                        input2="#FB2D5E";
                    }else if(btn5Clicked.equals("G")){
                        input2="#4CCC51";
                    }else if(btn5Clicked.equals("B")){
                        input2="#2A7BF4";
                    }
                }

                Intent gotoGame = new Intent(MainActivity.this, gameActivity.class);

                if(input1.equals("null") && input2.equals("null")){
                    Toast.makeText(getApplicationContext(),"PLEASE SELECT A VALID GROUP",Toast.LENGTH_LONG).show();
                }else {
                    startActivity(gotoGame);
                }

            }
        });

        findViewById(R.id.exitBtnFromMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent gotoMain = new Intent(MainActivity.this, AppLoginActivity.class);
               startActivity(gotoMain);
               finish();
            }
        });



        findViewById(R.id.reportButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myFileIntent=new Intent(Intent.ACTION_GET_CONTENT);
                myFileIntent.setType("*/*");
                startActivity(myFileIntent);
            }
        });


        findViewById(R.id.adminSectionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminSection = new Intent(MainActivity.this, adminLogin.class);
                startActivity(adminSection);
            }
        });
    }
}