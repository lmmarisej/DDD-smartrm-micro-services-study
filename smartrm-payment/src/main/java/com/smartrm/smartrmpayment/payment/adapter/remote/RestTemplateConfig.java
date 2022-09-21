package com.smartrm.smartrmpayment.payment.adapter.remote;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author: yoda
 * @description:
 */
@Configuration
public class RestTemplateConfig {
    
    @Value("${remote.baseUri.trade}")
    private String baseTradeUri;
    
    @Value("${remote.connectTimeout}")
    private long connectTimeOut;
    
    @Value("${remote.readTimeout}")
    private long readTimeOut;
    
    @Bean
    public RestTemplate tradeRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(baseTradeUri).setConnectTimeout(Duration.ofMillis(connectTimeOut))
                .setReadTimeout(Duration.ofMillis(readTimeOut)).build();
    }
    
}
