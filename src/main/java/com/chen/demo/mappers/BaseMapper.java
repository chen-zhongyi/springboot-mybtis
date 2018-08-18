package com.chen.demo.mappers;

import com.chen.demo.models.BaseModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T extends BaseModel> {

    int insert(@Param("map") Map<String, Object> map);

    int update(@Param("map") Map<String, Object> map, @Param("id") Long id);

    int delete(@Param("id") Long id);

    T findByID(@Param("id") Long id);

    List<T> fetchAll();
}
