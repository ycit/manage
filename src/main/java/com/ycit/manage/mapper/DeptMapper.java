package com.ycit.manage.mapper;

import com.ycit.manage.bean.modal.Dept;
import com.ycit.manage.bean.vo.DeptTree;
import com.ycit.manage.bean.vo.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${DESCRIPTION}
 * <p>
 * Created by xlch at 2018/4/25
 */
@Repository
public interface DeptMapper {

    @Select("select * from dept where id = #{id}")
    Dept findById(@Param("id") int id);

    @Select("select id, full_name as name,pid AS p_id,( CASE WHEN (pid = 0 OR pid IS NULL) THEN 'true' ELSE 'false' END ) as isOpen from dept where status = 0 order by id asc")
    List<ZTreeNode> findTree();

}
