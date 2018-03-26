package com.ycit.mapper;

import com.ycit.bean.modal.GoodsAlbum;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsAlbumMapper {

    @Insert("insert into goods_album (goods_id, img, thumbnail) values (#{goodsAlbum.goodsId}, #{goodsAlbum.img}, #{goodsAlbum.thumbnail})")
    @Options(useGeneratedKeys = true, keyColumn = "goodsAlbum.id", keyProperty = "goodsAlbum.id")
    int insert(@Param("goodsAlbum") GoodsAlbum goodsAlbum);

    @Delete("delete from goods_album where goods_id = #{goodsId}")
    int deleteByGoodsId(@Param("goodsId") int goodsId);

    @Select("select * from goods_album where goods_id = #{goodsId}")
    List<GoodsAlbum> findByGoodsId(@Param("goodsId") int goodsId);

    @Select("select * from goods_album where id = #{id}")
    GoodsAlbum findById(@Param("id")int id);

}
