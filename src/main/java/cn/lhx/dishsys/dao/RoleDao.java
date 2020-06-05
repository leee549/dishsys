package cn.lhx.dishsys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import javax.management.relation.Role;
import java.util.Set;

/**
 * @author lee549
 * @date 2020/5/30 20:09
 */
public interface RoleDao extends BaseMapper<Role> {
    /**
     * 赋予角色
     * @return
     * @param id 用户id
     */
    Set<String> getByUserId(Integer id);
}
