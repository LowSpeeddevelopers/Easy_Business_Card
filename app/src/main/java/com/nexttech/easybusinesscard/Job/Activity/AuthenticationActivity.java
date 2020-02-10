package com.nexttech.easybusinesscard.Job.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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


public class AuthenticationActivity extends AppCompatActivity {

    public static NonSwipeableViewPager viewPager;

    FirebaseAuth firebaseAuth;
    String userId;
    FirebaseUser firebaseUser;
    Verification verification;

    public static String mobileNumber;

    AlertDialog.Builder builder;

    static FirebaseDatabase database;

    ImageView bagbutton,chatbutton,cardbutton,profilebutton;
    boolean isSelected = false;
    View view;

    static Context context;

    @Override
    protected void onStart() {
        super.onStart();

        if (!hasConnection()){
            builder = new AlertDialog.Builder(this);

            //Uncomment the below code to Set the message and title from the strings.xml file
            builder.setMessage("Please check your internet connection...") .setTitle("No Internet");

            //Setting message manually and performing action on button click
            builder.setMessage("Please check your internet connection...")
                    .setCancelable(false)
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("No Internet");
            alert.show();
        }

        if(firebaseUser!=null){
            startActivity(new Intent(this, MainActivity.class));
        }else {
            viewPager.setCurrentItem(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
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

        database = FirebaseDatabase.getInstance();

        context = AuthenticationActivity.this;

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

    public static void employerInfoSaveInFirebase(EmployerInfoModel employerInfoModel){
        String contestId = String.valueOf(System.currentTimeMillis());

        DatabaseReference myRef = database.getReference("Users").child("Employer").child(contestId);

        myRef.setValue(employerInfoModel);

        Toast.makeText(context, "Employer Information Insert Successfully", Toast.LENGTH_SHORT).show();
    }

    public static void employeeInfoSaveInFirebase(EmployeeInfoModel employeeInfoModel){
        String contestId = String.valueOf(System.currentTimeMillis());

        DatabaseReference myRef = database.getReference("Users").child("Employee").child(contestId);

        myRef.setValue(employeeInfoModel);

        Toast.makeText(context, "Employee Information Insert Successfully", Toast.LENGTH_SHORT).show();
    }

    public boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {

            switch (pos){
                case 0:
                    return new Login_fragment(AuthenticationActivity.this, verification);
                case 1:
                    return new OTP_verification(AuthenticationActivity.this,verification);
                case 2:
                    return new signUp_Type(AuthenticationActivity.this);
                case 3:
                    return new EmployeeSignUp(AuthenticationActivity.this);
                case 4:
                    return new EmployerSignup(AuthenticationActivity.this);
                case 5:
                    return new Phone_number_Edit(AuthenticationActivity.this,verification);
                case 6:
                    return new Profile_settings();
                case 7:
                    return new PrivacyFragment();
                case 8:
                    return new SecurityFragment();
                default:
                    return new SettingsFragment(AuthenticationActivity.this);
            }
        }

        @Override
        public int getCount() {
            return 9;
        }
    }



}
