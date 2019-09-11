package com.diufinalproject.sugarsense.dasshboard;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.diufinalproject.sugarsense.R;
import com.diufinalproject.sugarsense.dasshboard.fragments.Home;
import com.diufinalproject.sugarsense.dasshboard.fragments.Videos;
import com.diufinalproject.sugarsense.dasshboard.fragments.Workout;

public class DashBoardWithBottomNav extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_with_bottom_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameholder_nav, new Home()).commit();

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                changeFragment(menuItem);
                return true;
            }
        });
    }

    private void changeFragment(MenuItem menuItem) {
        Fragment selectedFragment = null;
        switch (menuItem.getItemId()) {

            case R.id.nav_home:
                selectedFragment = new Home();
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_workout:
                //selectedFragment = new Workout();
                selectedFragment = new Home();
                Toast.makeText(this, "Workout", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_videos:
                //selectedFragment = new Videos();
                selectedFragment = new Home();
                Toast.makeText(this, "Videos", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frameholder_nav,selectedFragment).commit();
    }
}
