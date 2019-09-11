package com.diufinalproject.sugarsense.activities.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.activities.tasks.fragments.ActivityFragment;
import com.diufinalproject.sugarsense.activities.tasks.fragments.DashboardFragment;
import com.diufinalproject.sugarsense.activities.tasks.fragments.NotificationsFragment;
import com.diufinalproject.sugarsense.activities.tasks.fragments.ProjectsFragment;
import com.diufinalproject.sugarsense.activities.tasks.fragments.WatchlistFragment;

public class TaskActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        bottomNavigationView = findViewById(R.id.default_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        replaceDummyFragment(new DashboardFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment;

        if (menuItem.isChecked())
            menuItem.setChecked(false);
        switch (menuItem.getItemId()) {
            case R.id.action_home:
                menuItem.setChecked(true);
                fragment = new DashboardFragment();
                replaceDummyFragment(fragment);
                break;
            case R.id.action_projects:
                menuItem.setChecked(true);
                fragment = new ProjectsFragment();
                replaceDummyFragment(fragment);
                break;
            case R.id.action_notifications:
                menuItem.setChecked(true);
                fragment = new NotificationsFragment();
                replaceDummyFragment(fragment);
                break;
            case R.id.action_activity:
                menuItem.setChecked(true);
                //fragment = new ActivityFragment();
                //replaceDummyFragment(fragment);
                break;
            case R.id.action_watchlist:
                menuItem.setChecked(true);
                //fragment = new WatchlistFragment();
                //replaceDummyFragment(fragment);
                break;
            default:
                break;

        }
        return true;
    }

    private void replaceDummyFragment(@NonNull Fragment fragment) {
        Bundle b = new Bundle();
        fragment.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
    }
}
