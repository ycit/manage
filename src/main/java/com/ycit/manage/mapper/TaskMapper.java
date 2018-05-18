package com.ycit.manage.mapper;

import com.ycit.manage.bean.modal.Task;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ${DESCRIPTION}
 * <p>
 * Created by xlch at 2018/5/7
 */
@Repository
public interface TaskMapper {

    @Select("select * from task where id = #{id}")
    Task findById(@Param("id")int id);

    @Select("select * from task where group_id = #{groupId} and status != -1")
    List<Task> findByGroup(@Param("groupId")int groupId);

    @Select("select * from task where status != -1")
    List<Task> findAll();

    @Insert("insert into task (name,group_id,group_name,cron,status) values (#{task.name},#{task.groupId},#{task.groupName}, #{task.cron},#{task.status})")
    @Options(useGeneratedKeys = true, keyColumn = "task.id", keyProperty = "task.id")
    int insert(@Param("task") Task task);

    @Update("update task set name = #{task.name}, group_id = #{task.groupId}," +
            "group_name = #{task.groupName}, cron = #{task.cron}, status = #{task.status}, modify_time = current_timestamp where id = #{task.id}")
    int updateById(@Param("task")Task task);


    @Update("update task set status = #{status} where id = #{id}")
    int updateStatusById(@Param("id")int id, @Param("status")int status);


}
