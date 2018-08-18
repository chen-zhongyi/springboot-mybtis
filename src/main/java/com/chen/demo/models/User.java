package com.chen.demo.models;

import com.chen.demo.commons.EncryptUtil;
import com.chen.demo.enums.UserType;

public class User extends BaseModel{

    private String username;
    private String password;
    private String otherStr;
    private UserType type;

    private String email;
    private String phone;
    private String realName;

    public static String genOtherStr(){
        return EncryptUtil.gen(12);
    }

    public static String password(String password, String otherStr){
        return EncryptUtil.md5(password + otherStr);
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

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
