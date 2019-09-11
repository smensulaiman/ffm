package com.diufinalproject.sugarsense.loginsignup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.loginsignup.fragments.LogInListener;
import com.diufinalproject.sugarsense.loginsignup.fragments.LoginFragment;
import com.diufinalproject.sugarsense.loginsignup.fragments.SignUpListener;
import com.diufinalproject.sugarsense.loginsignup.fragments.SignupFragment;

public class LoginAndSignup extends AppCompatActivity implements SignUpListener, LogInListener {

    Fragment loginFragment;
    Fragment signupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligin_and_signup);
        loginFragment = new LoginFragment();
        signupFragment = new SignupFragment();
    }

    @Override
    public void gotoLogin() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentholder,loginFragment);
        transaction.commit();
    }

    @Override
    public void gotoSignup() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentholder,signupFragment);
        transaction.commit();
    }
}
