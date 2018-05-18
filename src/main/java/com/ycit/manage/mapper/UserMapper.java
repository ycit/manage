package com.ycit.manage.mapper;

import com.ycit.manage.bean.modal.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    @Select("select * from USER where username = #{username} and status = 0")
    User findByUsername(@Param("username")String username);

    @Select("select * from USER where username = #{username} and id != #{id}")
    User findByIdAndUsername(@Param("id")int id, @Param("username")String username);

    @Delete("update user set status = -1 where id = #{id}")
    int deleteById(@Param("id")int id);

    @Select("select * from USER where status = 0")
    List<User> finds();

    @Select("select * from USER where id = #{id}")
    User findById(@Param("id") int id);

    int insert(@Param("user")User user);

    @Update("update user set img = #{img} where id = #{id}")
    int updateImg(@Param("id")int id, @Param("img")String img);

    int update(@Param("user")User user);

    @Select("select password from user where id = #{id}")
    String findPwById(@Param("id")int id);

    @Update("update user set password = #{pw} where id = #{id}")
    int updatePwById(@Param("id")int id, @Param("pw")String pw);

    @Update("update user set role = #{role} where id = #{id}")
    int updateRoleById(@Param("id")int id, @Param("role")String role);

}
