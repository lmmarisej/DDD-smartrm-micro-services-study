package com.smartrm.smartrmtrade.trade.event;

import com.smartrm.infracore.event.DomainEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description:
 */
@Component
public class TestEventHandler extends DomainEventHandler<TestEvent> {
    
    private static Logger LOGGER = LoggerFactory.getLogger(TestEventHandler.class);
    
    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        LOGGER.info("test event");
    }
}
