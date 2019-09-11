package com.diufinalproject.sugarsense.firstload;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.loginsignup.LoginAndSignup;

public class ScrollPage extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private ScrollAdapter adapter;
    private TextView mDots[];
    private Button back;
    private Button next;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_page);

        viewPager = findViewById(R.id.viewPager);
        linearLayout = findViewById(R.id.linear_layout);

        back = findViewById(R.id.btn_back);
        next = findViewById(R.id.btn_next);

        adapter = new ScrollAdapter(ScrollPage.this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(listener);
        dotsCounter(0);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });
    }

    public void dotsCounter(int position) {

        mDots = new TextView[3];
        linearLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(getApplicationContext());
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            if (i == 0) {
                mDots[i].setTextColor(Color.parseColor("#72CAAF"));
            } else if (i == 1) {
                mDots[i].setTextColor(Color.parseColor("#F6D496"));
            } else {
                mDots[i].setTextColor(Color.parseColor("#EC7898"));
            }


            linearLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorwhite));
        }

    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            dotsCounter(i);
            currentPage = i;
            int lastpage = mDots.length;
            switch (currentPage) {
                case 0:
                    back.setEnabled(false);
                    back.setVisibility(View.GONE);
                    next.setEnabled(true);
                    next.setText("NEXT");
                    break;

                case 2:
                    back.setEnabled(true);
                    next.setEnabled(true);
                    back.setText("BACK");
                    next.setText("FINISH");
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (next.getText().toString().contains("FINISH")) {
                                SharedPreferences sharedPreferences = getSharedPreferences("FirstTime", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("condition", "accepted");
                                editor.commit();
                                startActivity(new Intent(getApplicationContext(), LoginAndSignup.class));
                                finish();
                            }
                        }
                    });
                    break;

                default:
                    back.setVisibility(View.VISIBLE);
                    back.setEnabled(true);
                    next.setEnabled(true);
                    back.setText("BACK");
                    next.setText("NEXT");
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
