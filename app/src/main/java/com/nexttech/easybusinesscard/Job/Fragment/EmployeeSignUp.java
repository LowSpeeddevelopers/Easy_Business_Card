package com.nexttech.easybusinesscard.Job.Fragment;

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
import android.widget.Toast;

import com.nexttech.easybusinesscard.Job.Activity.MainActivity;
import com.nexttech.easybusinesscard.R;


public class EmployeeSignUp extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    Context context;
    EditText empFirstName, empLastName, empUsername, empPassword, empEmail, empMobile, empCountry;
    Button empSignUp;
    CheckBox empCheckbox;
    TextView empLogIn, terms;
    View view;


    public EmployeeSignUp(Context context){
        this.context=context;
    }

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

        empLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.viewPager.setCurrentItem(0);
            }
        });

        empSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateEmail())
                {
                    Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private boolean validateEmail() {
        String emailInput = empEmail.getText().toString().trim();
        String FnameInput = empFirstName.getText().toString().trim();
        String LnameInput = empLastName.getText().toString().trim();
        String UnameInput = empUsername.getText().toString().trim();
        String PassInput = empPassword.getText().toString().trim();
        String MobileInput = empMobile.getText().toString().trim();
        String CountryInput = empCountry.getText().toString().trim();

        Boolean returnValue = true;

        if (emailInput.isEmpty()) {
            empEmail.setError("Field can't be empty");
            returnValue = false;
        }
        if (FnameInput.isEmpty()) {
            empFirstName.setError("Field can't be empty");
            returnValue = false;
        }
        if (LnameInput.isEmpty()) {
            empLastName.setError("Field can't be empty");
            returnValue = false;
        }
        if (UnameInput.isEmpty()) {
            empUsername.setError("Field can't be empty");
            returnValue = false;
        }
        if (PassInput.isEmpty()) {
            empPassword.setError("Field can't be empty");
            returnValue = false;
        }
        if (MobileInput.isEmpty()) {
            empMobile.setError("Field can't be empty");
            returnValue = false;
        }
        if (CountryInput.isEmpty()) {
            empCountry.setError("Field can't be empty");
            returnValue = false;
        }

        return returnValue;
    }

}