package com.example.rid_project.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rid_project.databinding.ActivitySelectTextBinding;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;
import java.util.Random;



public class SelectTextActivity extends AppCompatActivity {

    private String userUid = "";
    private int cnt;
    private Random random = new Random();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    private Button btnNext;
    private ActivitySelectTextBinding binding;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectTextBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        text = "그런데도 그 보물로 하여 그 집 전체는 마술에 걸려 있는 듯 매력이 넘쳐 있었다.우리 집은 마음 속에 가장 깊숙한 곳에 보물을 감추고 있는 것이었다.그래.집이건 별이건 혹은 사막이건, 그들을 아름답게 보이게 하는 것은 눈에 보이지 않는 법이지!";
        String[] textList = text.split("\\.");

        cb1 = binding.cb1;
        cb2 = binding.cb2;
        cb3 = binding.cb3;
        cb4 = binding.cb4;

        if(textList.length != 0){
            cb1.setText(textList[0]);
            cb2.setText(textList[1]);
            cb3.setText(textList[2]);
            cb4.setText(textList[3]);
        }


        btnNext = binding.btnNext;
        btnNext.setOnClickListener(view1 -> {
            Intent intent = new Intent(SelectTextActivity.this,ReadTextActivity.class);
            intent.putExtra("check",Checked(view1));
            startActivity(intent);
            finish();
        });

        Intent getIntent = new Intent(this.getIntent());
        userUid = getIntent.getStringExtra("userUid");


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityResult.launch(intent);
    }

    public String Checked(View v) {
        String resultText = ""; // 체크되었을 때 값을 저장할 스트링 값
        if (cb1.isChecked()) {
            resultText += cb1.getText()+".";
        }
        if (cb2.isChecked()) {
            resultText += cb2.getText()+".";
        }
        if (cb3.isChecked()) {
            resultText += cb3.getText()+".";
        }
        if (cb4.isChecked()) {
            resultText += cb4.getText()+".";
        }
        return resultText;
    }


    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {

                        // cloud storage에 올린 코드
//                        cnt = random.nextInt(1000);
//                        StorageReference storageRef = storage.getReference(userUid.substring(0, 2) + Integer.toString(cnt));


                        // 카메라로 촬영한 이미지는 bitmap 형태로 제공
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 4;

                        Bundle extras = result.getData().getExtras();
                        Bitmap bitmap = (Bitmap) extras.get("data");





                    }
                }
            });
}