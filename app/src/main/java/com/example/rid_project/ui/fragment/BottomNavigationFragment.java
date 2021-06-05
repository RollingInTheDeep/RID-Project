package com.example.rid_project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.rid_project.R;
import com.example.rid_project.data.User;
import com.example.rid_project.databinding.FragmentBottomnavigationBinding;
import com.example.rid_project.ui.activity.SelectTextActivity;
import com.example.rid_project.viewModel.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationFragment extends Fragment {

    public BottomNavigationView bottomNavigationView;
    private MainViewModel mainViewModel;
    private String userUid = "";


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentBottomnavigationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottomnavigation,container,false);
        bottomNavigationView = binding.bottomNavigationViewMain;

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        binding.setViewModel(mainViewModel);
        binding.setLifecycleOwner(this);

        mainViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                userUid = user.getUserId();
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.cameraItem_navigation:
                            Intent intent = new Intent(getActivity(), SelectTextActivity.class);
                            intent.putExtra("userUid", userUid);
                            getActivity().startActivity(intent);
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