package cn.lhx.dishsys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.lhx.dishsys.dao.MenuDao;
import cn.lhx.dishsys.entity.Menu;
import cn.lhx.dishsys.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lee549
 * @date 2020/5/28 20:59
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao,Menu> implements MenuService {

    @Resource MenuDao menuDao;

    @Override
    public Map<String, Object> getMenuByRole(Integer id) {
        //查询该角色的菜单列表
        List<Menu> rootMenu = menuDao.selectByRoleId(id);
        //最终的结果
        List<Menu> menuList = new ArrayList<>();
        // 先找到所有一级菜单
        for (int i=0;i<rootMenu.size();i++){
            if (ObjectUtil.isEmpty(rootMenu.get(i).getParentId())){
                menuList.add(rootMenu.get(i));
            }
        }
        //为一级菜单设置子菜单,递归调用
        for (Menu menu :menuList){
            menu.setChildMenus(getChild(menu.getId(),rootMenu));
        }
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("menu",menuList);
        // System.out.println(map);
        return map;

    }

    /**
     * 递归查找菜单
     * @param id 当前菜单id
     * @param rootMenu 要查找的菜单
     * @return
     */
    public List<Menu> getChild(Integer id,List<Menu> rootMenu){
        //子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu:rootMenu){
            //遍历所有节点,将父id 与传过来的id比较
            if (ObjectUtil.isNotEmpty(menu.getParentId())){
                if (menu.getParentId().equals(id)){
                    childList.add(menu);
                }
            }
        }
        //遍历子菜单的子菜单
       for (Menu menu :childList){
           if (ObjectUtil.isEmpty(menu.getUrl())){
               //递归
               menu.setChildMenus(getChild(menu.getId(),rootMenu));
           }
       }
        return childList;
    }
}
