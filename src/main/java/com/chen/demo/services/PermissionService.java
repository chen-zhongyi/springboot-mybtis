package com.chen.demo.services;

import com.chen.demo.models.Permission;

public interface PermissionService extends BaseService<Permission>{

    Permission findByCode(String code);

    Permission findByApi(String api);
}
