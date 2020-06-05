package cn.lhx.dishsys.controller;

import cn.lhx.dishsys.core.base.JsonResult;
import cn.lhx.dishsys.core.enmus.ResultCode;
import cn.lhx.dishsys.entity.UserInfo;
import cn.lhx.dishsys.service.MenuService;
import cn.lhx.dishsys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lee549
 * @date 2020/5/29 13:12
 */
@RestController
public class MenuController {
    @Resource
    MenuService menuService;
    @Resource
    UserService userService;

    @RequiresPermissions("user:menu")
    @GetMapping("/menus")
    public JsonResult<Object> getMenuList() {
        System.out.println("token"+SecurityUtils.getSubject().getPrincipal());
        // String token = SecurityUtils.getSubject().getPrincipal().toString();
        //JwtUtil.getUsername(token)
        UserInfo user = userService.selectByName("admin");
        System.out.println(user);
        return JsonResult.success(menuService.getMenuByRole(user.getRoleId()), ResultCode.SUCCESS.val, "获取列表成功");
    }
}
