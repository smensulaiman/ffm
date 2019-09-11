package com.diufinalproject.sugarsense.activities.bmi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.diufinalproject.sugarsense.R;

import java.util.ArrayList;

public class AlarmListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<AlarmModel> items;
    public AlarmListAdapter(Context context, ArrayList<AlarmModel> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total item in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns the item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_alarmrow, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        AlarmModel currentItem = (AlarmModel) getItem(position);
        viewHolder.itemName.setText(currentItem.getTime());
        viewHolder.itemDescription.setText(currentItem.getDesc());
        return convertView;
    }

    private class ViewHolder {
        TextView itemName;
        TextView itemDescription;

        public ViewHolder(View view) {
            itemName = (TextView)view.findViewById(R.id.time);
            itemDescription = (TextView) view.findViewById(R.id.desc);
        }
    }
}
