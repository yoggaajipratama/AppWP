package com.example.waterplants;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.waterplants.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Button btn_logout;
    TextView txt_id, txt_username;
    String id, username;
    SharedPreferences sharedpreferences;
    BottomNavigationView bottomNavigation;
    Fragment fragment;
    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_remote:
                            selectedFragment = new RemoteFragment();
                            break;
                        case R.id.nav_info:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_logout:
                            id = getIntent().getStringExtra(TAG_ID);
                            username = getIntent().getStringExtra(TAG_USERNAME);
                            sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putBoolean(Login.session_status, false);
                            editor.putString(TAG_ID, null);
                            editor.putString(TAG_USERNAME, null);
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, Login.class);
                            finish();
                            startActivity(intent);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).addToBackStack(null).commit();
                    return true;
                }
            };
}