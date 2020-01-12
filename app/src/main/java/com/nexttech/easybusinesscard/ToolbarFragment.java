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
import android.widget.TextView;


public class ToolbarFragment extends Fragment {


    TextView text,icon,image,qrcode,preview,backside,text2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vi= getLayoutInflater().inflate(R.layout.fragment_toolbar,null);
        text=vi.findViewById(R.id.textFieldTextview);

        icon=vi.findViewById(R.id.icon);
        image=vi.findViewById(R.id.image);
        qrcode=vi.findViewById(R.id.qrcode);
        preview=vi.findViewById(R.id.preview);
        backside=vi.findViewById(R.id.backside);
        text2=vi.findViewById(R.id.text2);



        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView=new TextView(getContext());
                textView.setTag("draggable textview");
                textView.setText("type your text here");
                textView.setOnLongClickListener(new LongPresslistener(getContext()));

                Create_card.viewPager.setCurrentItem(1);
                Create_card.mAdapter.notifyDataSetChanged();

               Create_card.absoluteLayout.addView(textView);
            }
        });



        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(2);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(3);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(4);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(5);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });


        backside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(6);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(7);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });
        return vi;
    }
}
