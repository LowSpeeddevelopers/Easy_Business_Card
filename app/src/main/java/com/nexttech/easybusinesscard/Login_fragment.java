package com.nexttech.easybusinesscard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login_fragment extends Fragment {


    public Login_fragment(Context context) {


    }
    View  vi;
    EditText email,Password;
    Button login;
    TextView forgetpass,signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi = inflater.inflate(R.layout.fragment_login, container, false);

        signup=vi.findViewById(R.id.signuptext);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.viewPager.setCurrentItem(1);
            }
        });



        return vi;
    }


}
