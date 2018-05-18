package com.ycit.manage.mapper;

import com.ycit.manage.bean.modal.JobMenu;
import com.ycit.manage.bean.modal.Menu;
import com.ycit.manage.bean.vo.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 * <p>
 * Created by xlch at 2018/5/14
 */
@Repository
public interface JobMenuMapper {

    @Select("select * from job_menu where job_id = #{jobId} and status = 0")
    List<JobMenu> findByJobId(@Param("jobId")int jobId);

    @Select("select m.* from job_menu jm left outer join menu " +
            "m on jm.menu_id = m.id where jm.job_id = #{jobId} and jm.status = 0")
    List<Menu> findMenuByJobId(@Param("jobId")int jobId);

    @Select("select menu_id from job_menu where job_id = #{jobId} and status = 0")
    List<Integer> findMenuIdByJobId(@Param("jobId")int jobId);

    @Update("update job_menu set status = -1 where job_id = #{jobId} and status = 0")
    int deleteByJobId(@Param("jobId")int jobId);

    int insertBatch(@Param("jobMenus")List<JobMenu> jobMenus);



}


