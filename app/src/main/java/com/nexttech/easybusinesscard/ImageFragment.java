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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class ImageFragment extends Fragment {
    TextView browse;
    Context context;
    public static ImageView viewImage;

    private Button  browseImage, saveImage, cancelImage,selectIcon;


    AlertDialog alertDialog;



    public ImageFragment(Context context){
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_image, container,false);
        browse=view.findViewById(R.id.btn_browse);

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageDialoguebox();
            }
        });

        //size=view.findViewById(R.id.btn_size);

        /*size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Size",Toast.LENGTH_LONG).show();
            }
        });*/
        return view;
    }



    private boolean imageDialoguebox(){


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogueView=getLayoutInflater().inflate(R.layout.browse_image,null);
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

                CropImage.activity()
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(((Activity)context));


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

        return true;
    }


    private void setImage(ImageView image) {
        ImageView imageView=new ImageView(context);
        imageView.setTag(String.valueOf(ToolbarFragment.imagetagcounter));
        ToolbarFragment.imagetagcounter++;
        imageView.setImageDrawable(image.getDrawable());
        imageView.setOnLongClickListener(new LongPresslistener(context));
        ToolbarFragment.addView(imageView);
        ToolbarFragment.addImageView(imageView);
        alertDialogDismiss();
    }

    private void alertDialogDismiss(){
        if (alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }





}
