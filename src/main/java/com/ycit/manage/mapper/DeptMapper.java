package com.ycit.manage.mapper;

import com.ycit.manage.bean.modal.Dept;
import com.ycit.manage.bean.vo.ZTreeNode;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 * <p>
 * Created by xlch at 2018/4/25
 */
@Repository
public interface DeptMapper {

    @Select("select * from dept where id = #{id}")
    Dept findById(@Param("id") int id);

    @Select("select id, full_name as name,p_id,( CASE WHEN (p_id = 0 OR p_id IS NULL) THEN 'true' ELSE 'false' END ) as isOpen from dept where status = 0 order by id asc")
    List<ZTreeNode> findTree();

    @Select("select * from dept where status = 0 order by level asc")
    List<Dept> finds();

    @Insert("insert into dept (full_name, nickname, level, p_id) values (#{dept.fullName}," +
            "#{dept.nickname}, #{dept.level}, #{dept.pId})")
    @Options(useGeneratedKeys = true, keyColumn = "dept.id", keyProperty = "dept.id")
    int insert(@Param("dept")Dept dept);

    @Update("update dept set status = -1, modify_time = current_timestamp where p_ids like CONCAT(CONCAT('%', #{id}),'%')")
    int deleteById(@Param("id")int id);

    @Select("select * from dept where p_id = #{id} and status = 0")
    List<Dept> findByPId(@Param("id")int id);

    @Update("update dept set p_ids = #{pIds} where id = #{id}")
    int updatePIdsById(@Param("id")int id, @Param("pIds")String pIds);

    @Update("update dept set full_name = #{dept.fullName}, nickname = #{dept.nickname}," +
            "level = #{dept.level}, p_id = #{dept.pId}, p_ids = #{dept.pIds} where id = #{dept.id}")
    int update(@Param("dept")Dept dept);

}
