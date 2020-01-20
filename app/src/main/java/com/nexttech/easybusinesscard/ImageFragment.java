package com.nexttech.easybusinesscard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class ImageFragment extends Fragment {
    Button browse;
    Context context;
    View dialogueView;
    public static ImageView viewImage;

    private Button  browseImage, saveImage, cancelImage,selectIcon;

    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    private ImageView  icon_1,icon_2,icon_3,icon_4,icon_5,icon_6,icon_7,icon_8;


    public ImageFragment(Context context){
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_image, null);
        browse=view.findViewById(R.id.btn_browse);
        builder = new AlertDialog.Builder(context);

        imageDialoguebox();


        //size=view.findViewById(R.id.btn_size);

        /*size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Size",Toast.LENGTH_LONG).show();
            }
        });*/
        return view;
    }



    public void iconDialoguebox(){

        dialogueView=getLayoutInflater().inflate(R.layout.select_icon,null);
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
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(((Activity)context));
            }
        });
        icon_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(((Activity)context));

            }
        });
        icon_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(((Activity)context));

            }
        });
        icon_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(((Activity)context));

            }
        });
        icon_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(((Activity)context));
            }
        });
        icon_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(((Activity)context));

            }
        });
        icon_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(((Activity)context));

            }
        });
        icon_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setAspectRatio(1,1)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(((Activity)context));

            }
        });

        builder.setView(null);
        builder.setView(dialogueView);
        alertDialog=builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialogDismiss();
        alertDialog.show();
    }
    public void imageDialoguebox(){
        dialogueView=getLayoutInflater().inflate(R.layout.browse_image,null);
        browseImage = dialogueView.findViewById(R.id.btn_browse_image);
        saveImage= dialogueView.findViewById(R.id.btn_save_image);
        cancelImage= dialogueView.findViewById(R.id.btn_cancel_image);
        viewImage= dialogueView.findViewById(R.id.image_view);

        builder.setView(null);
        builder.setView(dialogueView);
        alertDialog=builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialogDismiss();
        alertDialog.show();



        browseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iconDialoguebox();


            }

        });
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImage(viewImage);

            }
        });
        cancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogDismiss();
            }
        });
    }


    private void setImage(ImageView image) {
        ImageView imageView=new ImageView(context);
        imageView.setTag("draggable imageview");
        imageView.setImageDrawable(image.getDrawable());
        imageView.setOnLongClickListener(new LongPresslistener(context));
        if(Create_card.absoluteLayoutFront.getVisibility()==View.VISIBLE){
            Create_card.absoluteLayoutFront.addView(imageView);


        }
        else {
            Create_card.absoluteLayoutBack.addView(imageView);
        }

        alertDialogDismiss();
    }

    private void alertDialogDismiss(){
        if (alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }





}
