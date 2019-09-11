package com.diufinalproject.sugarsense.loginsignup.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.loginsignup.usermodel.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SignupFragment extends Fragment {

    private TextView goto_login;
    public SignUpListener listener;
    private FirebaseAuth mAuth;
    private Button signup;
    private EditText email, user, pass;
    private DatabaseReference mRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference();

        email = view.findViewById(R.id.email_signup);
        user = view.findViewById(R.id.username_signup);
        pass = view.findViewById(R.id.password_signup);

        signup = view.findViewById(R.id.btn_signup);
        goto_login = view.findViewById(R.id.goto_login);
        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.gotoLogin();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        return view;
    }

    private void signup() {

        if (pass.length() < 8) {
            Toast.makeText(getContext(), "Password should be at least 8 character or digit !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            Toast.makeText(getContext(), "Invalid email !!!", Toast.LENGTH_SHORT).show();
            return;
        }

        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                try {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "complete", Toast.LENGTH_SHORT).show();
                        User userData = new User(user.getText().toString(), email.getText().toString(), pass.getText().toString());
                        mRef.child("user").child(mAuth.getUid()).setValue(userData);
                        pDialog.setTitleText("Success");
                        pDialog.setContentText(user.getText().toString());
                        if(pDialog.isShowing())
                        pDialog.dismissWithAnimation();
                    } else {
                        Toast.makeText(getContext(), "faild", Toast.LENGTH_SHORT).show();
                        if(pDialog.isShowing())
                        pDialog.dismissWithAnimation();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if(pDialog.isShowing())
                        pDialog.dismissWithAnimation();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Sorry", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                if(pDialog.isShowing())
                    pDialog.dismissWithAnimation();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignUpListener) {
            listener = (SignUpListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must Implement listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
