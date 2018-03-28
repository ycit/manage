package com.ycit.mapper;

import com.ycit.bean.entity.GoodsSearchForm;
import com.ycit.bean.modal.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品
 *
 * @author xlch
 * @Date 2018-03-22 10:20
 */
@Repository
public interface GoodsMapper {

    int insert(@Param("goods")Goods goods);

    List<Goods> finds(@Param("goods")GoodsSearchForm goods);

    @Delete("delete from goods where id = #{id}")
    int deleteById(@Param("id")int id);

    @Update("update goods set img = #{img},thumbnail = #{thumbnail} where id = #{id}")
    int updateById(@Param("id") int id, @Param("img")String img, @Param("thumbnail")String thumbnail);

    @Select("select * from goods where id = #{id}")
    Goods findById(@Param("id")int id);

    int updateBeanById(@Param("goods")Goods goods);

}
