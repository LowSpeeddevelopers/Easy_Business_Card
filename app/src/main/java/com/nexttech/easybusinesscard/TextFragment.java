package com.nexttech.easybusinesscard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends Fragment {

    TextView text, textSize, textFont, textColor, textStyle, textBackground;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =getLayoutInflater().inflate(R.layout.fragment_text, null);

        text = view.findViewById(R.id.text);
        textSize = view.findViewById(R.id.textSize);
        textFont = view.findViewById(R.id.textFont);
        textColor = view.findViewById(R.id.textColor);
        textStyle = view.findViewById(R.id.textStyle);
        textBackground = view.findViewById(R.id.textBsckground);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView=new TextView(getContext());
                textView.setTag("draggable textview");
                textView.setText("type your text here");
                textView.setOnLongClickListener(new LongPresslistener(getContext()));

                Create_card.viewPager.setCurrentItem(1);
                Create_card.mAdapter.notifyDataSetChanged();

                //Create_card.absoluteLayout.addView(textView);
            }
        });



        textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(2);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });

        textFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(3);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });

        textColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(4);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });

        textStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(5);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });


        textBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(6);
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
