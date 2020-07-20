package com.android.app.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.app.sample.anim.AnimActivity;
import com.android.app.sample.nested.NestedActivity;
import com.android.app.sample.suspension.SuspensionActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button pickerButton;
    private Button tantanButton;
    private Button galleryButton;
    private Button adapterButton;

    private Button mNestedButton;

    private Button mSuspensionButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pickerButton = findViewById(R.id.button_picker);
        tantanButton = findViewById(R.id.button_tantan);
        galleryButton = findViewById(R.id.button_gallery);

        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PickerActivity.class));
            }
        });

        tantanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CardSwipeActivity.class));
            }
        });

        adapterButton = findViewById(R.id.button_adapter);

        adapterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdapterActivity.class));
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GalleryActivity.class));
            }
        });

        mNestedButton = findViewById(R.id.button_nested);
        mNestedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NestedActivity.class));
            }
        });

        mSuspensionButton = findViewById(R.id.button_suspension);
        mSuspensionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SuspensionActivity.class));
            }
        });

        findViewById(R.id.button_scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnimActivity.class));
            }
        });
    }

}
