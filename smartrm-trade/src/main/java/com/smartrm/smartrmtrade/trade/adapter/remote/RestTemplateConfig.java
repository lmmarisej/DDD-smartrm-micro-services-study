package com.smartrm.smartrmtrade.trade.adapter.remote;

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
    
    @Value("${remote.baseUri.pay}")
    private String basePayUri;
    
    @Value("${remote.baseUri.device}")
    private String baseDeviceUri;
    
    @Value("${remote.baseUri.user}")
    private String baseUserUri;
    
    @Value("${remote.baseUri.commodity}")
    private String baseCommodityUri;
    
    @Value("${remote.connectTimeout}")
    private long connectTimeOut;
    
    @Value("${remote.readTimeout}")
    private long readTimeOut;
    
    @Bean
    public RestTemplate payRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(basePayUri).setConnectTimeout(Duration.ofMillis(connectTimeOut))
                .setReadTimeout(Duration.ofMillis(readTimeOut)).build();
    }
    
    @Bean
    public RestTemplate deviceRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(baseDeviceUri).setConnectTimeout(Duration.ofMillis(connectTimeOut))
                .setReadTimeout(Duration.ofMillis(readTimeOut)).build();
    }
    
    @Bean
    public RestTemplate userRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(baseUserUri).setConnectTimeout(Duration.ofMillis(connectTimeOut))
                .setReadTimeout(Duration.ofMillis(readTimeOut)).build();
    }
    
    @Bean
    public RestTemplate commodityRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(baseCommodityUri).setConnectTimeout(Duration.ofMillis(connectTimeOut))
                .setReadTimeout(Duration.ofMillis(readTimeOut)).build();
    }
    
    
}
