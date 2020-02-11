package com.nexttech.easybusinesscard.Job.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.nexttech.easybusinesscard.Job.Verification;
import com.nexttech.easybusinesscard.R;


public class OTP_verification extends Fragment {

    Context context;


    public static EditText editTextCode;
    private FirebaseAuth mAuth;

    Verification verification;
    TextView phonenumber;


    public OTP_verification(Context context,Verification verification) {
        this.context = context;
       this. verification=verification;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_otp_verification_mobile, container, false);
        mAuth = FirebaseAuth.getInstance();

        editTextCode = view.findViewById(R.id.edt_text_code);
        phonenumber=view.findViewById(R.id.phonenumber);


        String s = getArguments().getString("phone");

        phonenumber.setText(s);

        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button
        view.findViewById(R.id.btn_sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }
                //verifying the code entered manually
                verification.verifyVerificationCode(code);

            }
        });
        return view;
    }

    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well

}
