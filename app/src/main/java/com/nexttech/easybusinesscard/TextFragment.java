package com.nexttech.easybusinesscard;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

public class TextFragment extends Fragment {

    TextView text, textSize, textFont, textColor, textStyle, textBackground;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view =getLayoutInflater().inflate(R.layout.fragment_text, null);

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

                PopupMenu popup = new PopupMenu(context, v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.default_font:

                                return true;
                            case R.id.cursive:

                                return true;
                            case R.id.aclonia:

                                return true;
                            case R.id.cutive:

                                return true;
                            default:

                                return false;
                        }
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.font_name_menu, popup.getMenu());
                popup.show();
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
                PopupMenu popupMenu=new PopupMenu(context,v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.bold:

                                return true;

                            case R.id.normal:

                                return true;

                            case R.id.italic:
                                return true;

                                default:
                                    return false;
                        }

                    }
                });
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
