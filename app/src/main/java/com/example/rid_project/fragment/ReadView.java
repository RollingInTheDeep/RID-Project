package com.example.rid_project.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rid_project.R;

public class ReadView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
