package com.example.rid_project.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rid_project.databinding.FragmentReadTextBinding;

public class ReadTextFragment extends Fragment {

    private FragmentReadTextBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding  = FragmentReadTextBinding.inflate(inflater, container, false);




        return binding.getRoot();

    }
}

