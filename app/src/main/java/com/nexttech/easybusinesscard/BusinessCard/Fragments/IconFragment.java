package com.nexttech.easybusinesscard.BusinessCard.Fragments;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nexttech.easybusinesscard.BusinessCard.Activity.Create_card;
import com.nexttech.easybusinesscard.BusinessCard.Utils.LongPresslistener;
import com.nexttech.easybusinesscard.R;


public class IconFragment extends Fragment {
    private TextView save,select;
    private EditText size;
    Context context;
    private ImageView  icon_1,icon_2,icon_3,icon_4,icon_5,icon_6,icon_7,icon_8;
    private SeekBar seekBar;
    private ImageView imageView;
    private AlertDialog alertDialog;



    public IconFragment(Context context){
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_icon, container,false);
        select=view.findViewById(R.id.select);
        seekBar=view.findViewById(R.id.seekbar);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iconDialoguebox();

            }
        });




        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                AbsoluteLayout.LayoutParams imageParams2 = new AbsoluteLayout.LayoutParams(progress, progress, imageView.getScrollX(), imageView.getScrollY());
                imageView.setLayoutParams(imageParams2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }


    private int iconDialoguebox(){
       AlertDialog.Builder builder =new AlertDialog.Builder(context);

        View dialogueView=getLayoutInflater().inflate(R.layout.select_icon,null);
        icon_1 = dialogueView.findViewById(R.id.icon1);
        icon_2= dialogueView.findViewById(R.id.icon2);
        icon_3= dialogueView.findViewById(R.id.icon3);
        icon_4= dialogueView.findViewById(R.id.icon4);
        icon_5= dialogueView.findViewById(R.id.icon5);
        icon_6= dialogueView.findViewById(R.id.icon6);
        icon_7= dialogueView.findViewById(R.id.icon7);
        icon_8= dialogueView.findViewById(R.id.icon8);
        icon_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_1);
            }
        });
        icon_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_2);

            }
        });
        icon_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_3);

            }
        });
        icon_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_4);

            }
        });
        icon_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_5);
            }
        });
        icon_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_6);

            }
        });
        icon_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_7);

            }
        });
        icon_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(icon_8);

            }
        });

        builder.setView(null);
        builder.setView(dialogueView);
        alertDialog=builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialogDismiss();
        alertDialog.show();

        return 12;
    }
    private void setImage(ImageView image) {
        imageView=new ImageView(context);
        imageView.setTag(String.valueOf(ToolbarFragment.imageiconcounter));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seekBar.getVisibility() == View.GONE){
                    seekBar.setVisibility(View.VISIBLE);
                }
                Create_card.setCurrentFragmentwithData(2,imageView.getTag().toString());
                Create_card.mAdapter.notifyDataSetChanged();
            }
        });
        ViewGroup.LayoutParams imageParams = new LinearLayout.LayoutParams(30, 30);
        imageView.setLayoutParams(imageParams);
        seekBar.setProgress(30);
        ToolbarFragment.imageiconcounter++;
        imageView.setImageDrawable(image.getDrawable());
        imageView.setOnLongClickListener(new LongPresslistener(context));

        ToolbarFragment.addView(imageView);
        ToolbarFragment.addIconView(imageView);
        seekBar.setVisibility(View.VISIBLE);
        Create_card.viewPager.setCurrentItem(2);
        Create_card.mAdapter.notifyDataSetChanged();

        alertDialogDismiss();
    }


    private void alertDialogDismiss(){
        if (alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }
}