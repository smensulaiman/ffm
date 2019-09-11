package com.diufinalproject.sugarsense.activities.tasks.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.activities.tasks.adapters.ViewPagerAdapter;

public class DashboardFragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new Issue_Assigned_Fragment(), "Assaigned");
        viewPagerAdapter.addFragment(new Issue_Created_Fragment(), "Created");
        viewPager.setAdapter(viewPagerAdapter);
    }

}
