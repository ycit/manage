package com.ycit.mapper;

import com.ycit.bean.modal.News;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapper {

    @Insert("insert into news (url) values (#{news.url})")
    @Options(useGeneratedKeys = true, keyColumn = "news.id", keyProperty = "news.id")
    int insert(@Param("news")News news);

    @Update("update news set img = #{img} where id = #{id}")
    int updateImgById(@Param("id")int id, @Param("img")String img);

    @Select("select * from news where id = #{id}")
    News findById(@Param("id")int id);

    @Delete("delete from news where id = #{id}")
    int deleteById(@Param("id")int id);

    @Update("update news set url = #{news.url}, modify_time = sysdate() where id = #{news.id}")
    int updateById(@Param("news")News news);

    @Select("select * from news order by create_time desc limit #{n}")
    List<News> findTopN(@Param("n")int n);

}
