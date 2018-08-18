package com.chen.demo.vos;

import com.chen.demo.annotations.*;
import com.chen.demo.models.User;

public class UserVo extends OneData{

    @NotNull(message = "用户名不能为空", method = "edit")
    @Size(min = 6, max = 20, message = "用户名长度在6到20之间")
    @DataField(name = "用户名")
    private String username;
    @Regex(value = "\\d{11}", message = "手机号码格式错误")
    @DataField(name = "手机号")
    private String phone;
    @Num(moreInt = 101, lessInt = 102)
    @DataField(name = "用户类型")
    private Integer type;

    public UserVo(){}

    public UserVo(User user){
        super(user);
        this.username = user.getUsername();
        this.phone = user.getPhone();
        this.type = user.getType().code();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
