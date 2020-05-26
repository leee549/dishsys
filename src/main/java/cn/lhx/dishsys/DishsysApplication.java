package cn.lhx.dishsys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lee549
 * @date 2020/5/21 21:46
 */
@SpringBootApplication
@MapperScan("cn.lhx.dishsys.dao")
public class DishsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(DishsysApplication.class, args);
    }

}
