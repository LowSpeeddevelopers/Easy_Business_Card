package com.nexttech.easybusinesscard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(null);
        Toolbar toolbar = findViewById(R.id.toolbarMainActivity);

        TextView toolbartext = toolbar.findViewById(R.id.toolbartext);
        toolbartext.setText("LOG IN");
        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {

            return new signUp_Type(MainActivity.this);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

}
