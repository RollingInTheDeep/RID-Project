package com.example.rid_project.ui.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rid_project.R;

public class ReadTextFragment extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_read_text);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
