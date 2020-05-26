package cn.lhx.dishsys;

import cn.lhx.dishsys.entity.UserInfo;
import cn.lhx.dishsys.service.UserService;
import cn.lhx.dishsys.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
class DishsysApplicationTests {
    @Resource
    private UserService userService;
    @Test
    void test(){
        UserInfo admin = userService.selectByName("admin");
        System.out.println(admin);

    }

    @Test
    void contextLoads() {
    }

}
