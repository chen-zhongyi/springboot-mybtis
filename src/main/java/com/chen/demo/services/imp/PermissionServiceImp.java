package com.chen.demo.services.imp;

import com.chen.demo.mappers.PermissionMapper;
import com.chen.demo.models.Permission;
import com.chen.demo.services.PermissionService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImp extends BaseServiceImp<Permission> implements PermissionService, InitializingBean{

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    public void setBaseMapper(){
        super.setBaseMapper(permissionMapper);
    }

    /**
     * 初始化函数
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception{
    }

    @Override
    public Permission findByCode(String code){
        return permissionMapper.findByCode(code);
    }

    @Override
    public Permission findByApi(String api){
        return permissionMapper.findByApi(api);
    }
}
