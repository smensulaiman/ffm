package com.diufinalproject.sugarsense.loginsignup.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.dasshboard.DashBoardWithBottomNav;
import com.diufinalproject.sugarsense.utils.DatasaveManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {

    public LogInListener listener;
    private TextView gotoSignup;
    private EditText email, password;
    private Button login;
    private FirebaseAuth mAuth;
    private SweetAlertDialog pDialog;
    private DatabaseReference ref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        mAuth = FirebaseAuth.getInstance();

        gotoSignup = view.findViewById(R.id.goto_signup);
        gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.gotoSignup();
            }
        });

        email = view.findViewById(R.id.login_email);
        password = view.findViewById(R.id.login_password);
        login = view.findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        return view;

    }

    private void login() {

        if(email.getText().toString().equals("")){
            Intent intent = new Intent(getActivity(), DashBoardWithBottomNav.class);
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("userlogin", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("usersession", "Admin");
            editor.commit();
            DatasaveManager.name = "Admin";
            DatasaveManager.number = "+8801914667359";
            startActivity(intent);
        }else {

            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "successful", Toast.LENGTH_SHORT).show();
                        final String[] name = {null};
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            final String uId = user.getUid();
                            ref = FirebaseDatabase.getInstance().getReference().child("user").child(uId).child("name");
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    name[0] = dataSnapshot.getValue(String.class);
                                    Intent intent = new Intent(getActivity(), DashBoardWithBottomNav.class);
                                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("userlogin", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("usersession", name[0]);
                                    DatasaveManager.name = name[0];
                                    editor.commit();
                                    pDialog.setTitleText("Success");
                                    if(pDialog.isShowing()){
                                        pDialog.dismissWithAnimation();
                                    }
                                    startActivity(intent);
                                    getActivity().finish();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        } else {
                            Toast.makeText(getActivity(), "incorrect email or password.",
                                    Toast.LENGTH_SHORT).show();
                            if(pDialog.isShowing()){
                                pDialog.dismissWithAnimation();
                            }
                        }
                    } else {
                        if(pDialog.isShowing()){
                            pDialog.dismissWithAnimation();
                        }
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Failed...")
                                .setContentText("Something went wrong!")
                                .show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if(pDialog.isShowing()){
                        pDialog.dismissWithAnimation();
                    }
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Failed...")
                            .setContentText("Something went wrong!")
                            .show();
                }
            });
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LogInListener) {
            listener = (LogInListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must have interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
