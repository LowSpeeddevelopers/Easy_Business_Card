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
import com.nexttech.easybusinesscard.Job.Verification;
import com.nexttech.easybusinesscard.R;


public class Login_fragment extends Fragment {

    Context context;
    Verification verification;

    EditText email,password;
    TextView forgetpass,signup,login;


    public Login_fragment(Context context, Verification verification) {
        this.context = context;
        this.verification=verification;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vi = inflater.inflate(R.layout.fragment_login, container, false);

        email = vi.findViewById(R.id.login_email);
        password= vi.findViewById(R.id.login_pass);

        signup=vi.findViewById(R.id.signuptext);
        login=vi.findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = email.getText().toString().trim();
                MainActivity.mobileNumber = number;
                MainActivity.viewPager.setCurrentItem(1);
                verification.sendVerificationCode(number);
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
