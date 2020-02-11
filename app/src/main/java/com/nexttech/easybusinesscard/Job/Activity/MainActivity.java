package com.nexttech.easybusinesscard.Job.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nexttech.easybusinesscard.Job.Fragment.EmployeeSignUp;
import com.nexttech.easybusinesscard.Job.Fragment.EmployerSignup;
import com.nexttech.easybusinesscard.Job.Fragment.Login_fragment;
import com.nexttech.easybusinesscard.Job.Fragment.OTP_verification;
import com.nexttech.easybusinesscard.Job.Fragment.Phone_number_Edit;
import com.nexttech.easybusinesscard.Job.Fragment.PrivacyFragment;
import com.nexttech.easybusinesscard.Job.Fragment.Profile_settings;
import com.nexttech.easybusinesscard.Job.Fragment.SecurityFragment;
import com.nexttech.easybusinesscard.Job.Fragment.SettingsFragment;
import com.nexttech.easybusinesscard.Job.Fragment.signUp_Type;
import com.nexttech.easybusinesscard.Job.Model.EmployeeInfoModel;
import com.nexttech.easybusinesscard.Job.Model.EmployerInfoModel;
import com.nexttech.easybusinesscard.Job.Utils.NonSwipeableViewPager;
import com.nexttech.easybusinesscard.Job.Verification;
import com.nexttech.easybusinesscard.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    LinearLayout bottomnav;
    private ActionBarDrawerToggle t;
    private NavigationView nv;


    FirebaseAuth firebaseAuth;
    static FirebaseUser firebaseUser;
    Verification verification;
    public static String mobileNumber;
    AlertDialog.Builder builder;
     static DatabaseReference database;
    ImageView bagbutton,chatbutton,cardbutton,profilebutton;
    FrameLayout container;
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
        }else {
            if(firebaseUser!=null){
                database.child("Users").child("Employer").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){

                        }else {
                            Bundle b = new Bundle();
                            b.putString("phone",firebaseUser.getPhoneNumber());
                            Fragment f = new signUp_Type(MainActivity.this);
                            f.setArguments(b);

                            getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }else {
                Fragment f=new Login_fragment(this,verification);

                getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl = findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        container=findViewById(R.id.container);

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
        database = FirebaseDatabase.getInstance().getReference();
        context = this;


        nv = findViewById(R.id.nv);
        bottomnav = findViewById(R.id.bottomnav);


        


        nv.setVisibility(View.GONE);
        bottomnav.setVisibility(View.GONE);





        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "Home",Toast.LENGTH_SHORT).show();break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();break;
                    case R.id.notification:
                        Toast.makeText(MainActivity.this, "Notification",Toast.LENGTH_SHORT).show();break;
                    case R.id.invite_friends:
                        Toast.makeText(MainActivity.this, "Invite friends",Toast.LENGTH_SHORT).show();break;
                    case R.id.help:
                        Toast.makeText(MainActivity.this, "Help and Support",Toast.LENGTH_SHORT).show();break;
                    case R.id.about_us:
                        Toast.makeText(MainActivity.this, "About us",Toast.LENGTH_SHORT).show();break;
                    case R.id.privacy_policy:
                        Toast.makeText(MainActivity.this, "Privacy Policy",Toast.LENGTH_SHORT).show();break;
                    case R.id.log_out:
                        Toast.makeText(MainActivity.this, "Log out",Toast.LENGTH_SHORT).show();break;
                    default:
                        return true;
                }


                return true;

            }
        });

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
        if(firebaseUser!=null){
            nv.setVisibility(View.VISIBLE);
            bottomnav.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
    public static void employerInfoSaveInFirebase(EmployerInfoModel employerInfoModel){
        String userId = firebaseUser.getUid();
        DatabaseReference myRef = database.child("Users").child("Employer").child(userId);
        myRef.setValue(employerInfoModel);
        Toast.makeText(context, "Employer Information Insert Successfully", Toast.LENGTH_SHORT).show();
    }
    public static void employeeInfoSaveInFirebase(EmployeeInfoModel employeeInfoModel){
        String userId = firebaseUser.getUid();
        DatabaseReference myRef = database.child("Users").child("Employee").child(userId);
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
}
