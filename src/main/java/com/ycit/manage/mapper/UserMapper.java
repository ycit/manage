package com.ycit.manage.mapper;

import com.ycit.manage.bean.modal.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from USER where username = #{username}")
    User findByUsername(@Param("username")String username);

    @Delete("delete from USER where id = #{id}")
    int deleteById(@Param("id")int id);

    @Select("select * from USER")
    List<User> finds();

    @Select("select * from USER where id = #{id}")
    User findById(@Param("id") int id);

    @Insert("insert USER (username,password) values (#{user.username},#{user.password})")
    @Options(useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "user.id")
    int insert(@Param("user")User user);

}
