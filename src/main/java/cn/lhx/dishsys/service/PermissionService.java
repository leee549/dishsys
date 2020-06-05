package cn.lhx.dishsys.service;

import cn.lhx.dishsys.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * @author lee549
 * @date 2020/5/30 20:11
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 据角色Id查询权限
     * @param id 角色Id
     * @return 权限列表
     */
    Set<String> getPermissionByRoleId(Integer id);

    /**
     * 获取所有权限列表
     * @return
     */
    Set<String> getAllPermission();
}
