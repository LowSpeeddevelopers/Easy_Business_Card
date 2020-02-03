package com.nexttech.easybusinesscard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(null);
        Toolbar toolbar = findViewById(R.id.toolbarMainActivity);

        TextView toolbartext = toolbar.findViewById(R.id.toolbartext);
        toolbartext.setText("LOG IN");


    }
}
