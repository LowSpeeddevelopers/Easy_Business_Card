package com.nexttech.easybusinesscard;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;


public class Qr_code extends Fragment {
    TextView qr_generate,qr_from_cv;
    Context context;
    Bitmap bitmap;
    String TAG="GenerateQRCode";
    String input_value;
    QRGEncoder qrgEncoder;
    CardView card_seekBar;
    SeekBar seekBar;
    ImageView imageView;

    public Qr_code(Context context) {
        this.context=context;
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_qr_code, container, false);
        View view = getLayoutInflater().inflate(R.layout.fragment_qr_code, null);

        seekBar=view.findViewById(R.id.seekbar);
        qr_generate=view.findViewById(R.id.qrcode_generate);
        qr_from_cv=view.findViewById(R.id.qrcode_from_cv);
        card_seekBar=view.findViewById(R.id.card_seekBar);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                AbsoluteLayout.LayoutParams imageParams2 = new AbsoluteLayout.LayoutParams(5*progress, 5*progress,imageView.getScrollX(),imageView.getScrollY());
                Log.e("progress",String.valueOf(seekBar.getProgress()));
                imageView.setLayoutParams(imageParams2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        qr_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.qr_code_generate_dialog);
                dialog.setTitle("Generate QR Code");


                final ImageView generated_qr = dialog.findViewById(R.id.qr);
                final EditText qr_edit_text = dialog.findViewById(R.id.qr_ed_text);
                TextView close_dailog =  dialog.findViewById(R.id.Close_dialog);
                TextView setQR =  dialog.findViewById(R.id.Qr_set_dialog);
                TextView generate_qr_code =  dialog.findViewById(R.id.Qr_generate_dialog);

                close_dailog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                setQR.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(bitmap!= null) {
                            imageView = new ImageView(context);
                            imageView.setTag(String.valueOf(ToolbarFragment.qrimagecounter));
                            ToolbarFragment.qrimagecounter++;
                            seekBar.setProgress(imageView.getHeight());
                            imageView.setOnLongClickListener(new LongPresslistener(context));
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Create_card.setCurrentFragmentwithData(4, imageView.getTag().toString());
                                    if (card_seekBar.getVisibility() == View.GONE) {
                                        card_seekBar.setVisibility(View.VISIBLE);
                                    }

                                }
                            });
                            imageView.setImageBitmap(bitmap);
                            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(280, 280);
                            imageView.setLayoutParams(layoutParams);
                            ToolbarFragment.addView(imageView);
                            ToolbarFragment.addqrcodeView(imageView);
                            card_seekBar.setVisibility(View.VISIBLE);
                            bitmap = null;
                        }
                        else {
                            Toast.makeText(getContext(), "not working", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }
                });

                generate_qr_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        input_value=qr_edit_text.getText().toString().trim();
                        if (input_value.length()>0){
                            WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
                            Display display=manager.getDefaultDisplay();
                            Point point=new Point();
                            display.getSize(point);
                            int width=point.x;
                            int height=point.y;
                            int smallerDimenson=width<height?width:height;
                            smallerDimenson=smallerDimenson*3/4;
                            qrgEncoder=new QRGEncoder(input_value,null, QRGContents.Type.TEXT,smallerDimenson);

                            try {
                                bitmap=qrgEncoder.encodeAsBitmap();
                                generated_qr.setImageBitmap(bitmap);


                            } catch (WriterException e) {
                                Log.v(TAG,e.toString());
                            }
                        }
                        else {
                            qr_edit_text.setError("Required");
                        }
                    }
                });
                dialog.show();

                Toast.makeText(getContext(), "Clicked On Generate QR", Toast.LENGTH_SHORT).show();
            }
        });
        qr_from_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked on From CV", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
