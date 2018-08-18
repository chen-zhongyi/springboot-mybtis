package com.chen.demo.mappers;

import com.chen.demo.models.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Alias("account")
public interface AccountMapper extends BaseMapper<Account>{

    Account findByUsername(@Param("username") String username);
}
