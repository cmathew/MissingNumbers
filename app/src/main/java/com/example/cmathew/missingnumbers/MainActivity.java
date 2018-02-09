package com.example.cmathew.missingnumbers;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.drawerLayout = findViewById(R.id.main_drawer_layout);
        this.navigationView = findViewById(R.id.main_nav_view);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            setupDrawerNavigation();
        }

        navigateToMissingNumber();
    }

    private void setupDrawerNavigation() {
        navigationView.setNavigationItemSelectedListener(this);
        this.drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open_content_desc, R.string.nav_close_content_desc) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state
        drawerToggle.syncState();
    }

    private void navigateToMissingNumber() {
        MissingNumberFragment numberFragment = MissingNumberFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, numberFragment).commit();
    }

    private void navigateToDivision() {
        DivisionFragment divisionFragment = DivisionFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, divisionFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()) {
            return true;
        }

        item.setChecked(true);
        drawerLayout.closeDrawers();

        if (item.getItemId() == R.id.nav_missing_number) {
            navigateToMissingNumber();
            return true;
        } else if (item.getItemId() == R.id.nav_dividing) {
            navigateToDivision();
            return true;
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
