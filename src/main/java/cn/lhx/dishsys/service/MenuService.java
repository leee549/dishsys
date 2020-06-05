package cn.lhx.dishsys.service;

import cn.lhx.dishsys.dao.MenuDao;
import cn.lhx.dishsys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author lee549
 * @date 2020/5/28 20:59
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据角色Id获取菜单
     * @param id 角色id
     * @return
     */
    Map<String, Object> getMenuByRole(Integer id);

}
