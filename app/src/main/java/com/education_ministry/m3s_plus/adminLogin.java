package com.education_ministry.m3s_plus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class adminLogin extends AppCompatActivity {

    String usernameVal="admin";
    String passwordVal="1234";
    private Context mContext;

    EditText mEt1, mEt2, mEt3, mEt4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        initialize();

        addTextWatcher(mEt1);
        addTextWatcher(mEt2);
        addTextWatcher(mEt3);
        addTextWatcher(mEt4);

        findViewById(R.id.loginBackBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.infoBtnPinCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(adminLogin.this,R.anim.move_animation);
                findViewById(R.id.messagePopupboxLogin).startAnimation(animation);
                findViewById(R.id.messagePopupboxLogin).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.closeLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation2= AnimationUtils.loadAnimation(adminLogin.this,R.anim.move_animation_down);
                findViewById(R.id.messagePopupboxLogin).startAnimation(animation2);
                findViewById(R.id.messagePopupboxLogin).setVisibility(View.GONE);
            }
        });


        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mEt1.getText().toString().equals("") || mEt2.getText().toString().equals("") || mEt3.getText().toString().equals("") || mEt4.getText().toString().equals("")){
                    Toast.makeText(adminLogin.this,"PLEASE ENTER THE PINCODE",Toast.LENGTH_LONG).show();
                }else {
                    if (mEt1.getText().toString().equals("2") && mEt2.getText().toString().equals("0") && mEt3.getText().toString().equals("3") && mEt4.getText().toString().equals("5")) {
                        Intent toHome = new Intent(adminLogin.this, adminHome.class);
                        startActivity(toHome);
                        finish();
                    }else{
                        Toast.makeText(adminLogin.this,"INVALID PINCODE",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    private void initialize() {
        mEt1=findViewById(R.id.number1);
        mEt2=findViewById(R.id.number2);
        mEt3=findViewById(R.id.number3);
        mEt4=findViewById(R.id.number4);
        mContext = adminLogin.this;
    }

    private void addTextWatcher(final EditText one) {
        one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (one.getId()) {
                    case R.id.number1:
                        if (one.length() == 1) {
                            mEt2.requestFocus();
                        }
                        break;
                    case R.id.number2:
                        if (one.length() == 1) {
                            mEt3.requestFocus();
                        } else if (one.length() == 0) {
                            mEt1.requestFocus();
                        }
                        break;
                    case R.id.number3:
                        if (one.length() == 1) {
                            mEt4.requestFocus();
                        } else if (one.length() == 0) {
                            mEt2.requestFocus();
                        }
                        break;
                    case R.id.number4:
                        if (one.length() == 1) {
                            InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(adminLogin.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        } else if (one.length() == 0) {
                            mEt3.requestFocus();
                        }
                        break;
                }
            }
        });
    }
}