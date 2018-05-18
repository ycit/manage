package com.ycit.manage.mapper;

import com.ycit.manage.bean.modal.Menu;
import com.ycit.manage.bean.vo.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 * <p>
 * Created by xlch at 2018/5/14
 */
@Repository
public interface MenuMapper {

    List<ZTreeNode> findTreeByMenuId(@Param("menuIds")List<Integer> menuIds);

    List<ZTreeNode> findOriginalTree();

}
