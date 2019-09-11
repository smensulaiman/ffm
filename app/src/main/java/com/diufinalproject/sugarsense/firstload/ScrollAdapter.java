package com.diufinalproject.sugarsense.firstload;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.diufinalproject.sugarsense.R;

public class ScrollAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;

    public ScrollAdapter(Context context) {
        this.context = context;
    }

    public int[] images = {
            R.drawable.support,
            R.drawable.rice,
            R.drawable.alarm
    };

    public String[] head = {
            "Consultant",
            "Live chart",
            "Alarm"
    };

    public String[] desc = {
            "We always awake for your better health. Sugar sense offer you to contact the diabetes specialist in Bangladesh. You can easily contact with your preferred consultant and chat online with them.",
            "We are always concern about your food. You can get a live chart for your daily meal for better health for free and also see how much calories you need to consume.",
            "Don’t forget to get your meal timely. we are here to remind you the time meal you should take. Get proper notification from Sugar sense and eat healthy."

    };


    @Override
    public int getCount() {
        return head.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.scroll_view, container, false);

        ImageView slideImage = view.findViewById(R.id.slideImage);
        TextView slideTextHead = view.findViewById(R.id.headText);
        TextView slideTextDesc = view.findViewById(R.id.descText);

        slideImage.setImageResource(images[position]);
        slideTextHead.setText(head[position]);
        slideTextDesc.setText(desc[position]);
        int i = position;
        if (i == 0) {
            slideTextHead.setTextColor(Color.parseColor("#72CAAF"));
        } else if (i == 1) {
            slideTextHead.setTextColor(Color.parseColor("#F6D496"));
        } else {
            slideTextHead.setTextColor(Color.parseColor("#EC7898"));
        }

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
