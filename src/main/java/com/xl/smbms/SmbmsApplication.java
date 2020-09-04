package com.xl.smbms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(basePackages = "com.xl.smbms.dao")
@SpringBootApplication
public class SmbmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmbmsApplication.class, args);
    }

}
