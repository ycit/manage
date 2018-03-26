package com.ycit.mapper;

import com.ycit.bean.modal.dict.Info;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictInfoMapper {

    @Select("select * from dict_info where type_id = #{typeId}")
    List<Info> findByTypeId(@Param("typeId")int typeId);

    @Delete("delete from dict_info where type_id = #{typeId}")
    int deleteByTypeId(@Param("typeId")int typeId);

    int insertBatch(@Param("infos")List<Info> infos);

    @Select("select * from dict_info where id = #{id}")
    Info findById(@Param("id")int id);

}
