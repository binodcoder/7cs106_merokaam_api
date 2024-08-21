package com.binodcoder.merokaamapi.security.response;

import java.util.List;

public class UserInfoResponse {
    private int id;

    private String jwtToken;

    private String username;

    private String userTypeName;

    public UserInfoResponse(int id, String username, String userTypeName, String jwtToken) {
        this.id = id;
        this.username = username;
        this.userTypeName = userTypeName;
        this.jwtToken = jwtToken;
    }

    public UserInfoResponse(int id, String username,   String userTypeName) {
        this.id = id;
        this.username = username;
        this.userTypeName = userTypeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }
}


