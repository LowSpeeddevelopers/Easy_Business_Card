package com.nexttech.easybusinesscard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class EmployeeSignUp extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    Context context;
    public EmployeeSignUp(Context context){
        this.context=context;
    }
    EditText empFirstName, empLastName, empUsername, empPassword, empEmail, empMobile, empCountry;
    Button empSignUp;
    CheckBox empCheckbox;
    TextView empLogIn, terms;
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_employee_sign_up, container,false);

        empFirstName = view.findViewById(R.id.employee_firstName);
        empLastName = view.findViewById(R.id.employee_lastName);
        empUsername = view.findViewById(R.id.employee_userName);
        empPassword = view.findViewById(R.id.employee_password);
        empEmail = view.findViewById(R.id.employee_email);
        empMobile = view.findViewById(R.id.employee_mobile);
        empCountry = view.findViewById(R.id.employee_country);

        empLogIn = view.findViewById(R.id.employee_logIn);
        terms = view.findViewById(R.id.employee_terms);

        empCheckbox = view.findViewById(R.id.employee_checkbox);

        empSignUp = view.findViewById(R.id.btn_employee_signUp);

        empSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
