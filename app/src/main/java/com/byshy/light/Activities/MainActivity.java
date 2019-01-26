package com.byshy.light.Activities;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.byshy.light.Fragments.AboutFragment;
import com.byshy.light.Fragments.BalanceFragment;
import com.byshy.light.Fragments.MainFragment;
import com.byshy.light.Fragments.NotificationFragment;
import com.byshy.light.R;
import com.byshy.light.Fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_screen_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        if (mNavigationView != null) {
            mNavigationView.setNavigationItemSelectedListener(this);
        }

        mNavigationView.setCheckedItem(R.id.nav_main);
        loadFragment(new MainFragment());

//        NavigationView navigationView = findViewById(R.id.nav_view);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            loadFragment(new MainFragment());
        } else if (id == R.id.nav_balance) {
            loadFragment(new BalanceFragment());
        } else if (id == R.id.nav_notifications) {
            loadFragment(new NotificationFragment());
        } else if (id == R.id.nav_settings) {
            loadFragment(new SettingsFragment());
        } else if (id == R.id.nav_about) {
            loadFragment(new AboutFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.generic, fragment).commit();
    }

}
