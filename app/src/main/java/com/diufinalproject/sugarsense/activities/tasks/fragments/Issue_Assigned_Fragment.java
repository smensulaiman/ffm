package com.diufinalproject.sugarsense.activities.tasks.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.activities.tasks.adapters.IssueAdapter;
import com.diufinalproject.sugarsense.adapters.TaskDetailsAdapter;
import com.diufinalproject.sugarsense.models.Issue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Issue_Assigned_Fragment extends Fragment {

    RecyclerView recyclerViewList;
    TaskDetailsAdapter adapter;
    ArrayList<Issue> issues = new ArrayList<>();
    ArrayList<String> modelTasks;

    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issue__assigned_, container, false);
        recyclerViewList = view.findViewById(R.id.issue_item);

        //databaseReference = FirebaseDatabase.getInstance().getReference("Task");

        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        issues.clear();

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//
//                    if(snapshot.exists()) {
//                        String issueName = snapshot.child("doctorExperties").getValue().toString();
//                        String issueDescription = snapshot.child("issueDescription").getValue().toString();
//                        String doctorName = snapshot.child("doctorName").getValue().toString();
//                        String doctorExperties = snapshot.child("doctorExperties").getValue().toString();
//                        String issueStatus = snapshot.child("issueStatus").getValue().toString();
//
//                        issues.add(new Issue(issueName, issueDescription, doctorName, doctorExperties, issueStatus));
//                        adapter.setList(issues);
//                    }
//
//                }
//
//                if (pDialog.isShowing())
//                    pDialog.dismissWithAnimation();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                if (pDialog.isShowing())
//                    pDialog.dismissWithAnimation();
//            }
//        });

        modelTasks = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            modelTasks.add("name");
        }

        recyclerViewList.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new TaskDetailsAdapter(getContext(), modelTasks);
        recyclerViewList.setAdapter(adapter);
        if (pDialog.isShowing())
            pDialog.dismissWithAnimation();
        return view;
    }

}
