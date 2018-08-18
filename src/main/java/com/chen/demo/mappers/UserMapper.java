package com.chen.demo.mappers;

import com.chen.demo.models.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Alias("user")
public interface UserMapper extends BaseMapper<User>{

    User findByUsername(@Param("username") String username);
}
