package com.diufinalproject.sugarsense.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.dasshboard.DashBoardWithBottomNav;
import com.kaopiz.kprogresshud.KProgressHUD;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final KProgressHUD progressDialog=  KProgressHUD.create(LoginActivity.this)
//                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
//                        .setLabel("Please wait")
//                        .setCancellable(false)
//                        .setAnimationSpeed(2)
//                        .setDimAmount(0.5f)
//                        .show();
                startActivity(new Intent(LoginActivity.this, DashBoardWithBottomNav.class));
                finish();
            }
        });
    }
}
