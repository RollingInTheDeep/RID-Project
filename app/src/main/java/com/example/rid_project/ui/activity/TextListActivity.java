package com.example.rid_project.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rid_project.databinding.ActivityTextListBinding;

public class TextListActivity extends AppCompatActivity {
    private String selectText;
    private String bookName;
    private ActivityTextListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTextListBinding .inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SharedPreferences pref;      // 프리퍼런스
        SharedPreferences.Editor editor; // 에디터
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

        editor = pref.edit();
        Intent intent = getIntent();
        bookName = intent.getStringExtra("name");
        selectText = pref.getString("check", "_");

        TextView tvBookName = binding.textViewBookTitle;
        tvBookName.setText(bookName);

        TextView tvBook = binding.textViewBook;
        tvBook.setText(selectText);

    }
}
