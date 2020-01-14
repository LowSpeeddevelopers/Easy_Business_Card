package com.nexttech.easybusinesscard;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class ToolbarFragment extends Fragment {

    Context context;

    public ToolbarFragment(Context context){
        this.context=context;
    }
    AlertDialog.Builder builder;
    AlertDialog alertDialog;

    TextView text,icon,image,qrcode,preview,backside,text2;

    public static TextView textView;
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
        builder = new AlertDialog.Builder(context);
        textView=new TextView(context);


        text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Create_card.setCurrentFragment(1);



                textView.setTag("draggable textview");
                textView.setText("type your text here");
                textView.setOnLongClickListener(new LongPresslistener(context));
                Typeface typeface=Typeface.create("Aclonia",Typeface.BOLD);
                textView.setTypeface(typeface);


                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        HashMap<String,String> datamap=new HashMap<>();
//                        datamap.put("text",textView.getText().toString());
//                        datamap.put("textSize",String.valueOf(Math.round(textView.getTextSize())));
//                        datamap.put("colorCode",String.valueOf(textView.getCurrentTextColor()));
//                        datamap.put("font",String.valueOf(textView.getTypeface()));


                        Typeface typeface=textView.getTypeface();

                        Log.e("typeface",String.valueOf(typeface.getStyle()));
                        //datamap.put("backgroundColor",String.valueOf(textView.getBackground()));

//                        Create_card.setCurrentFragmentwithData(1,datamap);
                    }
                });


                if(Create_card.isLayoutVisible()){
                    Create_card.absoluteLayoutFront.addView(textView);
                }else {
                    Create_card.absoluteLayoutBack.addView(textView);
                }


            }
        });



        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.viewPager.setCurrentItem(1);
                Create_card.mAdapter.notifyDataSetChanged();


            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Create_card.viewPager.setCurrentItem(3);
//                Create_card.mAdapter.notifyDataSetChanged();
            }
        });

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Create_card.viewPager.setCurrentItem(4);
//                Create_card.mAdapter.notifyDataSetChanged();
            }
        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Create_card.absoluteLayoutFront.getVisibility()==View.VISIBLE){
                    backside.setText("Front Side");
                    Create_card.absoluteLayoutFront.setVisibility(View.GONE);
                    Create_card.absoluteLayoutBack.setVisibility(View.VISIBLE);
                } else{
                    backside.setText("Back Side");
                    Create_card.absoluteLayoutFront.setVisibility(View.VISIBLE);
                    Create_card.absoluteLayoutBack.setVisibility(View.GONE);
                }
                ShowDialogebox();

            }
        });


        backside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (Create_card.absoluteLayoutFront.getVisibility()==View.VISIBLE){
                    backside.setText("Front Side");
                    Create_card.absoluteLayoutFront.setVisibility(View.GONE);
                    Create_card.absoluteLayoutBack.setVisibility(View.VISIBLE);
                } else{
                    backside.setText("Back Side");
                    Create_card.absoluteLayoutFront.setVisibility(View.VISIBLE);
                    Create_card.absoluteLayoutBack.setVisibility(View.GONE);
                }


            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Create_card.viewPager.setCurrentItem(7);
//                Create_card.mAdapter.notifyDataSetChanged();
            }
        });
        return vi;
    }


    void ShowDialogebox()
    {

        final TextView front,back;

        final ImageView imageView;


       View dialogueView = getLayoutInflater().inflate(R.layout.dialougebox, null);

       front=dialogueView.findViewById(R.id.front);
       back=dialogueView.findViewById(R.id.back);
       imageView=dialogueView.findViewById(R.id.imageview);


        imageView.setImageBitmap(Create_card.loadBitmapFromView(Create_card.absoluteLayoutFront));
        front.setTypeface(null, Typeface.BOLD);




       front.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               imageView.setImageBitmap(Create_card.loadBitmapFromView(Create_card.absoluteLayoutFront));
               front.setTypeface(null, Typeface.BOLD);
               back.setTypeface(null, Typeface.NORMAL);
           }
       });

       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Create_card.absoluteLayoutFront.setVisibility(View.GONE);
               Create_card.absoluteLayoutBack.setVisibility(View.VISIBLE);

               imageView.setImageBitmap(Create_card.loadBitmapFromView(Create_card.absoluteLayoutBack));
               back.setTypeface(null, Typeface.BOLD);
               front.setTypeface(null, Typeface.NORMAL);
           }
       });


        builder.setView(null);
        builder.setView(dialogueView);
        alertDialog=builder.create();
        alertDialog.setCanceledOnTouchOutside(true);

        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Create_card.absoluteLayoutFront.setVisibility(View.VISIBLE);
                Create_card.absoluteLayoutBack.setVisibility(View.GONE);
            }
        });
        alertDialogDismiss();
        alertDialog.show();



    }
    private void alertDialogDismiss(){
        if (alertDialog.isShowing()){
            alertDialog.dismiss();


        }
    }
}
