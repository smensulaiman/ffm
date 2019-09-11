package com.diufinalproject.sugarsense.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diufinalproject.sugarsense.R;

import java.util.ArrayList;

public class TaskDetailsAdapter extends RecyclerView.Adapter<TaskDetailsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> modelRemainderArrayList = new ArrayList<String>();

    public TaskDetailsAdapter(Context context, ArrayList<String> modelRemainderArrayList) {
        this.context = context;
        this.modelRemainderArrayList = modelRemainderArrayList;
    }

    @Override
    public TaskDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_issue, parent, false);
        return new TaskDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TaskDetailsAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return modelRemainderArrayList.size();
    }

    public void setList(ArrayList<String> list) {
        this.modelRemainderArrayList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
