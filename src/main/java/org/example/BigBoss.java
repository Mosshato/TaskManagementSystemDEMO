package org.example;

import java.util.List;

public class BigBoss extends User implements Login, Task{
    Boolean isLoggedin = false;

    BigBoss(String name) {
        super(name);
    }

    @Override
    public void login() {
        System.out.println("You are logged in as a Boss");
        this.isLoggedin = true;
    }

    @Override
    public void logout() {
        System.out.println("You logged out from a Boss user");
    }

    @Override
    public void whileIsLoggedIn() {

    }

    @Override
    public Boolean isloggedin() {
        return this.isLoggedin;
    }

    @Override
    public void viewTasks() {
        System.out.println("Below are the today tasks assigned to the Managers and Employee: \n");
    }

    @Override
    public void setTaskCompleted() {

    }
}
















