package com.wxy.newprictice;





import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.wxy.newprictice.dao")
@EnableCaching
public class NewpricticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewpricticeApplication.class, args);
    }

}
