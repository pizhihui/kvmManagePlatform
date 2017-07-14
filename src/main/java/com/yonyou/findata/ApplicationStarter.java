package com.yonyou.findata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Description
 * @Author pizhihui
 * @Date 2017/4/17
 */
@SpringBootApplication
@ServletComponentScan(basePackages = {"com.yonyou.findata.listener"})
@EnableTransactionManagement
@ImportResource("classpath:spring/spring.xml")
public class ApplicationStarter extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationStarter.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(ApplicationStarter.class, args);

    }

//    final private CityMapper cityMapper;
//
//    public ApplicationStarter(CityMapper cityMapper) {
//        this.cityMapper = cityMapper;
//    }
//
//    public void run(String... args) throws Exception {
//        System.out.println();
//    }
}
