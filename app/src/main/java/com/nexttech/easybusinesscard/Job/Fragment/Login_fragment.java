package com.nexttech.easybusinesscard.Job.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nexttech.easybusinesscard.Job.Activity.MainActivity;
import com.nexttech.easybusinesscard.R;


public class Login_fragment extends Fragment {


    public Login_fragment(Context context) {


    }
    View  vi;
    EditText email,Password;
    Button login;
    TextView forgetpass,signup,login1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi = inflater.inflate(R.layout.fragment_login, container, false);

        signup=vi.findViewById(R.id.signuptext);
        login1=vi.findViewById(R.id.login_btn);
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(),job_navigation.class);
//                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.viewPager.setCurrentItem(1);
            }
        });



        return vi;
    }


}
