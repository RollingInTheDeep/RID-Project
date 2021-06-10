package com.example.rid_project.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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

        Intent intent1 = getIntent();
        EditText editText = binding.etText;
        editText.setText(intent1.getStringExtra("check"));
        SharedPreferences pref;          // 프리퍼런스
        SharedPreferences.Editor editor; // 에디터
        // 1. Shared Preference 초기화
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();
        // 2. 저장해둔 값 불러오기 ("식별값", 초기값) -> 식별값과 초기값은 직접 원하는 이름과 값으로 작성.


        editor = pref.edit();

        btnBookSave = binding.btnBookSave;
        SharedPreferences.Editor finalEditor = editor;
        btnBookSave.setOnClickListener(view1 -> {
                Intent intent = new Intent(ReadTextActivity.this,MainActivity.class);
                finalEditor.putString("check", String.valueOf(editText.getText()));
                finalEditor.apply();
                // clear top : 스택에 액티비티가 이미 존재한다면 새로운 인스턴스를 새로 생성하지 않고 백스택에서 top으로 가져오면서 이 인스턴스 위에 있던 인스턴스들을 모두 삭제함
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // single top : 해당 액티비티가 이미 스택 top에 있다면 인스턴스를 새로 생성하지 않고, 그게 아니라면 standard와 동일하게 새로 생성
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
        });
    }
}