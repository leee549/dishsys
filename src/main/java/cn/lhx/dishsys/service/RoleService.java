package cn.lhx.dishsys.service;

import com.baomidou.mybatisplus.extension.service.IService;

import javax.management.relation.Role;
import java.util.Set;

/**
 * @author lee549
 * @date 2020/5/30 20:10
 */
public interface RoleService extends IService<Role> {
    /**
     * 赋予角色
     * @return
     * @param id 用户id
     */
    Set<String> getByUserId(Integer id);
}
