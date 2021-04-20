package com.example.studnetinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;



import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewpager;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbarId);
        tabLayout = findViewById(R.id.tablayoutId);
        viewpager = findViewById(R.id.viewpagerId);

        setSupportActionBar(toolbar);

        tabLayout.addTab(tabLayout.newTab().setText("Academic Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Private info"));

        if (isNetworkAvailable()) {
            connect();
        } else {
            snakbar();
            }

        }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    void snakbar(){

        Snackbar.make(findViewById(R.id.mActivity), "Network Error...", Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isNetworkAvailable()) {
                    snakbar();

                }
                else {
                    connect();
                }
            }
        }).setActionTextColor(getResources().getColor(android.R.color.darker_gray)).setDuration(800000).show();
    }
    void connect(){
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());


        viewpager.setAdapter(fragmentAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
