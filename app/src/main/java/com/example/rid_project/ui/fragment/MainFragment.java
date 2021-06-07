package com.example.rid_project.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rid_project.R;
import com.example.rid_project.adapter.MainAdapter;
import com.example.rid_project.data.Book;
import com.example.rid_project.data.MainData;
import com.example.rid_project.data.User;
import com.example.rid_project.databinding.FragmentMainBinding;
import com.example.rid_project.viewModel.MainViewModel;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    private ArrayList<Book> arrayList;
    private MainAdapter mainAdapter;
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ImageButton btnAdd;
    private LinearLayout linear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false);
        View view = binding.getRoot();

        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        binding.setViewModel(mainViewModel);
        binding.setLifecycleOwner(this);

        mainViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.setUser(user);

            }
        });

        recyclerView = binding.recyclerViewMainBookContainer;
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(arrayList);

        recyclerView.setAdapter(mainAdapter);
        btnAdd = binding.imageButtonMainPlus;


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear = (LinearLayout)View.inflate(getContext(), R.layout.fragment_main_add_book, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(linear);

                builder.setPositiveButton("저장", (dialog, which) -> {
                    EditText descripton = (EditText) linear.findViewById(R.id.editText_main_bookTitle);
                    String s = descripton.getText().toString();
                    Book book = new Book(s);
                    mainViewModel.setBookData(book);
                    arrayList.add(book);
                    mainAdapter.notifyDataSetChanged();
                    // db에 저장하기
                    dialog.dismiss();
                });

                builder.setNegativeButton("취소", (dialog, which) -> {
                    dialog.dismiss();
                });
                builder.show();

            }
        });
        return view;
    }



}
