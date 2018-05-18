package com.ycit.manage.mapper;

import com.ycit.manage.bean.modal.Config;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SystemMapper {

    @Select("select * from config")
    List<Config> finds();

    int updateBatch(@Param("configs")List<Config> configs);

    @Select("select value from config where name = #{name}")
    String findByName(@Param("name")String name);

}
