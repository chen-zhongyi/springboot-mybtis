package com.chen.demo.models;

import com.chen.demo.annotations.DataField;

public class Permission extends BaseModel{

    @DataField(name = "代码")
    private String code;
    @DataField(name = "描述")
    private String description;
    @DataField(name = "Api")
    private String api;
    @DataField(name = "是否需要权限验证")
    private Boolean isNeedValid;

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
