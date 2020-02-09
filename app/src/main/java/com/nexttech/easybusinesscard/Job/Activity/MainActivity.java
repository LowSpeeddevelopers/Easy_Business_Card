package com.nexttech.easybusinesscard.Job.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.nexttech.easybusinesscard.Job.Fragment.OTP_verification;
import com.nexttech.easybusinesscard.Job.Fragment.Phone_number_Edit;
import com.nexttech.easybusinesscard.Job.Fragment.PrivacyFragment;
import com.nexttech.easybusinesscard.Job.Fragment.Profile_settings;
import com.nexttech.easybusinesscard.Job.Fragment.SecurityFragment;
import com.nexttech.easybusinesscard.Job.Fragment.SettingsFragment;
import com.nexttech.easybusinesscard.Job.Model.EmployeeInfoModel;
import com.nexttech.easybusinesscard.Job.Model.EmployerInfoModel;
import com.nexttech.easybusinesscard.Job.Utils.NonSwipeableViewPager;
import com.nexttech.easybusinesscard.Job.Verification;
import com.nexttech.easybusinesscard.R;
import com.nexttech.easybusinesscard.Job.Fragment.signUp_Type;


public class MainActivity extends AppCompatActivity {

    public static NonSwipeableViewPager viewPager;
    public static EmployerInfoModel employerInfoModel;
    public static EmployeeInfoModel employeeInfoModel;

    FirebaseAuth firebaseAuth;
    String userId;
    FirebaseUser firebaseUser;
    Verification verification;

    ImageView bagbutton,chatbutton,cardbutton,profilebutton;
    boolean isSelected = false;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(null);
        Toolbar toolbar = findViewById(R.id.toolbarMainActivity);
        TextView toolbartext = toolbar.findViewById(R.id.toolbartext);
        bagbutton=findViewById(R.id.bagbutton);
        chatbutton=findViewById(R.id.chatbutton);
        cardbutton=findViewById(R.id.cardbutton);
        profilebutton=findViewById(R.id.profilebutton);
        toolbartext.setText("LOG IN");
        firebaseAuth=FirebaseAuth.getInstance();
        //userId=firebaseAuth.getCurrentUser().getUid();
        firebaseUser=firebaseAuth.getCurrentUser();
        verification=new Verification(this,firebaseAuth);

        viewPager = findViewById(R.id.container);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        bagbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected){
                    view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    bagbutton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    if(view == cardbutton){
                        cardbutton.setImageDrawable(getResources().getDrawable(R.drawable.cardicon));

                    }else if(view == chatbutton){
                        chatbutton.setImageDrawable(getResources().getDrawable(R.drawable.chaticon));
                    }else if(view == profilebutton){
                        profilebutton.setImageDrawable(getResources().getDrawable(R.drawable.profileicon));
                    }
                    bagbutton.setImageDrawable(getResources().getDrawable(R.drawable.bagiconwhite));
                    isSelected=true;
                    view=bagbutton;

                }else {
                    bagbutton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    bagbutton.setImageDrawable(getResources().getDrawable(R.drawable.bagiconwhite));
                    isSelected=true;
                    view=bagbutton;
                }
            }
        });
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected){
                    view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    chatbutton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


                    if(view == cardbutton){
                        cardbutton.setImageDrawable(getResources().getDrawable(R.drawable.cardicon));
                    }else if(view == profilebutton){
                        profilebutton.setImageDrawable(getResources().getDrawable(R.drawable.profileicon));
                    }else if(view == bagbutton){
                        bagbutton.setImageDrawable(getResources().getDrawable(R.drawable.bagicon));
                    }

                    chatbutton.setImageDrawable(getResources().getDrawable(R.drawable.chaticonwhite));
                    isSelected=true;
                    view=chatbutton;

                }else {
                    chatbutton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    chatbutton.setImageDrawable(getResources().getDrawable(R.drawable.chaticonwhite));
                    isSelected=true;
                    view=chatbutton;
                }
            }
        });
        cardbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected){
                    view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    cardbutton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                     if(view == chatbutton){
                        chatbutton.setImageDrawable(getResources().getDrawable(R.drawable.chaticon));
                    }else if(view == profilebutton){
                        profilebutton.setImageDrawable(getResources().getDrawable(R.drawable.profileicon));
                    }else if(view == bagbutton){
                        bagbutton.setImageDrawable(getResources().getDrawable(R.drawable.bagicon));
                    }
                    cardbutton.setImageDrawable(getResources().getDrawable(R.drawable.cardiconwhite));
                    isSelected=true;
                    view=cardbutton;
                }else {
                    cardbutton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    cardbutton.setImageDrawable(getResources().getDrawable(R.drawable.cardiconwhite));
                    isSelected=true;
                    view=cardbutton;
                }
            }
        });
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected){
                    view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    profilebutton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                    if(view == cardbutton){
                        cardbutton.setImageDrawable(getResources().getDrawable(R.drawable.cardicon));
                    }else if(view == chatbutton){
                        chatbutton.setImageDrawable(getResources().getDrawable(R.drawable.chaticon));
                    }else if(view == bagbutton){
                        bagbutton.setImageDrawable(getResources().getDrawable(R.drawable.bagicon));
                    }
                    profilebutton.setImageDrawable(getResources().getDrawable(R.drawable.profileiconwhite));
                    isSelected=true;
                    view=profilebutton;

                }else {
                    profilebutton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    profilebutton.setImageDrawable(getResources().getDrawable(R.drawable.profileiconwhite));
                    isSelected=true;
                    view=profilebutton;
                }
            }
        });
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
                case 4:
                    return new Phone_number_Edit(MainActivity.this,verification);
                case 5:
                    return new OTP_verification(MainActivity.this,verification);
                case 6:
                    return new Profile_settings();
                case 7:
                    return new PrivacyFragment();
                case 8:
                    return new SecurityFragment();
                default:
                    return new SettingsFragment(MainActivity.this);
            }
        }

        @Override
        public int getCount() {
            return 9;
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
