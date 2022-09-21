package com.smartrm.smartrmtrade.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.smartrm.smartrmtrade.trade",
        "com.smartrm.infracore"})
@MapperScan({"com.smartrm.smartrmtrade.trade.infrastructure.mapper",
        "com.smartrm.infracore.idgenerator.impl.mapper"})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SmartrmTradeApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SmartrmTradeApplication.class, args);
    }
    
}
