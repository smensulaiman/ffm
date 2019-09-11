package com.diufinalproject.sugarsense.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.activities.chat.Message;
import com.diufinalproject.sugarsense.activities.chat.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.VideoViewHolder> {

    Context mcontext;
    List<User> mUser = new ArrayList<>();

    public UserAdapter(Context context, List<User> user) {
        this.mcontext = context;
        this.mUser = user;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.user_item, parent, false);

        return new UserAdapter.VideoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final VideoViewHolder holder, int position) {

        User user = mUser.get(position);
        holder.text.setText(user.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext, Message.class);
                intent.putExtra("name",holder.text.getText());
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        TextView text;

        public VideoViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.name);
        }
    }
}