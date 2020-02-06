package com.nexttech.easybusinesscard.Job.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.nexttech.easybusinesscard.Job.Activity.MainActivity;
import com.nexttech.easybusinesscard.R;


public class EmployerSignup extends Fragment {

    Context context;
    EditText empFirstName, empLastName, empUsername, empCompanyName, empPassword, empEmail, empMobile, empCountry;
    Button empSignUp;
    CheckBox empCheckbox;
    TextView empLogIn, terms;
    View view;

    public EmployerSignup(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_employer_signup, container,false);

        empFirstName = view.findViewById(R.id.employer_firstName);
        empLastName = view.findViewById(R.id.employer_lastName);
        empUsername = view.findViewById(R.id.employer_userName);
        empCompanyName = view.findViewById(R.id.employer_company_name);
        empPassword = view.findViewById(R.id.employer_password);
        empEmail = view.findViewById(R.id.employer_email);
        empMobile = view.findViewById(R.id.employer_mobile);
        empCountry = view.findViewById(R.id.employer_country);

        empLogIn = view.findViewById(R.id.employer_logIn);
        terms = view.findViewById(R.id.employer_terms);

        empCheckbox = view.findViewById(R.id.employer_checkbox);

        empSignUp = view.findViewById(R.id.btn_employer_signUp);

        empLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.viewPager.setCurrentItem(0);
            }
        });

        empSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }


}
