package com.diufinalproject.sugarsense.activities.tasks.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.models.Issue;

import java.util.ArrayList;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Issue> issues = new ArrayList<>();

    public IssueAdapter(Context context, ArrayList<Issue> issues) {
        this.context = context;
        this.issues = issues;
    }

    public void setList(ArrayList<Issue> issues){
        this.issues = issues;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_issue,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final Issue issue = issues.get(i);

        viewHolder.txtIssueNumber.setText(issue.getIssueName());
        viewHolder.txtIssueDescription.setText(issue.getIssueDescription());
        viewHolder.txtDoctor.setText(issue.getDoctorName()+"\n"+issue.getDoctorExperties());
        viewHolder.txtIssueStatus.setText(issue.getIssueStatus());

    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtIssueNumber;
        TextView txtIssueDescription;
        TextView txtDoctor;
        TextView txtIssueStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIssueNumber = itemView.findViewById(R.id.txtIssueNumber);
            txtIssueDescription = itemView.findViewById(R.id.txtIssueDescription);
            txtDoctor = itemView.findViewById(R.id.txtDoctor);
            txtIssueStatus = itemView.findViewById(R.id.txtIssueStatus);
        }
    }
}
