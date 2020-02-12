package com.nexttech.easybusinesscard.Job.Fragment;

import android.content.Context;
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


import com.google.firebase.auth.FirebaseAuth;
import com.nexttech.easybusinesscard.Job.Activity.MainActivity;
import com.nexttech.easybusinesscard.Job.Model.UserInfoModel;
import com.nexttech.easybusinesscard.R;


public class EmployeeSignUp extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    Context context;
    EditText empFirstName, empLastName, empUsername, empPassword, empEmail, empMobile, empCountry;
    Button empSignUp;
    CheckBox empCheckbox;
    TextView empLogIn, terms;
    View view;

    String emailInput, FnameInput, LnameInput, UnameInput, PassInput, MobileInput, CountryInput;


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

        String phoneNumber = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();

        phoneNumber = phoneNumber.replace("+88","");

        empMobile.setText(phoneNumber);
        empMobile.setEnabled(false);

        empLogIn = view.findViewById(R.id.employee_logIn);
        terms = view.findViewById(R.id.employee_terms);

        empCheckbox = view.findViewById(R.id.employee_checkbox);

        empSignUp = view.findViewById(R.id.btn_employee_signUp);

        empLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity.viewPager.setCurrentItem(0);
            }
        });

        empSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailInput = empEmail.getText().toString().trim();
                FnameInput = empFirstName.getText().toString().trim();
                LnameInput = empLastName.getText().toString().trim();
                UnameInput = empUsername.getText().toString().trim();
                PassInput = empPassword.getText().toString().trim();
                MobileInput = empMobile.getText().toString().trim();
                CountryInput = empCountry.getText().toString().trim();

                if (validateEmail())
                {
                    Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();

                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    UserInfoModel userInfoModel = new UserInfoModel(userId, FnameInput, LnameInput, "Employee", UnameInput, "", PassInput, emailInput, MobileInput, CountryInput);

                    MainActivity.userInfoSaveInFirebase(userInfoModel);

                    //AuthenticationActivity.viewPager.setCurrentItem(4);
                }
            }
        });

        return view;
    }

    private boolean validateEmail() {

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
