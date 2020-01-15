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

public class ImageFragment extends Fragment {
    Button browse;
    Context context;
    public ImageFragment(Context context){
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_image, null);
        browse=view.findViewById(R.id.btn_browse);
        //size=view.findViewById(R.id.btn_size);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Create_card)getActivity()).imageDialoguebox();
            }
        });
        /*size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Size",Toast.LENGTH_LONG).show();
            }
        });*/
        return view;
    }


}
