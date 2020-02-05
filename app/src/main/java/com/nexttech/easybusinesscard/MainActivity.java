package com.nexttech.easybusinesscard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    FirebaseAuth firebaseAuth;
    String userId;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(null);
        Toolbar toolbar = findViewById(R.id.toolbarMainActivity);
        TextView toolbartext = toolbar.findViewById(R.id.toolbartext);
        toolbartext.setText("LOG IN");
        firebaseAuth=FirebaseAuth.getInstance();
        //userId=firebaseAuth.getCurrentUser().getUid();
        firebaseUser=firebaseAuth.getCurrentUser();

        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {

            switch (pos){
                case 0:
                   return new Login_fragment(MainActivity.this);
                case 1:
                    return new signUp_Type(MainActivity.this);
                default:
                    return new EmployeeSignUp(MainActivity.this);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        if(firebaseUser!=null){
            startActivity(new Intent(this,Create_card.class));
        }else {
            viewPager.setCurrentItem(0);
        }
    }
}
