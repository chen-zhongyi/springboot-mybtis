package com.chen.demo.mappers;

import com.chen.demo.models.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Alias("permission")
public interface PermissionMapper extends BaseMapper<Permission>{

    Permission findByCode(@Param("code") String code);

    Permission findByApi(@Param("api") String api);
}
