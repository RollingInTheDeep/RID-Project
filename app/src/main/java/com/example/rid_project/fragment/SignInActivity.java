package com.example.rid_project.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rid_project.R;
import com.example.rid_project.databinding.ActivitySigninBinding;
import com.google.android.gms.common.SignInButton;

public class SignInActivity extends AppCompatActivity {

    private ActivitySigninBinding binding;
    private SignInButton btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        btnSignIn = binding.btnMainSignIn;


    }
}