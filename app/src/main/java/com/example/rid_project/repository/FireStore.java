package com.example.rid_project.repository;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.rid_project.data.User;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class FireStore {
    private static final String TAG = "dataBase";
    FirebaseFirestore firestore;
    private MutableLiveData<User> user = new MutableLiveData<>();
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

    public MutableLiveData<User> findAll(){return user;}


    private void setData(String userUid){
        if(userUid != null){
            DocumentReference docRef = firestore.collection("Users").document(userUid);

            docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                    if (error != null) {
                        System.err.println("Listen failed: " + error);
                        return;
                    }

                    if (value != null && value.exists()) {
                        Log.d(TAG, "Current data: " + value.getData());
                        user.setValue(value.toObject(User.class));
                    } else {
                        Log.d(TAG, "Current data: null");
                    }
                }
            });
        }

    }
}
