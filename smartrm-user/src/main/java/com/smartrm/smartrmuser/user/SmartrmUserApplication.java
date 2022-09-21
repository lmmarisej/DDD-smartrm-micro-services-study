package com.smartrm.smartrmuser.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.smartrm.smartrmuser.user",
        "com.smartrm.infracore"})
@MapperScan({"com.smartrm.smartrmuser.user.infrastructure.mapper",
        "com.smartrm.infracore.idgenerator.impl.mapper"})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SmartrmUserApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SmartrmUserApplication.class, args);
    }
    
}
