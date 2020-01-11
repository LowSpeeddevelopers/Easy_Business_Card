package com.nexttech.easybusinesscard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class Create_card extends AppCompatActivity {
    TextView importtemp,share,export,browse;


    View v1,v2,v3,v5;
    View focusview, dialogueView;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    ImageView mainTemp, ivLockCard;
    boolean b=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        importtemp=findViewById(R.id.importtemp);
        ivLockCard=findViewById(R.id.iv_lock_card);
        share=findViewById(R.id.share);
        export=findViewById(R.id.export);
        browse=findViewById(R.id.browse);
        v1=findViewById(R.id.view1);
        v2=findViewById(R.id.view2);
        v3=findViewById(R.id.view3);
        v5=findViewById(R.id.view5);
        mainTemp = findViewById(R.id.maintemp);
        builder = new AlertDialog.Builder(Create_card.this);
        importtemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    focusview.setBackgroundColor(Color.WHITE);
                    v1.setBackgroundColor(Color.BLACK);
                    focusview=v1;
                    b=true;
                }else {
                    v1.setBackgroundColor(Color.BLACK);
                    focusview=v1;
                    b=true;
                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    focusview.setBackgroundColor(Color.WHITE);
                    v2.setBackgroundColor(Color.BLACK);
                    focusview=v2;
                    b=true;
                }else {
                    v2.setBackgroundColor(Color.BLACK);
                    focusview=v2;
                    b=true;
                }
                showShareDialougeBox();
            }
        });
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    focusview.setBackgroundColor(Color.WHITE);
                    v3.setBackgroundColor(Color.BLACK);
                    focusview=v3;
                    b=true;
                }else {
                    v3.setBackgroundColor(Color.BLACK);
                    focusview=v3;
                    b=true;
                }
            }
        });
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    focusview.setBackgroundColor(Color.WHITE);
                    v5.setBackgroundColor(Color.BLACK);
                    focusview=v5;
                    b=true;
                }else {
                    v5.setBackgroundColor(Color.BLACK);
                    focusview=v5;
                    b=true;
                }
                ShowDialogebox();

            }
        });
        ivLockCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable drawable = (BitmapDrawable) mainTemp.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                if(isStoragePermissionGranted()){
                    SaveImage(bitmap);
                }
            }
        });

    }

    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();

        String saveDirectoryName = "Business Cards";

        File myDir = new File(root + "/"+saveDirectoryName);
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(this, "Save to "+saveDirectoryName, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission","Permission is granted");
                return true;
            } else {

                Log.v("Permission","Permission is revoked");
                ActivityCompat.requestPermissions(Create_card.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Permission","Permission is granted");
            return true;
        }
    }

    void ShowDialogebox()
    {
        ImageView temp1, temp1rear, temp2,temp2rear, temp3, temp3rear;

        dialogueView = getLayoutInflater().inflate(R.layout.brows_template, null);
        temp1 = dialogueView.findViewById(R.id.imgtemp1);
        temp1rear = dialogueView.findViewById(R.id.imgtemp1rear);
        temp2 = dialogueView.findViewById(R.id.imgtemp2);
        temp2rear = dialogueView.findViewById(R.id.imgtemp2rear);
        temp3 = dialogueView.findViewById(R.id.imgtemp3);
        temp3rear = dialogueView.findViewById(R.id.imgtemp3rear);

        builder.setView(null);
        builder.setView(dialogueView);
        alertDialog=builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialogDismiss();
        alertDialog.show();

        temp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTemp.setImageDrawable(getResources().getDrawable(R.drawable.cardone));
                alertDialogDismiss();
            }
        });
        temp1rear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTemp.setImageDrawable(getResources().getDrawable(R.drawable.rear));
                alertDialogDismiss();
            }
        });
        temp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTemp.setImageDrawable(getResources().getDrawable(R.drawable.temp2));
                alertDialogDismiss();
            }
        });
        temp2rear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTemp.setImageDrawable(getResources().getDrawable(R.drawable.temp2rear));
                alertDialogDismiss();

            }
        });
        temp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTemp.setImageDrawable(getResources().getDrawable(R.drawable.temp3));
                alertDialogDismiss();
            }
        });
        temp3rear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainTemp.setImageDrawable(getResources().getDrawable(R.drawable.temp3rear));
                alertDialogDismiss();
            }
        });

    }
    private void alertDialogDismiss(){
        if (alertDialog.isShowing()){
            alertDialog.dismiss();
        }
    }
    void showShareDialougeBox(){

        BitmapDrawable drawable = (BitmapDrawable) mainTemp.getDrawable();
        Bitmap bitmap1 = drawable.getBitmap();



        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM, getImageUri(this,bitmap1));
        startActivity(Intent.createChooser(share,"Share via"));
    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
