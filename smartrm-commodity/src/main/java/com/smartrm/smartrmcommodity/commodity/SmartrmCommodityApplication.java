package com.smartrm.smartrmcommodity.commodity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.smartrm.smartrmcommodity.commodity",
        "com.smartrm.infracore"})
@MapperScan({"com.smartrm.infracore.idgenerator.impl.mapper"})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SmartrmCommodityApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SmartrmCommodityApplication.class, args);
    }
    
}
