package com.example.rid_project.ui.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.rid_project.databinding.ActivityReadTextBinding;


public class ReadTextActivity extends AppCompatActivity {


    private Button btnBookSave;
    private ActivityReadTextBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityReadTextBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        btnBookSave = binding.btnBookSave;
        btnBookSave.setOnClickListener(view1 -> {
                Log.e("d","listener시작");

                Intent intent = new Intent(ReadTextActivity.this,MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Log.e("d","test");
                finish();
        });
    }
}