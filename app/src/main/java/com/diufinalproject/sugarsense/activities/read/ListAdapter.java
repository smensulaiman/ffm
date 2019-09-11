package com.diufinalproject.sugarsense.activities.read;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.diufinalproject.sugarsense.R;
import java.util.List;
import java.util.Random;

public class ListAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private List<String> mItems;
    private Random mRandomizer = new Random();

    public ListAdapter(Context context, List<String> items) {
        super(context,0,items);
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public String getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_list_view_row_items, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text.setText(getItem(position));
        return convertView;
    }

    static class ViewHolder {
        TextView text;

        public ViewHolder(View view) {
            text = (TextView)view.findViewById(R.id.text_view_item_name);
        }
    }
}
