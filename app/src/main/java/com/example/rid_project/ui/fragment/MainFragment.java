package com.example.rid_project.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rid_project.adapter.MainAdapter;
import com.example.rid_project.data.MainData;
import com.example.rid_project.databinding.FragmentMainBinding;
import java.util.ArrayList;


public class MainFragment extends Fragment {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private FragmentMainBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        recyclerView= binding.recyclerViewMainBookContainer;
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

        ImageButton btnAdd = binding.imageButtonMainPlus;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    MainData mainData = new MainData("그만해");
                    arrayList.add(mainData);
                    mainAdapter.notifyDataSetChanged();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);}

}
