package com.chen.demo.vos;

import com.chen.demo.models.BaseModel;
import org.apache.commons.lang3.BooleanUtils;

public abstract class OneData extends Data{

    private Long id;
    private Long createTime;
    private Long updateTime;
    private Integer isDeleted;
    private Integer isEnable;

    public OneData(){}

    public OneData(BaseModel base){
        this.id = base.getId();
        this.createTime = base.getCreateTime();
        this.updateTime = base.getUpdateTime();
        this.isDeleted = BooleanUtils.toInteger(base.getDeleted());
        this.isEnable = BooleanUtils.toInteger(base.getEnable());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }
}
