package cn.lhx.dishsys.service.impl;

import cn.lhx.dishsys.dao.PermissionDao;
import cn.lhx.dishsys.entity.Permission;
import cn.lhx.dishsys.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lee549
 * @date 2020/5/30 20:11
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {
    @Resource PermissionDao permissionDao;

    @Override
    public Set<String> getPermissionByRoleId(Integer id) {
        return permissionDao.selectPermissionByRoleId(id);
    }

    @Override
    public Set<String> getAllPermission() {
        List<Permission> perms = this.list();
        Set<String> set = new HashSet<>(2);
        perms.forEach(perm->set.add(perm.getExpression()));
        return set;
        // return permissionDao.listAll();
    }
}
