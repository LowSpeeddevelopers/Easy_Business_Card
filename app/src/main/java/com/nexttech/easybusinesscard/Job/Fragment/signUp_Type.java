package com.nexttech.easybusinesscard.Job.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nexttech.easybusinesscard.Job.Activity.MainActivity;
import com.nexttech.easybusinesscard.R;


public class signUp_Type extends Fragment {

    Button btnSignupEmployee, btnSignupEmployer;

    public signUp_Type(Context context) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up__type, container, false);

        btnSignupEmployee = view.findViewById(R.id.btn_signUp_employee);
        btnSignupEmployer = view.findViewById(R.id.btn_signUp_employer);

        btnSignupEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity.viewPager.setCurrentItem(3);
            }
        });

        btnSignupEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity.viewPager.setCurrentItem(4);
            }
        });

        return view;
    }

}
