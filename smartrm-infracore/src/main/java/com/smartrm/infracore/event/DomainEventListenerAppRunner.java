package com.smartrm.infracore.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yoda
 * @description:
 */
@Component
public class DomainEventListenerAppRunner implements ApplicationRunner {
    
    private static Logger LOGGER = LoggerFactory.getLogger(DomainEventListenerAppRunner.class);
    private static String groupId = "smartrm";
    @Resource
    private ApplicationContext applicationContext;
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final Map<Class, DomainEventListener> listeners = new ConcurrentHashMap<>();
    @Value("${kafka.server}")
    private String bootstrapServer;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", bootstrapServer);
        props.setProperty("group.id", groupId);
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        Collection<DomainEventHandler> handlers = applicationContext.getBeansOfType(DomainEventHandler.class).values();
        for (DomainEventHandler handler : handlers) {
            ParameterizedType parameterizedType = (ParameterizedType) handler.getClass().getGenericSuperclass();
            Class eventType = (Class) (parameterizedType.getActualTypeArguments()[0]);
            DomainEventListener listener = new DomainEventListener(props, eventType, handler);
            listeners.put(eventType, listener);
            executorService.execute(listener);
            LOGGER.info("created listener for event:{}", eventType.getSimpleName());
        }
        
    }
}
