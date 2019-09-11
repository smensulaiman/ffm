package com.diufinalproject.sugarsense.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.models.ModelRemainder;
import java.util.ArrayList;

public class RemainderDetailsAdapter extends RecyclerView.Adapter<RemainderDetailsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelRemainder> modelRemainderArrayList = new ArrayList<ModelRemainder>();

    public RemainderDetailsAdapter(Context context, ArrayList<ModelRemainder> modelRemainderArrayList) {
        this.context = context;
        this.modelRemainderArrayList = modelRemainderArrayList;
    }

    private RemainderDetailsAdapterListener remainderDetailsAdapterListener;

    public void setRemainderDetailsAdapterListener(RemainderDetailsAdapterListener remainderDetailsAdapterListener) {
        this.remainderDetailsAdapterListener = remainderDetailsAdapterListener;
    }

    @Override
    public RemainderDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_remainder_details, parent, false);
        return new RemainderDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RemainderDetailsAdapter.ViewHolder holder, int position) {
        final ModelRemainder modelRemainder = modelRemainderArrayList.get(position);

        holder.layout_color.setBackgroundColor(Color.parseColor(modelRemainder.getColor()));
        holder.txtActivity.setText(modelRemainder.getType());
        holder.txtMessage.setText(modelRemainder.getDetails());
        holder.txtDateTime.setText("Created on : "+modelRemainder.getCreatedAt());

        holder.imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(remainderDetailsAdapterListener != null){
                    int position = holder.getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        remainderDetailsAdapterListener.onItemClick(position,modelRemainder.getId());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelRemainderArrayList.size();
    }

    public void setList(ArrayList<ModelRemainder> list) {
        this.modelRemainderArrayList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout_color;
        TextView txtActivity;
        TextView txtMessage;
        TextView txtDateTime;
        ImageView imgClose;

        public ViewHolder(View itemView) {
            super(itemView);
            layout_color = itemView.findViewById(R.id.layout_color);
            txtActivity = itemView.findViewById(R.id.txtActivity);
            txtMessage = itemView.findViewById(R.id.txtMessage);
            txtDateTime = itemView.findViewById(R.id.txtDateTime);
            imgClose = itemView.findViewById(R.id.imgClose);
        }
    }
}
