package com.example.rid_project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;  //  프래그먼트 매니저


    private BottomNavigationFragment fragmentBottom;  //  Fragment NavBar
    private MainViewFragment fragmentMain;  //  메인뷰 프래그먼트


    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        fragmentBottom = new BottomNavigationFragment();
        fragmentMain = new MainViewFragment();
        transaction.replace(R.id.fragment_main_view, fragmentMain);
        transaction.replace(R.id.fragment_main_navigationbar, fragmentBottom).commit();

    }

}