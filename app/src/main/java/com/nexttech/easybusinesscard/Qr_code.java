package com.nexttech.easybusinesscard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Qr_code extends Fragment {
    TextView qr_size,qr_generate,qr_from_cv;
    Context context;


    public Qr_code(Context context) {
        this.context=context;
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_qr_code, container, false);
        View view = getLayoutInflater().inflate(R.layout.fragment_qr_code, null);

        qr_size=view.findViewById(R.id.qrcode_size);
        qr_generate=view.findViewById(R.id.qrcode_generate);
        qr_from_cv=view.findViewById(R.id.qrcode_from_cv);



        qr_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "clicked on Size", Toast.LENGTH_SHORT).show();
            }
        });

        qr_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked On Generate QR", Toast.LENGTH_SHORT).show();
            }
        });

        qr_from_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked on From CV", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
