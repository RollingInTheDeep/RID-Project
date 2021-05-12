package com.example.rid_project.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.rid_project.databinding.FragmentMainAddBookBinding;


public class MainAddBookFragment extends Fragment {

    private FragmentMainAddBookBinding binding;
    private String bookName;

    EditText etTitle;
    Button btnComplete;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState){

        binding = FragmentMainAddBookBinding.inflate(inflater, container, false);

        btnComplete = binding.btnMainComplete;
        etTitle = binding.editTextMainBookTitle;
        bookName = etTitle.getText().toString();

        btnComplete.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(MainAddBookFragment.this, MainActivity.class);
                // 디비가 있는 클래스로 bookName 값 넘겨주기
                // Intent.putExtra("bookName",bookName);
                // startActivity(intent); // 액티비티 이동
            }
        }));

        return binding.getRoot();
    }


    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}