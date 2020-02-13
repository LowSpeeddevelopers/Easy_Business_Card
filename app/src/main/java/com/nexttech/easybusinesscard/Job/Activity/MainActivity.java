package com.nexttech.easybusinesscard.Job.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.tv.TvContentRating;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nexttech.easybusinesscard.BusinessCard.Activity.Create_card;
import com.nexttech.easybusinesscard.Job.Fragment.HomeFragment;
import com.nexttech.easybusinesscard.Job.Fragment.Login_fragment;
import com.nexttech.easybusinesscard.Job.Fragment.Profile_settings;
import com.nexttech.easybusinesscard.Job.Fragment.SecurityFragment;
import com.nexttech.easybusinesscard.Job.Fragment.SettingsFragment;
import com.nexttech.easybusinesscard.Job.Fragment.signUp_Type;
import com.nexttech.easybusinesscard.Job.Model.UserInfoModel;
import com.nexttech.easybusinesscard.Job.Verification;
import com.nexttech.easybusinesscard.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    LinearLayout bottomnav;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    ProgressBar progressBar;

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

    TextView tvUsername, tvEmail;


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
            progressBar.setVisibility(View.GONE);
        }else {
            setFragment();
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
        progressBar = findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.VISIBLE);

        toolbartext.setText("LOG IN");
        firebaseAuth=FirebaseAuth.getInstance();
        //userId=firebaseAuth.getCurrentUser().getUid();
        firebaseUser=firebaseAuth.getCurrentUser();
        verification=new Verification(this,firebaseAuth);
        database = FirebaseDatabase.getInstance().getReference();
        context = this;

        nv = findViewById(R.id.nv);
        bottomnav = findViewById(R.id.bottomnav);


        TextView Title = nv.findViewById(R.id.title);
        TextView email = nv.findViewById(R.id.email);
        TextView onlinestatus = nv.findViewById(R.id.onlinestatus);
        final TextView coinCount = nv.findViewById(R.id.coin);
        TextView home = nv.findViewById(R.id.home);
        TextView settings = nv.findViewById(R.id.settings);
        final TextView notification = nv.findViewById(R.id.notifications);
        TextView inviteFriens = nv.findViewById(R.id.invite_friends);
        TextView HelpandSupport = nv.findViewById(R.id.help_and_support);
        TextView about = nv.findViewById(R.id.about_us);
        TextView privacy = nv.findViewById(R.id.privacy_policy);
        TextView Logout = nv.findViewById(R.id.logout);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.closeDrawer(GravityCompat.START,true);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(getLayoutInflater().inflate(R.layout.notification,null,false));
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                alertDialog.setCanceledOnTouchOutside(true);

                dl.closeDrawer(GravityCompat.START,true);

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new SettingsFragment(MainActivity.this)).commit();
                dl.closeDrawer(GravityCompat.START,true);
            }
        });
        inviteFriens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "home selected", Toast.LENGTH_SHORT).show();
                dl.closeDrawer(GravityCompat.START,true);
            }
        });
        HelpandSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "home selected", Toast.LENGTH_SHORT).show();
                dl.closeDrawer(GravityCompat.START,true);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "home selected", Toast.LENGTH_SHORT).show();
                dl.closeDrawer(GravityCompat.START,true);
            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "home selected", Toast.LENGTH_SHORT).show();
                dl.closeDrawer(GravityCompat.START,true);
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "home selected", Toast.LENGTH_SHORT).show();
                dl.closeDrawer(GravityCompat.START,true);
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


                /// on bag button click perform here
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


                //chat page perform here
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

                startActivity(new Intent(MainActivity.this, Create_card.class));
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
    public static void userInfoSaveInFirebase(UserInfoModel userInfoModel){
        DatabaseReference myRef = database.child("Users").child(userInfoModel.getUserId());
        myRef.setValue(userInfoModel);
        Toast.makeText(context, "User Information Insert Successfully", Toast.LENGTH_SHORT).show();
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

    public void setFragment(){
        firebaseUser = firebaseAuth.getCurrentUser();

        nv.setVisibility(View.GONE);
        bottomnav.setVisibility(View.GONE);

        if(firebaseUser!=null){
            database.child("Users").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){

                        UserInfoModel userInfoModel = dataSnapshot.getValue(UserInfoModel.class);

                        String fullname = userInfoModel.getFirstName()+" "+userInfoModel.getLastName();
//                        tvUsername.setText(fullname);
//                        tvEmail.setText(userInfoModel.getEmail());

                        Fragment fragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                        progressBar.setVisibility(View.GONE);
                        nv.setVisibility(View.VISIBLE);
                        bottomnav.setVisibility(View.VISIBLE);
                    }else {
                        Fragment f = new signUp_Type(MainActivity.this);

                        getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();

                        progressBar.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else {
            Fragment f=new Login_fragment(this,verification);

            getSupportFragmentManager().beginTransaction().replace(R.id.container,f).commit();

            progressBar.setVisibility(View.GONE);
        }
    }
}
