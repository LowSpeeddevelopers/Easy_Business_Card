package com.nexttech.easybusinesscard.Job.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

//        if(!AuthenticationActivity.employeeInfoModel.equals(null)){
//            edtMobileNumber.setText(AuthenticationActivity.employeeInfoModel.getMobileNumber());
//        } else if(!AuthenticationActivity.employerInfoModel.equals(null)){
//            edtMobileNumber.setText(AuthenticationActivity.employerInfoModel.getMobileNumber());
//        }

        view.findViewById(R.id.btn_send_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String number = edtMobileNumber.getText().toString().trim();
//                if(!AuthenticationActivity.employeeInfoModel.equals(null)){
//                    AuthenticationActivity.employeeInfoModel.setMobileNumber(number);
//                } else if(!AuthenticationActivity.employerInfoModel.equals(null)){
//                    AuthenticationActivity.employerInfoModel.setMobileNumber(number);
//                }
//                AuthenticationActivity.viewPager.setCurrentItem(5);
//                verification.sendVerificationCode(number);

            }
        });

        return view;
    }





}
