package com.example.rid_project.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.rid_project.R;


public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        int permissionCheck = ContextCompat.checkSelfPermission(StartActivity.this, Manifest.permission.CAMERA);
        if(permissionCheck== PackageManager.PERMISSION_DENIED){
            // 권한 없음
            ActivityCompat.requestPermissions(StartActivity.this, new String[]{Manifest.permission.CAMERA},0);
        }

        Handler handler = new Handler();  // 시작화면 3초후 로그인 화면으로 연결
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    //권한 있음
                    Intent intent = new Intent(getApplicationContext(),SignInActivity.class);
                    //Intent intent = new Intent(getApplicationContext(),SelectTextActivity.class);
                    startActivity(intent);
                    finish();

            }
        },2000);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults[0] == 0) {
                Toast.makeText(this, "카메라 권한이 승인됨",
                        Toast.LENGTH_SHORT).show();
            } else {
                //권한 거절된 경우
                Toast.makeText(this, "카메라 권한이 거절 되었습니다.카메라를 이용하려면 권한을 승낙하여야 합니다.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}


