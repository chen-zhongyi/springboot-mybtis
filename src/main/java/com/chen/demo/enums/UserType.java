package com.chen.demo.enums;

public enum UserType implements BaseEunm{

    ADMIN(100, "管理员"), MANAGER(101, "店长"), USER(102, "用户");

    private int code;
    private String intro;

    private UserType(int code, String intro){
        this.code = code;
        this.intro = intro;
    }

    public static UserType convert(Integer code){
        if(code == null)    return null;
        for(UserType type : UserType.values()){
            if(type.code() == code.intValue()){
                return type;
            }
        }
        return null;
    }

    @Override
    public int code(){
        return this.code;
    }

    @Override
    public String intro(){
        return this.intro;
    }
}
