package com.ycit.mapper;

import com.ycit.bean.modal.dict.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictTypeMapper {

    @Select("select * from dict_type")
    List<Type> findAll();

    @Insert("insert into dict_type (name, description) values (#{type.name}, #{type.description})")
    @Options(useGeneratedKeys = true, keyColumn = "type.id", keyProperty = "type.id")
    int insert(@Param("type")Type type);

    @Delete("delete from dict_type where id = #{id}")
    int deleteById(@Param("id")int id);

    @Select("select * from dict_type where id = #{id}")
    Type findById(@Param("id")int id);

    @Update("update dict_type set name = #{type.name},description=#{type.description}, modify_time = sysdate() where id = #{type.id}")
    int updateById(@Param("type")Type type);

}
