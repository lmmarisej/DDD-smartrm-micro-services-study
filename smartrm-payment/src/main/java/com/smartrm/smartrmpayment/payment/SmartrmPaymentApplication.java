package com.smartrm.smartrmpayment.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {
        "com.smartrm.smartrmpayment.payment",
        "com.smartrm.infracore"
})
@MapperScan({"com.smartrm.smartrmpayment.payment.infrastructure.mapper",
        "com.smartrm.infracore.idgenerator.impl.mapper"})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SmartrmPaymentApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SmartrmPaymentApplication.class, args);
    }
    
}
