package com.simon.jdelna.http;

import java.util.List;

public class LoginResponse {
    private List<User> users;
    private boolean successful;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public LoginResponse(List<User> users) {
        this.users = users;
        this.successful = true;
    }

    public LoginResponse(boolean successful) {
        this.successful = successful;
    }
}
