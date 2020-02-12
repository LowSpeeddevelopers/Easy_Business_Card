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
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.nexttech.easybusinesscard.Job.Activity.MainActivity;
import com.nexttech.easybusinesscard.Job.Model.UserInfoModel;
import com.nexttech.easybusinesscard.R;


public class EmployerSignup extends Fragment {

    Context context;
    EditText empFirstName, empLastName, empUsername, empCompanyName, empPassword, empEmail, empMobile, empCountry;
    Button empSignUp;
    CheckBox empCheckbox;
    TextView empLogIn, terms;
    View view;

    UserInfoModel userInfoModel;

    String emailInput, FnameInput, LnameInput, UnameInput, PassInput, MobileInput, CountryInput, CompanyInput;

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

        String phoneNumber = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();

        phoneNumber = phoneNumber.replace("+88","");

        empMobile.setText(phoneNumber);
        empMobile.setEnabled(false);

        empLogIn = view.findViewById(R.id.employer_logIn);
        terms = view.findViewById(R.id.employer_terms);

        empCheckbox = view.findViewById(R.id.employer_checkbox);

        empSignUp = view.findViewById(R.id.btn_employer_signUp);

        empLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AuthenticationActivity.viewPager.setCurrentItem(0);
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
                CompanyInput = empCompanyName.getText().toString().trim();

                if (validateEmail()) {
                    Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();

                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    UserInfoModel userInfoModel = new UserInfoModel(userId, FnameInput, LnameInput, "Employer", UnameInput, CompanyInput, PassInput, emailInput, MobileInput, CountryInput);

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
        if (CompanyInput.isEmpty()) {
            empCompanyName.setError("Field can't be empty");
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
