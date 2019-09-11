package com.diufinalproject.sugarsense.activities.expense;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diufinalproject.sugarsense.R;

import java.util.ArrayList;

public class UserExpenseListAdapter extends RecyclerView.Adapter<UserExpenseListAdapter.ViewHolder> {

    Context context;
    public ArrayList<ModelUserVisit> userVisitArrayList = new ArrayList<ModelUserVisit>();

    public UserExpenseListAdapter(Context context, ArrayList<ModelUserVisit> userVisitArrayList) {
        this.context = context;
        this.userVisitArrayList = userVisitArrayList;

    }
    @Override
    public UserExpenseListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user_visit_expense, parent, false);
        return new UserExpenseListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserExpenseListAdapter.ViewHolder holder, final int position) {
        final ModelUserVisit modelUserVisit = userVisitArrayList.get(position);

        holder.txtCompanyName.setText(modelUserVisit.getCompanyName());
        holder.txtContactPerson.setText(modelUserVisit.getContactPerson());
        holder.txtPurpose.setText(modelUserVisit.getPurpose());
        holder.txtDateTime.setText(modelUserVisit.getDateTime());
        holder.txtAmount.setText(modelUserVisit.getAmount());
    }

    @Override
    public int getItemCount() {
        return userVisitArrayList.size();
    }

    public void setList(ArrayList<ModelUserVisit> list) {
        this.userVisitArrayList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView txtCompanyName;

        TextView txtContactPerson;

        TextView txtPurpose;

        TextView txtDateTime;

        TextView txtAmount;

        public ViewHolder(View view) {
            super(view);

            txtCompanyName = view.findViewById(R.id.txtCompanyName);
            txtContactPerson = view.findViewById(R.id.txtContactPerson);
            txtPurpose = view.findViewById(R.id.txtPurpose);
            txtDateTime = view.findViewById(R.id.txtDateTime);
            txtAmount = view.findViewById(R.id.txtAmount);
        }
    }
}
