package com.chen.demo.models;

public class Account extends BaseModel{

    private String username;
    private String password;
    private String otherStr;

    public static String getPasswordAndOtherStr(String otherStr, String password){
        return otherStr + password;
    }

    public String getPasswordAndOtherStr(){
        return this.otherStr + this.password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOtherStr() {
        return otherStr;
    }

    public void setOtherStr(String otherStr) {
        this.otherStr = otherStr;
    }
}
