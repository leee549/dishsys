package cn.lhx.dishsys.config;

import cn.lhx.dishsys.component.LoginHandlerInterceptor;
import cn.lhx.dishsys.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author lee549
 * @date 2020/3/14 20:27
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //路径映射，addResourceHandler：虚拟路径     addResourceLocations：物理路径
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    registry.addResourceHandler("/file/**").addResourceLocations("file:f:/");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("login");
    //registry.addViewController("/index.html").setViewName("login");
    //registry.addViewController("/login").setViewName("login");
    // registry.addViewController("/main.html").setViewName("dashboard");

  }

  @Bean
  public LocaleResolver localeResolver() {
    return new MyLocaleResolver();
  }

  // @Override
  // public void addInterceptors(InterceptorRegistry registry) {
  //              registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
  //                      .excludePathPatterns("/index.html","/","/user/login","/static/**","/favicon.ico", "/asserts/**", "/webjars/**","/captcha");
  // }
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    //设置允许跨域的路径
    registry.addMapping("/**")

            //设置允许跨域请求的域名
            .allowedOrigins("*")
            //这里：是否允许证书 不再默认开启
            .allowCredentials(true)
            //设置允许的方法
            .allowedMethods("*")
            //跨域允许时间
            .maxAge(3600);
  }


}
