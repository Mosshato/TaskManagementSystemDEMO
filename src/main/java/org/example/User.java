package org.example;

import java.util.List;

public abstract class User {
    String userName;
    String password;

    String name;
    String email;
    String phone;
    User(String name) {
        this.name = name;
    }
    User() {
        this.name  = null;
    }
    public void getInfo(){

    }

}
