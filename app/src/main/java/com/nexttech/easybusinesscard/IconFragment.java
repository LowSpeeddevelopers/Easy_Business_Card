package com.nexttech.easybusinesscard;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


public class IconFragment extends Fragment {
    Button select;
    Context context;

    public IconFragment(Context context){
        this.context=context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_icon, null);
        select=view.findViewById(R.id.btn_select);
        //size=view.findViewById(R.id.btn_size);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Create_card)getActivity()).iconDialoguebox();
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