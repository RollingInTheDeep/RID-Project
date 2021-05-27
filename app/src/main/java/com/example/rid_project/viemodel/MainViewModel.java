package com.example.rid_project.viemodel;

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

    public void setUser(User user){
        fireStore.setUser(user);
    }

    //data binding시 필요
    public LiveData<User> getLiveData()
    {
        return user;
    }

    public void setLiveData(User user){
        this.user.setValue(user);
        this.userData = user;
        setUser(userData);
    }

}
