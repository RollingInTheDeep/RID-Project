package com.example.rid_project.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rid_project.ui.fragment.BottomNavigationFragment;
import com.example.rid_project.ui.fragment.MainFragment;
import com.example.rid_project.R;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;  //  프래그먼트 매니저
    private BottomNavigationFragment fragmentBottom;  //  Fragment NavBar
    private MainFragment fragmentMain;  //  메인뷰 프래그먼트
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        fragmentBottom = new BottomNavigationFragment();
        fragmentMain = new MainFragment();
        transaction.replace(R.id.fragment_main_view, fragmentMain);
        transaction.replace(R.id.fragment_main_navigationbar, fragmentBottom).commit();

    }

}