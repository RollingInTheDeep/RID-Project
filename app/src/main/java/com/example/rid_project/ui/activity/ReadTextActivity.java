package com.example.rid_project.ui.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                Intent intent = new Intent(ReadTextActivity.this,MainActivity.class);
                // clear top : 스택에 액티비티가 이미 존재한다면 새로운 인스턴스를 새로 생성하지 않고 백스택에서 top으로 가져오면서 이 인스턴스 위에 있던 인스턴스들을 모두 삭제함
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // single top : 해당 액티비티가 이미 스택 top에 있다면 인스턴스를 새로 생성하지 않고, 그게 아니라면 standard와 동일하게 새로 생성
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
        });
    }
}