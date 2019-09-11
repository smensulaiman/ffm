package com.diufinalproject.sugarsense.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.models.DbItem;

import java.util.ArrayList;

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DbItem> modelRemainderArrayList = new ArrayList<DbItem>();

    public DBAdapter(Context context, ArrayList<DbItem> modelRemainderArrayList) {
        this.context = context;
        this.modelRemainderArrayList = modelRemainderArrayList;
    }

    private RemainderDetailsAdapterListener remainderDetailsAdapterListener;

    public void setRemainderDetailsAdapterListener(RemainderDetailsAdapterListener remainderDetailsAdapterListener) {
        this.remainderDetailsAdapterListener = remainderDetailsAdapterListener;
    }

    @Override
    public DBAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_db_list, parent, false);
        return new DBAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DBAdapter.ViewHolder holder, int position) {
        final DbItem modelRemainder = modelRemainderArrayList.get(position);
        holder.count.setText(modelRemainder.getCount());
        holder.date.setText("Created on : "+modelRemainder.getDate());
    }

    @Override
    public int getItemCount() {
        return modelRemainderArrayList.size();
    }

    public void setList(ArrayList<DbItem> list) {
        this.modelRemainderArrayList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView count,date;

        public ViewHolder(View itemView) {
            super(itemView);
            count = itemView.findViewById(R.id.db);
            date = itemView.findViewById(R.id.date);
        }
    }
}
