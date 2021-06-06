package com.example.rid_project.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rid_project.data.Book;
import com.example.rid_project.data.User;
import com.example.rid_project.repository.FireStore;

import org.jetbrains.annotations.NotNull;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<User> user;
    private MutableLiveData<Book> book;
    private User userData;
    private Book bookData;
    private FireStore fireStore = new FireStore();

    public MainViewModel(@NonNull @NotNull Application application) {
        super(application);
        this.user = fireStore.findAll();
        this.userData = user.getValue();
        this.book = fireStore.find();
    }

    public void setLiveData(User user){
        // livedata 설정
        this.user.setValue(user);
        // main view model 변수 값 설정
        this.userData = user;
        // firestore user 전달
        this.fireStore.setUser(userData);
    }

    public void setBookData(Book book){
        this.book.setValue(book);
        this.bookData = book;
        this.fireStore.setBookName(book);
    }

    //data binding시 필요
    public LiveData<User> getLiveData()
    {
        return user;
    }

}
