package com.accenture.flowershop.be.entity.user;

public class UserRole {

    private int userType_id;
    private String type;

    public UserRole(int userType_id, String type) {
        this.userType_id = userType_id;
        this.type = type;
    }

    public int getUserType_id() {
        return userType_id;
    }

    public void setUserType_id(int userType_id) {
        this.userType_id = userType_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
