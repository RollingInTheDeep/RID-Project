package com.example.rid_project.ui.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


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


import com.example.rid_project.R;
import com.example.rid_project.databinding.ActivityReadTextBinding;
import com.example.rid_project.databinding.ActivitySelectTextBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectTextBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        cb1 = binding.cb1;
        cb2 = binding.cb2;
        cb3 = binding.cb1;
        cb4 = binding.cb4;


        btnNext = binding.btnNext;
        btnNext.setOnClickListener(view1 -> {
            Intent intent = new Intent(SelectTextActivity.this,ReadTextActivity.class);
            startActivity(intent);
            finish();
        });

        Intent getIntent = new Intent(this.getIntent());
        userUid = getIntent.getStringExtra("userUid");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityResult.launch(intent);

    }

    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        cnt = random.nextInt(1000);
                        StorageReference storageRef = storage.getReference(userUid.substring(0, 2) + Integer.toString(cnt));

                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 4;
                        Bundle extras = result.getData().getExtras();
                        Bitmap bitmap = (Bitmap) extras.get("data");

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] data = baos.toByteArray();

                        UploadTask uploadTask = storageRef.putBytes(data);
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                Log.e("fail", "upload fail");
                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Log.e("success", "upload success");

                            }
                        });


                    }
                }
            });

}