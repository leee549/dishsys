package cn.lhx.dishsys.config;

import cn.lhx.dishsys.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lee549
 * @date 2020/3/14 20:27
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
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

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    //设置允许跨域的路径
   String[] methods = {"GET","POST","PUT","DELETE","HEAD","OPTIONS"};

    registry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowCredentials(true)
            .allowedHeaders("*");
  }
}
