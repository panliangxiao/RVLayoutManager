package com.android.app.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
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
//                NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
//                boolean isOpened = manager.areNotificationsEnabled();
//                // 根据isOpened结果，判断是否需要提醒用户跳转AppInfo页面，去打开App通知权限
//                Intent intent = new Intent();
//                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Uri uri = Uri.fromParts("package", getApplication().getPackageName(), null);
//                intent.setData(uri);
//                startActivity(intent);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    RentalSocietyNotificationManager.notify(MainActivity.this, "", "1111", "", "3333");
                }
            }
        });
    }

}
