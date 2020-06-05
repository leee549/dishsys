package cn.lhx.dishsys.security.shiro.realm;

import cn.hutool.core.util.ObjectUtil;
import cn.lhx.dishsys.entity.UserInfo;
import cn.lhx.dishsys.service.PermissionService;
import cn.lhx.dishsys.service.RoleService;
import cn.lhx.dishsys.service.UserService;
import cn.lhx.dishsys.util.JwtToken;
import cn.lhx.dishsys.util.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lee549
 * @date 2020/4/8 19:45
 */
public class MyRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权 分配角色
     * 只有当需要检测用户权限的时候才会调用此方法
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("进入shiro授权=====");
        // 获得username
        String username = JwtUtil.getUsername(principals.toString());
        UserInfo user = userService.selectByName(username);
        // 判断是否管理员 1 是 0 否；
        boolean isAdmin = user.getRoleId() == 3;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 设置权限
        if (isAdmin) {
            info.setStringPermissions(permissionService.getAllPermission());
        } else {
            info.setStringPermissions(permissionService.getPermissionByRoleId(user.getRoleId()));
        }
        // 设置角色
        info.setRoles(roleService.getByUserId(user.getUserId()));
        return info;
    }

    /**
     * @param auth 用户的身份信息 ：用户名 、密码
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth)
            throws AuthenticationException {
        logger.info("进入shiro认证");
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的
        String token = (String) auth.getCredentials();
        // 获取用户输入的用户名
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token校验不通过");
        }
        // 查询数据库用户名
        UserInfo userInfo = userService.selectByName(username);
        logger.info("认证：" + username);
        if (ObjectUtil.isEmpty(userInfo)) {
            throw new UnknownAccountException("用户:" + username + "不存在");
        }
        if (!JwtUtil.verify(token, username, userInfo.getUserPass())) {
            throw new AuthenticationException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(
                token,
                token,
                getName());
    }


}
