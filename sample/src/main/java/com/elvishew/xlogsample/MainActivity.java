package com.elvishew.xlogsample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.salton123.log.XLog;

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/13 5:52 PM
 * ModifyTime: 5:52 PM
 * Description:
 */
public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tvHello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XLog.i(this, "hello");
            }
        });
        requestPermission();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_EXTERNAL_STORAGE) {
            if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Toast.makeText(getApplicationContext(), "已授权", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "请授权", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}

