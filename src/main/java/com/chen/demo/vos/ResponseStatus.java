package com.chen.demo.vos;

public class ResponseStatus {
    
    public static final Status SUCCESS = new Status(1000, "success");
    public static final Status FAIL = new Status(1002, "服务器出错");
    
    public static final Status USER_NOT_EXIST = new Status(2001, "用户不存在");

    public static final Status LOGIN_USERNAME_OR_PASSWORD_ERROR = new Status(3001, "用户名不存在或者密码错误");

    public static final Status PERMISSION_CODE_EXIST = new Status(4001, "code已存在");
    public static final Status PERMISSION_API_EXIST = new Status(4002, "api已存在");
    public static final Status PERMISSION_NOT_EXIST = new Status(4003, "权限不存在");

    
    public static class Status{
        private int code;
        private String description;
        
        public Status(int code, String description){
            this.code = code;
            this.description = description;
        }

       public int code(){
            return this.code;
       } 
       
       public String description(){
           return this.description;
       }
    }
}
