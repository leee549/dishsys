package cn.lhx.dishsys;

import cn.lhx.dishsys.dao.MenuDao;
import cn.lhx.dishsys.dao.PermissionDao;
import cn.lhx.dishsys.entity.Menu;
import cn.lhx.dishsys.entity.UserInfo;
import cn.lhx.dishsys.service.MenuService;
import cn.lhx.dishsys.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class DishsysApplicationTests {
    @Resource
    private UserService userService;
    @Resource
    MenuService menuService;
    @Resource
    MenuDao menuDao;

    @Resource
    PermissionDao permissionDao;

    @Test
    void test(){
        UserInfo admin = userService.selectByName("admin");
        System.out.println(admin);

    }
    @Test
    void test1(){
        List<Menu> menus = menuDao.selectByRoleId(1);
        System.out.println(menus);
    }
    @Test
    void testMenu(){
//        List<Menu> menuByRole = menuService.getMenuByRole(1);
    }
    @Test
    void pers() {
        // List<Permission> perms = permissionDao.selectPermissionByRoleId(1L);
        // for (Permission pes : perms) {
        //     System.out.println(pes);
        // }

    }


    @Test
    void contextLoads() {
    }

}
