package com.nexttech.easybusinesscard;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import yuku.ambilwarna.AmbilWarnaDialog;


public class TextFragment extends Fragment {



        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
                if (isVisibleToUser) {
                    if (Create_card.isLayoutVisible()){
                        TextView tv = ToolbarFragment.textArrayFront.get(Create_card.tageeee);
                        Log.e("page tag",Create_card.tageeee);
                        if(tv!=null){
                            text.setText(tv.getText());
                            seekbarTextSize.setProgress(Math.round(tv.getTextSize()));
                        }

                    } else {
                        TextView tv = ToolbarFragment.textArrayBack.get(Create_card.tageeee);
                        Log.e("page tag",Create_card.tageeee);
                        if(tv!=null){
                            text.setText(tv.getText());
                            seekbarTextSize.setProgress(Math.round(tv.getTextSize()));
                        }


                    }
                }
            else {
            }
        }



    Context context;
    public TextFragment(Context context){
        this.context=context;
    }

    EditText text;

    SeekBar seekbarTextSize;

    TextView textFont, textColor, textStyle, savetext;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =getLayoutInflater().inflate(R.layout.fragment_text, null);


        text = view.findViewById(R.id.text);
        seekbarTextSize = view.findViewById(R.id.seekbarTextSize);
        textFont = view.findViewById(R.id.textFont);
        textColor = view.findViewById(R.id.textColor);
        textStyle = view.findViewById(R.id.textStyle);
        //savetext=view.findViewById(R.id.saveText);

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (Create_card.isLayoutVisible()){
                    if (ToolbarFragment.textArrayFront.size()>0) {
                        ToolbarFragment.textArrayFront.get(Create_card.tageeee).setText(s);
                    }
                } else {
                    if (ToolbarFragment.textArrayBack.size()>0) {
                        ToolbarFragment.textArrayBack.get(Create_card.tageeee).setText(s);
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//      savetext.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              String ttext=text.getText().toString();
//
//              Log.e("tagggggggggg",Create_card.tageeee);
//
//              if (Create_card.isLayoutVisible()){
//                  ToolbarFragment.textArrayFront.get(Create_card.tageeee).setText(ttext);
//              } else {
//                  ToolbarFragment.textArrayBack.get(Create_card.tageeee).setText(ttext);
//              }
//
//          }
//      });

      seekbarTextSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              if (Create_card.isLayoutVisible()){
                  if(ToolbarFragment.textArrayFront.get(Create_card.tageeee)!=null){
                      ToolbarFragment.textArrayFront.get(Create_card.tageeee).setTextSize(TypedValue.COMPLEX_UNIT_PX,progress);
                  }
              } else {
                  if(ToolbarFragment.textArrayBack.get(Create_card.tageeee)!=null){
                      ToolbarFragment.textArrayBack.get(Create_card.tageeee).setTextSize(TypedValue.COMPLEX_UNIT_PX,progress);
                  }
              }
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {

          }


              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {
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
                                if (Create_card.isLayoutVisible()){
                                    ToolbarFragment.textArrayFront.get(Create_card.tageeee).setTypeface(null);
                                } else {
                                    ToolbarFragment.textArrayBack.get(Create_card.tageeee).setTypeface(null);
                                }
                                return true;
                            case R.id.cursive:
                                Typeface typeface2 =  Typeface.createFromAsset(context.getAssets(),new FrontTag().getFrontName("tag2"));
                                if (Create_card.isLayoutVisible()){
                                    ToolbarFragment.textArrayFront.get(Create_card.tageeee).setTypeface(typeface2);
                                } else {
                                    ToolbarFragment.textArrayBack.get(Create_card.tageeee).setTypeface(typeface2);
                                }

                                return true;
                            case R.id.aclonia:
                                Typeface typeface3 =  Typeface.createFromAsset(context.getAssets(),new FrontTag().getFrontName("tag3"));
                                if (Create_card.isLayoutVisible()){
                                    ToolbarFragment.textArrayFront.get(Create_card.tageeee).setTypeface(typeface3);
                                } else {
                                    ToolbarFragment.textArrayBack.get(Create_card.tageeee).setTypeface(typeface3);
                                }

                                return true;
                            case R.id.cutive:
                                Typeface typeface4 =  Typeface.createFromAsset(context.getAssets(),new FrontTag().getFrontName("tag4"));
                                if (Create_card.isLayoutVisible()){
                                    ToolbarFragment.textArrayFront.get(Create_card.tageeee).setTypeface(typeface4);
                                } else {
                                    ToolbarFragment.textArrayBack.get(Create_card.tageeee).setTypeface(typeface4);
                                }
                                return true;
                            default:
                                if (Create_card.isLayoutVisible()){
                                    ToolbarFragment.textArrayFront.get(Create_card.tageeee).setTypeface(null);
                                } else {
                                    ToolbarFragment.textArrayBack.get(Create_card.tageeee).setTypeface(null);
                                }
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
                if (Create_card.isLayoutVisible()){
                    openTextColorPicker(ToolbarFragment.textArrayFront.get(Create_card.tageeee).getCurrentTextColor());
                } else {
                    openTextColorPicker(ToolbarFragment.textArrayBack.get(Create_card.tageeee).getCurrentTextColor());
                }

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
                                if (Create_card.isLayoutVisible()){
                                    ToolbarFragment.textArrayFront.get(Create_card.tageeee).setTypeface(ToolbarFragment.textArrayFront.get(Create_card.tageeee).getTypeface(), Typeface.BOLD);
                                } else {
                                    ToolbarFragment.textArrayBack.get(Create_card.tageeee).setTypeface(ToolbarFragment.textArrayBack.get(Create_card.tageeee).getTypeface(), Typeface.BOLD);
                                }

                                return true;
                            case R.id.normal:
                                if (Create_card.isLayoutVisible()){
                                    ToolbarFragment.textArrayFront.get(Create_card.tageeee).setTypeface(null, Typeface.NORMAL);
                                } else {
                                    ToolbarFragment.textArrayBack.get(Create_card.tageeee).setTypeface(null, Typeface.NORMAL);
                                }

                                return true;
                            case R.id.italic:
                                if (Create_card.isLayoutVisible()){
                                    ToolbarFragment.textArrayFront.get(Create_card.tageeee).setTypeface(ToolbarFragment.textArrayFront.get(Create_card.tageeee).getTypeface(), Typeface.ITALIC);
                                } else {
                                    ToolbarFragment.textArrayBack.get(Create_card.tageeee).setTypeface(ToolbarFragment.textArrayBack.get(Create_card.tageeee).getTypeface(), Typeface.ITALIC);
                                }

                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });


        return view;
    }


    public void openTextColorPicker(int mDefaultColor) {


        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(context, mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                if (Create_card.isLayoutVisible()){
                    ToolbarFragment.textArrayFront.get(Create_card.tageeee).setTextColor(color);
                } else {
                    ToolbarFragment.textArrayBack.get(Create_card.tageeee).setTextColor(color);
                }

            }
        });
        colorPicker.show();
    }

}
