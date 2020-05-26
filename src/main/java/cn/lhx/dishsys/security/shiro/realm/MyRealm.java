package cn.lhx.dishsys.security.shiro.realm;

import cn.hutool.core.util.ObjectUtil;

import cn.lhx.dishsys.entity.UserInfo;
import cn.lhx.dishsys.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lee549
 * @date 2020/4/8 19:45
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    // @Autowired
    // private PermissionService permissionService;
    // @Autowired
    // private RoleService roleService;

    /**
     * 授权 分配角色
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
      // // 获得username
      // Employee employee = (Employee) principals.getPrimaryPrincipal();
      // // 判断是否管理员 1 是 0 否；
      // Boolean isAdmin = employee.getAdmin();
      //
      // SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
      //
      // // 设置权限
      // if (isAdmin) {
      //   info.setStringPermissions(permissionService.queryAllExpression());
      // } else {
      //   info.setStringPermissions(permissionService.queryExpressionByEmployeeId(employee.getId()));
      // }
      // // 设置角色
      // info.setRoles(roleService.querySnByEmployeeId(employee.getId()));
      // return info;
        return null;
    }

    /**
     * @param token 用户的身份信息 ：用户名 、密码
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        // 获取用户输入的用户名
        String username = (String) token.getPrincipal();
        // 查询数据库用户名
        UserInfo userInfo = userService.selectByName(username);
        System.out.println("认证：" + username);
        if (ObjectUtil.isEmpty(userInfo)) {

            throw new UnknownAccountException("用户:" + username + "不存在");
        }

        // 定义盐
        String salt = userInfo.getUserId().toString();

        return new SimpleAuthenticationInfo(
                userInfo,
                userInfo.getUserPass(),
                ByteSource.Util.bytes(salt),
                getName());
    }



}
