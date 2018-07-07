package com.purplechai.admin.kissanyojnaapp;

/**
 * Created by Harish on 25-05-2018.
 */
//this is very simple class and it only contains the user attributes, a constructor and the getters
// you can easily do this by right click -> generate -> constructor and getters
public class User {

    private int id;
    private String uname, email, phone, state;

    public User(int id, String uname, String email, String phone, String state) {
        this.id = id;
        this.uname = uname;
        this.email = email;
        this.phone = phone;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getUname() {
        return uname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getState() {
        return state;
    }
}
