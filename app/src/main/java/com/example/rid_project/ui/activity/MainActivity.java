package com.example.rid_project.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rid_project.ui.fragment.BottomNavigationFragment;
import com.example.rid_project.ui.fragment.MainFragment;
import com.example.rid_project.R;
import com.example.rid_project.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;  //  프래그먼트 매니저
    private ActivityMainBinding binding;
    private BottomNavigationFragment fragmentBottom;  //  Fragment NavBar
    private MainFragment fragmentMain;  //  메인뷰 프래그먼트
    private String userID;
    private String userName;
    private FrameLayout fragmentView;
    private FragmentTransaction transaction;
    private TextView textViewMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();  //  이전 signin activity intent
        userID = intent.getStringExtra("userID");  //  사용자 uid
        userName = intent.getStringExtra("userName");  //  사용자 이름

        fragmentManager = getSupportFragmentManager();   //액티비티 위에 프래그먼트 연결
        transaction = fragmentManager.beginTransaction();
        fragmentBottom = new BottomNavigationFragment();
        fragmentMain = new MainFragment();
        transaction.replace(R.id.fragment_main_view, fragmentMain);
        transaction.replace(R.id.fragment_main_navigationbar, fragmentBottom).commit();

        fragmentView = binding.fragmentMainView;  // 책장 프래그먼트
        //textViewMain = fragment_view.findViewById(R.id.textView_main_topBar);  // 책장 유저이름 칸
       // textViewMain.setText(userName + "의 책장");

    }
}
