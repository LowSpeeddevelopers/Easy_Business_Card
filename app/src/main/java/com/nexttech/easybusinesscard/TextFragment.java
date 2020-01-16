package com.nexttech.easybusinesscard;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.HashMap;

import yuku.ambilwarna.AmbilWarnaDialog;


public class TextFragment extends Fragment {



        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
                if (isVisibleToUser) {
                    if(Create_card.isDataAvailable){
                      dataSet=Create_card.dataSet;
                       Create_card.isDataAvailable=false;
                      Create_card.dataSet=null;

                     // SetData();

                     }
                }
            else {
            }
        }



    Context context;
    public TextFragment(Context context){
        this.context=context;
    }

    EditText text,textSize;



    TextView textFont, textColor, textStyle, textBackground;
    HashMap<String,String> dataSet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =getLayoutInflater().inflate(R.layout.fragment_text, null);


        text = view.findViewById(R.id.text);
        textSize = view.findViewById(R.id.textSize);
        textFont = view.findViewById(R.id.textFont);
        textColor = view.findViewById(R.id.textColor);
        textStyle = view.findViewById(R.id.textStyle);
        textBackground = view.findViewById(R.id.textBsckground);

        ToolbarFragment.textView.setBackgroundColor(156154);

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        textSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popup = new PopupMenu(context, v);

                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.font_name_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.default_font:
                                ToolbarFragment.textView.setTypeface(null);
                                return true;
                            case R.id.cursive:
                                Typeface typeface2 =  Typeface.createFromAsset(context.getAssets(),new FrontTag().getFrontName("tag2"));
                                ToolbarFragment.textView.setTypeface(typeface2);
                                return true;
                            case R.id.aclonia:
                                Typeface typeface3 =  Typeface.createFromAsset(context.getAssets(),new FrontTag().getFrontName("tag3"));
                                ToolbarFragment.textView.setTypeface(typeface3);
                                return true;
                            case R.id.cutive:
                                Typeface typeface4 =  Typeface.createFromAsset(context.getAssets(),new FrontTag().getFrontName("tag4"));
                                ToolbarFragment.textView.setTypeface(typeface4);
                                return true;
                            default:
                                ToolbarFragment.textView.setTypeface(null);
                                return false;
                        }
                    }
                });
                popup.show();
            }
        });
        textColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTextColorPicker(ToolbarFragment.textView.getCurrentTextColor());
            }
        });

        textStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu=new PopupMenu(context,v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.bold:
                                ToolbarFragment.textView.setTypeface(ToolbarFragment.textView.getTypeface(), Typeface.BOLD);
                                return true;
                            case R.id.normal:
                                ToolbarFragment.textView.setTypeface(null, Typeface.NORMAL);
                                return true;
                            case R.id.italic:
                                ToolbarFragment.textView.setTypeface(ToolbarFragment.textView.getTypeface(), Typeface.ITALIC);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });


        textBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable drawable = (ColorDrawable)ToolbarFragment.textView.getBackground();

                int color = drawable.getColor();


                openBackgroundColorPicker(color);
            }
        });

        return view;
    }


    void SetData(){

            text.setText(dataSet.get("text"));
            textSize.setText(dataSet.get("textSize"));

            textColor.setTextColor(Integer.parseInt(dataSet.get("colorCode")));

            Log.e("font", dataSet.get("font"));

            Typeface face = Typeface.createFromAsset(context.getAssets(),"font/"+dataSet.get("font")+".ttf");

            textFont.setTypeface(face);
            textFont.setText(text.getText());

            textBackground.setBackgroundColor(Color.parseColor(dataSet.get("backgroundColor")));
    }

    public void openTextColorPicker(int mDefaultColor) {


        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(context, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                ToolbarFragment.textView.setTextColor(color);
            }
        });
        colorPicker.show();
    }

    public void openBackgroundColorPicker(int mDefaultColor) {


        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(context, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                ToolbarFragment.textView.setBackgroundColor(color);
            }
        });
        colorPicker.show();
    }
}
