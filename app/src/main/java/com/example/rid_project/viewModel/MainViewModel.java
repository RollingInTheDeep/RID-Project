package com.example.rid_project.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rid_project.data.User;
import com.example.rid_project.repository.FireStore;

import org.jetbrains.annotations.NotNull;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<User> user;
    private User userData;
    private FireStore fireStore = new FireStore();

    public MainViewModel(@NonNull @NotNull Application application) {
        super(application);
        this.user = fireStore.findAll();
        this.userData = user.getValue();
    }

    public void setLiveData(User user){
        // livedata 설정
        this.user.setValue(user);
        // main view model 변수 값 설정
        this.userData = user;
        // firestore user 전달
        this.fireStore.setUser(userData);
    }

    //data binding시 필요
    public LiveData<User> getLiveData()
    {
        return user;
    }

}
