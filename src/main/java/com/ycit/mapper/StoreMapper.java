package com.ycit.mapper;

import com.ycit.bean.modal.dict.Info;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreMapper {

    @Select("select * from dict_store")
    List<Info> findAll();

}
