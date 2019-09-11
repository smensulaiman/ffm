package com.diufinalproject.sugarsense.activities.tasks.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.adapters.TaskDetailsAdapter;

import java.util.ArrayList;

public class Issue_Created_Fragment extends Fragment {


    RecyclerView recyclerViewList;
    TaskDetailsAdapter adapter;
    ArrayList<String> modelTasks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_issue__created, container, false);
        recyclerViewList = view.findViewById(R.id.issue_item);
        modelTasks = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            modelTasks.add("name");
        }

        recyclerViewList.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new TaskDetailsAdapter(getContext(), modelTasks);
        recyclerViewList.setAdapter(adapter);

        return view;
    }

}
