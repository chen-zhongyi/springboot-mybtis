package com.chen.demo.mappers;

import com.chen.demo.models.AccessToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccessTokenMapper extends BaseMapper<AccessToken> {
}
