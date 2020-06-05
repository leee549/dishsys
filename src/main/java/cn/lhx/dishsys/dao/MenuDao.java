package cn.lhx.dishsys.dao;

import cn.lhx.dishsys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lee549
 * @date 2020/5/28 21:00
 */
public interface MenuDao extends BaseMapper<Menu> {
    /**
     * 根据角色查找菜单
     * @param id
     * @return
     */
    List<Menu> selectByRoleId(@Param("id") Integer id);
}
