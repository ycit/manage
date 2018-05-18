package com.ycit.manage.mapper;

import com.ycit.manage.bean.criteria.JobForm;
import com.ycit.manage.bean.modal.Job;
import org.apache.ibatis.annotations.*;
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

    @Select("select * from job where dept_id = #{deptId} and status = 0 order by id asc")
    List<Job> findByDeptId(@Param("deptId")int deptId);


    List<Job> finds(@Param("job")JobForm job);

    @Insert("insert into job (full_name, nickname, dept_id) values (#{job.fullName}, #{job.nickname}, #{job.deptId})")
    @Options(useGeneratedKeys = true, keyProperty = "job.id", keyColumn = "job.id")
    int insert(@Param("job")Job job);

    @Update("update job set status = -1, modify_time = current_timestamp where id =#{id}")
    int deleteById(@Param("id")int id);

    @Update("update job set full_name = #{job.fullName}, nickname = #{job.nickname}," +
            "dept_id = #{job.deptId}, modify_time = current_timestamp where id = #{job.id}")
    int update(@Param("job")Job job);

}
