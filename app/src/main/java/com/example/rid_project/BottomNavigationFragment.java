package com.example.rid_project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BottomNavigationFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //보여질 뷰객체를 생성
        View view = inflater.inflate(R.layout.fragment_bottomnavigation, container, false); //container <-부모 사이즈를 주고 , false=아직 붙이지 않는다.

        //생성된 뷰를 리턴해주면 Activity에 보여짐
        return view;

    }

}