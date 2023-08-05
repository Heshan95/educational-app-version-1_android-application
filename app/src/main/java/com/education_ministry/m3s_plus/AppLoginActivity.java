package com.education_ministry.m3s_plus;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AppLoginActivity extends AppCompatActivity {


    String usernameCasted=null;
    String passwordCasted=null;
    Boolean incorrectStatus=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_login);


        Animation animation1= AnimationUtils.loadAnimation(AppLoginActivity.this,R.anim.move_animation_much_slower);
        findViewById(R.id.loginAppTextViewAdmin).startAnimation(animation1);

        Animation animation2= AnimationUtils.loadAnimation(AppLoginActivity.this,R.anim.visible_much_slower);
        findViewById(R.id.imageView7Login2).startAnimation(animation2);
        findViewById(R.id.imageView7Login2).setVisibility(View.VISIBLE);


        EditText username = findViewById(R.id.usernameField);
        EditText password = findViewById(R.id.passwordField);
        TextView appLoginAlertDescription = findViewById(R.id.appLoginAlertDescription);
        ImageView alertUsername = findViewById(R.id.alertUsername);
        ImageView alertPassword = findViewById(R.id.alertPassword);


        findViewById(R.id.loginExitBtnApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


        alertUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appLoginAlertDescription.setText("Hello there, looks like the entered username '"+usernameCasted+"' is not a valid credential for the user's username.");
                Animation animation = AnimationUtils.loadAnimation(AppLoginActivity.this, R.anim.move_animation);
                findViewById(R.id.appLoginSignInPopup).startAnimation(animation);
                findViewById(R.id.appLoginSignInPopup).setVisibility(View.VISIBLE);
            }
        });


        alertPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                appLoginAlertDescription.setText("Hello there, looks like the entered password '"+passwordCasted+"' is not a valid credential for the user's password.");
                Animation animation = AnimationUtils.loadAnimation(AppLoginActivity.this, R.anim.move_animation);
                findViewById(R.id.appLoginSignInPopup).startAnimation(animation);
                findViewById(R.id.appLoginSignInPopup).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.closeAppLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation2= AnimationUtils.loadAnimation(AppLoginActivity.this,R.anim.move_animation_down);
                findViewById(R.id.appLoginSignInPopup).startAnimation(animation2);
                findViewById(R.id.appLoginSignInPopup).setVisibility(View.GONE);
            }
        });

        findViewById(R.id.loginButtonApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        usernameCasted = username.getText().toString();
        passwordCasted = password.getText().toString();
                if(usernameCasted.equals("")){
                    incorrectStatus=true;
                    alertUsername.setVisibility(View.VISIBLE);
                    username.setBackgroundResource(R.drawable.login_notification_curves);
                }else if (passwordCasted.equals("")){
                    incorrectStatus=true;
                    alertPassword.setVisibility(View.VISIBLE);
                    password.setBackgroundResource(R.drawable.login_notification_curves);
                }else{
                    if(usernameCasted.equals("reygren")){
                        if(passwordCasted.equals("2035")){

                            incorrectStatus=false;
                            Intent gotoMain = new Intent(AppLoginActivity.this, MainActivity.class);
                            startActivity(gotoMain);
                            finish();
                        }else{
                            incorrectStatus=true;
                            alertPassword.setVisibility(View.VISIBLE);
                            password.setBackgroundResource(R.drawable.login_notification_curves);
                        }
                    }else{
                        incorrectStatus=true;
                        alertUsername.setVisibility(View.VISIBLE);
                        username.setBackgroundResource(R.drawable.login_notification_curves);


                    }
                }
            }
        });


        username.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                username.setBackgroundResource(R.drawable.logincurve);
                password.setBackgroundResource(R.drawable.logincurve);
                if(incorrectStatus) {
                    incorrectStatus=false;
                }
                alertUsername.setVisibility(View.INVISIBLE);
                alertPassword.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                username.setBackgroundResource(R.drawable.logincurve);
                password.setBackgroundResource(R.drawable.logincurve);
                if(incorrectStatus) {
                    incorrectStatus=false;
                }
                alertUsername.setVisibility(View.INVISIBLE);
                alertPassword.setVisibility(View.INVISIBLE);
                return false;
            }
        });
    }
}