package com.ycit.manage.mapper;

import com.ycit.manage.bean.modal.Job;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${DESCRIPTION}
 * <p>
 * Created by xlch at 2018/4/26
 */
@Repository
public interface JobMapper {

    @Select("select * from job where id = #{id}")
    Job findById(@Param("id") int id);

    @Select("select * from job where dept_id = #{deptId} and status = 0")
    List<Job> findByDeptId(@Param("deptId")int deptId);

}
