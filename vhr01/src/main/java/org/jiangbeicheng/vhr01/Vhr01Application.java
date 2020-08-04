package org.jiangbeicheng.vhr01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.jiangbeicheng.vhr01.mapper")
public class Vhr01Application {

    public static void main(String[] args) {
        SpringApplication.run(Vhr01Application.class, args);
    }

}
