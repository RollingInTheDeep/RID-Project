package com.example.rid_project.repository;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.rid_project.data.Book;
import com.example.rid_project.data.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class FireStore {
    private static final String TAG = "dataBase";
    FirebaseFirestore firestore;
    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<Book> book = new MutableLiveData<>();
    String userUid = "";

    public FireStore(){
        firestore = FirebaseFirestore.getInstance();
    }

    public void setUser(User user){
        this.userUid = user.getUserId();
        if(this.userUid != null){
            firestore.collection("Users").document(userUid).set(user);
            setData(userUid);
        }else{
            setData(null);
        }

    }
    public void setBookName(Book book){
        Map<String, Object> update = new HashMap<>();
        update.put(book.bookName, book);
        firestore.collection("Users").document(userUid).set(update, SetOptions.merge());
    }

    public MutableLiveData<User> findAll(){return user;}
    public MutableLiveData<Book> find(){return book;}

    private void setBookData(){
        CollectionReference colRef = firestore.collection("Users").document(this.userUid).collection(book.getValue().bookName);
    }



    private void setData(String userUid){
        if(userUid != null){
            DocumentReference docRef = firestore.collection("Users").document(userUid);
            docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {


                if (value != null && value.exists()) {
                    Log.d(TAG, "Current data: " + value.getData());
                    if(value.getClass().getName().equals("User")) {
                        user.setValue(value.toObject(User.class));
                    }
                } else {
                    Log.d(TAG, "Current data: null");

                }
            }
        });

    };
}
}
