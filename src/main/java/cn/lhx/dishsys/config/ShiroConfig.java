package cn.lhx.dishsys.config;

import cn.lhx.dishsys.core.filter.JwtFilter;
import cn.lhx.dishsys.security.shiro.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lee549
 * @date 2020/4/8 13:27
 */
@Configuration
public class ShiroConfig {

  /**
   * 3.shiroFilter shiro过滤器
   *
   * @return
   */
  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
    ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
    bean.setSecurityManager(securityManager);

    // 添加自己的过滤器并且取名为jwt
    Map<String, Filter> filterMap = new HashMap<>(2);
    filterMap.put("jwt", new JwtFilter());
    bean.setFilters(filterMap);
    // bean.setUnauthorizedUrl("/401");

    //自定义url规则
    Map<String, String> filters = new LinkedHashMap<>();
    // 所有请求通过我们自己的JWT Filter
    filters.put("/captcha", "anon");
    filters.put("/auth/login", "anon");
    filters.put("/auth/logout", "logout");
    // filters.put("/**", "authc");
    filters.put("/unauthorized/**", "jwt");
    filters.put("/**", "jwt");

    // filters.put("/login", "anon");

    // 访问401和404页面不通过我们的Filter
    bean.setFilterChainDefinitionMap(filters);
    return bean;


  }

  /**
   * 2.SecurityManager 安全管理器
   *
   * @return
   */
  @Bean
  public DefaultWebSecurityManager securityManager() {
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    //关shiro session
    DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
    DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
    defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
    subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
    securityManager.setSubjectDAO(subjectDAO);

    securityManager.setRealm(myRealm());
    return securityManager;
  }

  /**
   * 1.创建myRealm
   *
   * @return
   */
  @Bean(name = "myRealm")
  public MyRealm myRealm() {
    MyRealm myRealm = new MyRealm();
    // 给 Realm 注入密文匹配Bean
    // myRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
    // myRealm.setAuthenticationCachingEnabled(true);缓存登录
    // myRealm.setAuthorizationCachingEnabled(true);
    // myRealm.setCachingEnabled(true);
    return myRealm;
  }

  /**
   * 凭证匹配器
   *
   * @return
   */
  @Bean(name = "hashedCredentialsMatcher")
  public HashedCredentialsMatcher getHashedCredentialsMatcher() {
    HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("MD5");
    hashedCredentialsMatcher.setHashIterations(10000);
    // 哈希是Hex 编码的话为true，base64的话为false
    hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
    return hashedCredentialsMatcher;
  }

  /**
   * 开启Shiro注解(如@RequiresRoles,@RequiresPermissions), 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
   * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)
   */
  @Bean
  public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    advisorAutoProxyCreator.setProxyTargetClass(true);
    return advisorAutoProxyCreator;
  }
  /** 开启aop注解支持 @requirePermission */
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      DefaultWebSecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
        new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }

  @Bean(name = "lifecycleBeanPostProcessor")
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }
}
