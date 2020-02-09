package com.nexttech.easybusinesscard.Job.Fragment;

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

import com.google.firebase.auth.FirebaseAuth;
import com.nexttech.easybusinesscard.Job.Activity.MainActivity;
import com.nexttech.easybusinesscard.Job.Verification;
import com.nexttech.easybusinesscard.R;

public class Phone_number_Edit extends Fragment {

    Context context;

    EditText edtMobileNumber;
    Verification verification;


    public Phone_number_Edit(Context context,Verification verification) {
        this.context = context;
        this.verification=verification;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone_number__edit, container, false);
        edtMobileNumber = view.findViewById(R.id.edt_mobile_number);

        if(!MainActivity.employeeInfoModel.equals(null)){
            edtMobileNumber.setText(MainActivity.employeeInfoModel.getMobileNumber());
        } else if(!MainActivity.employerInfoModel.equals(null)){
            edtMobileNumber.setText(MainActivity.employerInfoModel.getMobileNumber());
        }

        view.findViewById(R.id.btn_send_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = edtMobileNumber.getText().toString().trim();
                if(!MainActivity.employeeInfoModel.equals(null)){
                    MainActivity.employeeInfoModel.setMobileNumber(number);
                } else if(!MainActivity.employerInfoModel.equals(null)){
                    MainActivity.employerInfoModel.setMobileNumber(number);
                }
                MainActivity.viewPager.setCurrentItem(5);
                verification.sendVerificationCode(number);

            }
        });

        return view;
    }





}
