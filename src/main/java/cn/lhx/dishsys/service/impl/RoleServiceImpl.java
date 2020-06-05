package cn.lhx.dishsys.service.impl;

import cn.lhx.dishsys.dao.RoleDao;
import cn.lhx.dishsys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.Set;

/**
 * @author lee549
 * @date 2020/5/30 20:11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
    @Resource
    RoleDao roleDao;

    @Override
    public Set<String> getByUserId(Integer id) {
        return roleDao.getByUserId(id);
    }
}
