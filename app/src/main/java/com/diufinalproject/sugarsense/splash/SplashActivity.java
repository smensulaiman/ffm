package com.diufinalproject.sugarsense.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.activities.WelcomeActivity;
import com.diufinalproject.sugarsense.dasshboard.DashBoardWithBottomNav;
import com.diufinalproject.sugarsense.firstload.ScrollPage;
import com.diufinalproject.sugarsense.loginsignup.LoginAndSignup;
import com.wang.avi.AVLoadingIndicatorView;

public class SplashActivity extends AppCompatActivity {
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        avi = findViewById(R.id.loader);
        avi.setIndicator("BallTrianglePathIndicator");
        avi.show();


        SharedPreferences sharedPreferences = getSharedPreferences("FirstTime", MODE_PRIVATE);
        String type = sharedPreferences.getString("condition", null);

        sharedPreferences = getSharedPreferences("userlogin", MODE_PRIVATE);
        String name = sharedPreferences.getString("usersessionXXX", null);

        if (name!=null && !name.equals("logout")) {
            startActivity(new Intent(getApplicationContext(), DashBoardWithBottomNav.class));
            finish();
        } else {

            if (type == null) {
                type = "empty";
            }
            if (type.toLowerCase().equals("acceptedx")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), LoginAndSignup.class));
                        finish();
                    }
                }, 3000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                        finish();
                    }
                }, 1000);
            }
        }
    }
}
