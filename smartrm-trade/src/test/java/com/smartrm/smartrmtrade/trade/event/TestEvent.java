package com.smartrm.smartrmtrade.trade.event;

import com.smartrm.infracore.event.DomainEvent;

/**
 * @author: yoda
 * @description:
 */
public class TestEvent extends DomainEvent {
    
    
    public TestEvent() {
        super("test.event");
    }
    
    @Override
    public String key() {
        return null;
    }
}
