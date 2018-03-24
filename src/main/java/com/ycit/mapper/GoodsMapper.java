package com.ycit.mapper;

import com.ycit.bean.entity.GoodsSearchForm;
import com.ycit.bean.modal.Goods;
import org.apache.ibatis.annotations.Param;
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

    int insert(@Param("Goods")Goods goods);

    List<Goods> finds(@Param("goods")GoodsSearchForm goods);

}
