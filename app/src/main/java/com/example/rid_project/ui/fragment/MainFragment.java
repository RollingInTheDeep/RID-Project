package com.example.rid_project.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rid_project.R;
import com.example.rid_project.adapter.MainAdapter;
import com.example.rid_project.data.MainData;
import com.example.rid_project.data.User;
import com.example.rid_project.databinding.FragmentMainBinding;
import com.example.rid_project.viemodel.MainViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ImageButton btnAdd;
    private FragmentMainBinding binding;
    private TextView userNameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        binding = FragmentMainBinding.inflate(inflater, container, false);

        recyclerView = binding.recyclerViewMainBookContainer;
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(arrayList);

        recyclerView.setAdapter(mainAdapter);
        btnAdd = binding.imageButtonMainPlus;
        userNameTextView = binding.textViewMainTopBar;

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mainViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                userNameTextView.setText(user.getUserName() + "의 책장");
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData mainData = new MainData("그만해");
                arrayList.add(mainData);
                mainAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        userNameTextView.setText(mainViewModel.getLiveData().getValue().getUserName() + "의 책장");
    }
}
