package cn.lhx.dishsys.controller;

import cn.lhx.dishsys.core.base.JsonResult;
import cn.lhx.dishsys.entity.UserInfo;
import cn.lhx.dishsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lee549
 * @date 2020/5/21 22:10
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/user")
    public JsonResult<Object> getUser(){
        // userService

        return JsonResult.success();
    }


}
