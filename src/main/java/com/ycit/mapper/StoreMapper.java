package com.ycit.mapper;

import com.ycit.bean.modal.Store;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreMapper {

    @Select("select * from store")
    List<Store> findAll();

    @Insert("insert into store (name, tel, province_id, city_id) values (#{store.name}," +
            "#{store.tel}, #{store.provinceId}, #{store.cityId})")
    @Options(useGeneratedKeys = true, keyProperty = "store.id", keyColumn = "store.id")
    int insert(@Param("store")Store store);

    @Select("select * from store where id = #{id}")
    Store findById(@Param("id")int id);

    @Delete("delete from store where id = #{id}")
    int deleteById(@Param("id")int id);

    int updateById(@Param("store")Store store);

    @Insert("insert into store (img) values (#{store.img})")
    @Options(useGeneratedKeys = true, keyProperty = "store.id", keyColumn = "store.id")
    int insertImg(@Param("store")Store store);

    @Update("update store set img = #{img}, modify_time = sysdate() where id = #{id}")
    int updateImg(@Param("id")int id, @Param("img")String img);



}
