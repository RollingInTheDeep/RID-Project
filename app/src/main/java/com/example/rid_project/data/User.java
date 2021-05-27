package com.example.rid_project.data;

public class User {

    private String userId;
    private String userName;

    public User(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    public User(){
    }

    public String getUserId(){return this.userId;}
    public String getUserName(){return this.userName;}

}
