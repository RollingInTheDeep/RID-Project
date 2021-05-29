package com.example.rid_project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.rid_project.R;
import com.example.rid_project.databinding.FragmentBottomnavigationBinding;
import com.example.rid_project.ui.activity.CameraActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationFragment extends Fragment {

    private FragmentBottomnavigationBinding binding;
    public BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBottomnavigationBinding.inflate(inflater, container, false);
        bottomNavigationView = binding.bottomNavigationViewMain;
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.cameraItem_navigation:
                            getActivity().startActivity(new Intent(getActivity(), CameraActivity.class));

                            Log.e("dd","d");
                            return true;
                        case R.id.bookitem_navigation:
                            return true;
                        case R.id.manageItem_navigation:
                            return true;
                        default:
                            return false;
                    }
                });

        return binding.getRoot();
    }

}