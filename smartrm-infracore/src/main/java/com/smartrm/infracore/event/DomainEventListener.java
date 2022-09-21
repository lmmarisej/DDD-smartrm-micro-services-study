package com.smartrm.infracore.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author: yoda
 * @description:
 */
public class DomainEventListener implements Runnable {
    
    private static Logger LOGGER = LoggerFactory.getLogger(DomainEventListener.class);
    
    private KafkaConsumer<String, String> kafkaConsumer;
    private DomainEventHandler handler;
    private Class eventType;
    private boolean isClose;
    
    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    
    
    public DomainEventListener(Properties props, Class eventType, DomainEventHandler handler) {
//    kafkaConsumer = new KafkaConsumer<String, String>(props);
        this.eventType = eventType;
        this.handler = handler;
    }
    
    public void close() {
        isClose = true;
    }
    
    @Override
    public void run() {
    kafkaConsumer.subscribe(Arrays.asList(eventType.getSimpleName()));
    while (!isClose) {
      ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
      for (ConsumerRecord<String, String> record : records) {
        LOGGER.info("listener receive msg:{}", record.value());
        try {
          DomainEvent event = (DomainEvent) objectMapper.readValue(record.value(), eventType);
          handler.onApplicationEvent(event);
        } catch (Exception e) {
          LOGGER.error("fail to handle event.", e);
          LOGGER.info("failed event:{}", record.value());
        }
      }
      kafkaConsumer.commitAsync();
    }
    }
}
