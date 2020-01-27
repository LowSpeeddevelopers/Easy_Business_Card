package com.nexttech.easybusinesscard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
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
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

public class ImageFragment extends Fragment {
    TextView browse;
    Context context;



    static SeekBar seekBar;
    public static ImageView imageViev = null;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            imageViev=new ImageView(context);
            imageViev.setOnLongClickListener(new LongPresslistener(context));
            imageViev.setTag(String.valueOf(ToolbarFragment.imagetagcounter));
        }
    }

    public ImageFragment(Context context){
        this.context=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_image, container,false);
        browse=view.findViewById(R.id.btn_browse);
        seekBar=view.findViewById(R.id.seekbar);

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity()
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(((Activity)context));


            }

        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ImageView imageView = (ImageView) Create_card.absoluteLayoutFront.findViewWithTag(imageViev.getTag().toString());
                Log.e("imagetag",imageView.getTag().toString());
                AbsoluteLayout.LayoutParams imageParams2 = new AbsoluteLayout.LayoutParams(3*progress, 3*progress, imageView.getScrollX(), imageView.getScrollY());

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


    public static void setImage(Bitmap image) {

        seekBar.setProgress(45);

        ToolbarFragment.imagetagcounter++;
        imageViev.setImageBitmap(image);
        imageViev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create_card.setCurrentFragmentwithData(3,imageViev.getTag().toString());
            }
        });
        ToolbarFragment.addView(imageViev);
        ToolbarFragment.addImageView(imageViev);

    }

}
