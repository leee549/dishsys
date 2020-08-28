package cn.lhx.dishsys.controller;

import cn.lhx.dishsys.core.base.JsonResult;
import cn.lhx.dishsys.core.enmus.ResultCode;
import cn.lhx.dishsys.entity.UserInfo;
import cn.lhx.dishsys.service.PermissionService;
import cn.lhx.dishsys.service.RoleService;
import cn.lhx.dishsys.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author lee549
 * @date 2020/5/21 22:10
 */
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;

    // @RequiresPermissions("user:list")
    @GetMapping("/user")
    public JsonResult<Object> getUser(){
        Set<String> byEmpId = roleService.getByUserId(1);
        Set<String> permissionByRoleId = permissionService.getPermissionByRoleId(1);
       return JsonResult.success("success!!!!");

    }
    @GetMapping("/image")
    public JsonResult<Object> getPicture(Integer id) {
        UserInfo user = this.userService.getById(id);
        return JsonResult.success(user.getFaceImg(), ResultCode.SUCCESS.val);
    }


}
