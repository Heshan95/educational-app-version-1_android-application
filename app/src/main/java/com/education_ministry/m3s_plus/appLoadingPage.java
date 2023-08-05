package com.education_ministry.m3s_plus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class appLoadingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_loading_page);

        Animation animation1= AnimationUtils.loadAnimation(appLoadingPage.this,R.anim.move_animation_much_slower);
        Animation animation2= AnimationUtils.loadAnimation(appLoadingPage.this,R.anim.visible_much_slower);
        findViewById(R.id.loadingAppContainer).startAnimation(animation1);
        findViewById(R.id.imageView7Loading2).startAnimation(animation2);
        findViewById(R.id.imageView7Loading2).setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(appLoadingPage.this,AppLoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }
}