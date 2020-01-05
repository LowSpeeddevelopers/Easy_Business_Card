package com.nexttech.easybusinesscard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Create_card extends AppCompatActivity {
    TextView importtemp,share,export,something,help;
    View v1,v2,v3,v4,v5;
    View focusview;
    boolean b=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        importtemp=findViewById(R.id.importtemp);
        share=findViewById(R.id.share);
        export=findViewById(R.id.export);
        something=findViewById(R.id.something);
        help=findViewById(R.id.help);
        v1=findViewById(R.id.view1);
        v2=findViewById(R.id.view2);
        v3=findViewById(R.id.view3);
        v4=findViewById(R.id.view4);
        v5=findViewById(R.id.view5);





        importtemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#66778A"));
                    focusview.setBackgroundColor(Color.WHITE);
                    v1.setBackgroundColor(Color.BLACK);
                    focusview=v1;
                    b=true;
                }else {
                    findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#66778A"));
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
                    findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#66778A"));
                    focusview.setBackgroundColor(Color.WHITE);
                    v2.setBackgroundColor(Color.BLACK);
                    focusview=v2;
                    b=true;
                }else {
                    findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#66778A"));
                    v2.setBackgroundColor(Color.BLACK);
                    focusview=v2;
                    b=true;
                }

            }
        });

        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#66778A"));
                    focusview.setBackgroundColor(Color.WHITE);
                    v3.setBackgroundColor(Color.BLACK);
                    focusview=v3;
                    b=true;
                }else {
                    findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#66778A"));
                    v3.setBackgroundColor(Color.BLACK);
                    focusview=v3;
                    b=true;
                }
            }
        });

        something.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#66778A"));
                    focusview.setBackgroundColor(Color.WHITE);
                    v4.setBackgroundColor(Color.BLACK);
                    focusview=v4;
                    b=true;
                }else {
                    findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#66778A"));
                    v4.setBackgroundColor(Color.BLACK);
                    focusview=v4;
                    b=true;
                }
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b){
                    findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#66778A"));
                    focusview.setBackgroundColor(Color.WHITE);
                    v5.setBackgroundColor(Color.BLACK);
                    focusview=v5;
                    b=true;
                }else {
                    findViewById(R.id.layout).setBackgroundColor(Color.parseColor("#66778A"));
                    v5.setBackgroundColor(Color.BLACK);
                    focusview=v5;
                    b=true;
                }
            }
        });

    }
}
