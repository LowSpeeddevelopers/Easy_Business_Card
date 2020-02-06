package com.nexttech.easybusinesscard.Job.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nexttech.easybusinesscard.BusinessCard.Activity.Create_card;
import com.nexttech.easybusinesscard.Job.Fragment.EmployeeSignUp;
import com.nexttech.easybusinesscard.Job.Fragment.EmployerSignup;
import com.nexttech.easybusinesscard.Job.Fragment.Login_fragment;
import com.nexttech.easybusinesscard.Job.Utils.NonSwipeableViewPager;
import com.nexttech.easybusinesscard.R;
import com.nexttech.easybusinesscard.Job.Fragment.signUp_Type;


public class MainActivity extends AppCompatActivity {

    public static NonSwipeableViewPager viewPager;

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
                case 2:
                    return new EmployeeSignUp(MainActivity.this);
                case 3:
                    return new EmployerSignup(MainActivity.this);
                default:
                    return new Login_fragment(MainActivity.this);
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        if(firebaseUser!=null){
            startActivity(new Intent(this, Create_card.class));
        }else {
            viewPager.setCurrentItem(0);
        }
    }
}
