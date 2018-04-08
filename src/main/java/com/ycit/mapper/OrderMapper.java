package com.ycit.mapper;

import com.ycit.bean.criteria.OrderSearchForm;
import com.ycit.bean.modal.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    List<Order> finds(@Param("searchForm")OrderSearchForm searchForm);

    @Update("update order set status = #{status},send_time = sysdate() where id = #{id}")
    int updateStatusById(@Param("id")int id, @Param("status")int status);

    @Delete("delete from order where id = #{id}")
    int deleteById(@Param("id")int id);

}
