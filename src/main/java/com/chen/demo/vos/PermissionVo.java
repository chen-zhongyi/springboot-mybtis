package com.chen.demo.vos;

import com.chen.demo.annotations.DataField;
import com.chen.demo.annotations.NotNull;
import com.chen.demo.annotations.Num;
import com.chen.demo.annotations.Size;
import com.chen.demo.models.Permission;

public class PermissionVo extends OneData{

    @NotNull
    @DataField(name = "代码")
    private String code;
    @Size(max = 200, message = "不能超过200个字符")
    @DataField(name = "描述")
    private String description;
    @Size(max = 200, message = "不能超过200个字符")
    @DataField(name = "Api")
    private String api;
    @Num(moreInt = 0, lessInt = 1)
    @DataField(name = "是否需要权限验证")
    private Boolean isNeedValid;

    public PermissionVo(){}

    public PermissionVo(Permission permission){
        super(permission);
        this.code = permission.getCode();
        this.description = permission.getDescription();
        this.api = permission.getApi();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Boolean getNeedValid() {
        return isNeedValid;
    }

    public void setNeedValid(Boolean needValid) {
        isNeedValid = needValid;
    }
}
