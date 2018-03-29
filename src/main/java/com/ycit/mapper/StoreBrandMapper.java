package com.ycit.mapper;

import com.ycit.bean.modal.StoreBrand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreBrandMapper {


    int insertBatch(@Param("storeBrands")List<StoreBrand> storeBrands);

    @Select("select * from store_brand where store_id = #{storeId}")
    List<StoreBrand> findByStoreId(@Param("storeId")int storeId);

    @Delete("delete from store_brand where store_id = #{storeId}")
    int deleteByStoreId(@Param("storeId")int storeId);

}
