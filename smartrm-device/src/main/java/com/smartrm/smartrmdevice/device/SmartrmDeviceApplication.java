package com.smartrm.smartrmdevice.device;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.smartrm.smartrmdevice.device",
        "com.smartrm.infracore"})
@MapperScan({"com.smartrm.smartrmdevice.device.infrastructure.mapper",
        "com.smartrm.infracore.idgenerator.impl.mapper"})
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SmartrmDeviceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SmartrmDeviceApplication.class, args);
    }
    
}
