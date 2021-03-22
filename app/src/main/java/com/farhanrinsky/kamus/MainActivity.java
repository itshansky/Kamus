package com.farhanrinsky.kamus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.farhanrinsky.kamus.fragment.Fragment_eng_id;
import com.farhanrinsky.kamus.fragment.Fragment_id_eng;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottom_home:
                        displaySelectedScreen(R.id.nav_home);
                        bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case R.id.bottom_kamus:
                        displaySelectedScreen(R.id.nav_kamus_id);
                        bottomNavigationView.getMenu().findItem(R.id.bottom_kamus).setChecked(true);
                        break;
                    case R.id.bottom_audio:
                        displaySelectedScreen(R.id.nav_audio);
                        bottomNavigationView.getMenu().findItem(R.id.bottom_audio).setChecked(true);
                        break;
                    case R.id.bottom_about:
                        displaySelectedScreen(R.id.nav_about);
                        bottomNavigationView.getMenu().findItem(R.id.bottom_about).setChecked(true);
                        break;
                }
                return false;
            }
        });
        displaySelectedScreen(R.id.nav_home);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    public void displaySelectedScreen(int itemId) {
        Fragment fragment = null;
        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeActivity();
                break;
            case R.id.nav_kamus_id:
                fragment = new Fragment_id_eng();
                break;
            case R.id.nav_kamus_eng:
                fragment = new Fragment_eng_id();
                break;
            case R.id.nav_audio:
                fragment = new AudioActivity();
                break;
            case R.id.nav_about:
                fragment = new AboutActivity();
                break;
            case R.id.kamus_id:
                fragment = new Fragment_id_eng();
                break;
            case R.id.kamus_eng:
                fragment = new Fragment_eng_id();
                break;
            case R.id.exit:
                finishAffinity();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.kamus_id)
        {
            displaySelectedScreen(R.id.kamus_id);
        }
        if (item.getItemId()==R.id.kamus_eng)
        {
            displaySelectedScreen(R.id.kamus_eng);
        }
        return true;
    }

}