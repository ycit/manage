package com.ycit.mapper;

import com.ycit.bean.modal.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    @Select("select * from admin where username = #{username}")
    Admin findByUsername(@Param("username")String username);

}
