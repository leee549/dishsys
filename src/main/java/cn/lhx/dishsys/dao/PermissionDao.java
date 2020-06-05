package cn.lhx.dishsys.dao;

import cn.lhx.dishsys.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Set;

/**
 * @author lee549
 * @date 2020/5/30 20:09
 */
public interface PermissionDao extends BaseMapper<Permission> {
    /**
     * 根据角色Id查询权限
     *
     * @param id 角色id
     * @return 返回权限列表
     */
    Set<String> selectPermissionByRoleId(Integer id);

}
