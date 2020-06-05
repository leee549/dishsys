package cn.lhx.dishsys.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author lee549
 * @date 2020/5/31 13:04
 */
public class JwtToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
