package com.nexttech.easybusinesscard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class ImageFragment extends Fragment {
    TextView browse;
    Context context;



    static SeekBar seekBar;
    public static ImageView imageViev = null;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){

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
                        .start(context, ImageFragment.this);
            }

        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                AbsoluteLayout.LayoutParams imageParams2 = new AbsoluteLayout.LayoutParams(progress, progress, imageViev.getScrollX(), imageViev.getScrollY());
                imageViev.setLayoutParams(imageParams2);

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

    public void setImage(Bitmap image) {

        imageViev=new ImageView(context);
        imageViev.setOnLongClickListener(new LongPresslistener(context));
        imageViev.setTag(String.valueOf(ToolbarFragment.imagetagcounter));
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("result",String.valueOf(requestCode));

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Uri imageUri = result.getUri();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
                setImage(bitmap);
                Log.e("setImage","image");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(context, "Something went wrong!!!", Toast.LENGTH_SHORT).show();
        }

    }
}
