package com.nexttech.easybusinesscard.Job.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nexttech.easybusinesscard.R;


public class signUp_Type extends Fragment {

    Button btnSignupEmployee, btnSignupEmployer;

    View view;

    public signUp_Type(Context context) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up__type, container, false);

        btnSignupEmployee = view.findViewById(R.id.btn_signUp_employee);
        btnSignupEmployer = view.findViewById(R.id.btn_signUp_employer);


        btnSignupEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new EmployeeSignUp(getContext()));
            }
        });

        btnSignupEmployer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new EmployerSignup(getContext()));
            }
        });

        return view;
    }

    private void openFragment(Fragment fragment){
        ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();
    }

}
