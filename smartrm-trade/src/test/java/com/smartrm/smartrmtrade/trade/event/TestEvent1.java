package com.smartrm.smartrmtrade.trade.event;

import com.smartrm.infracore.event.DomainEvent;

/**
 * @author: yoda
 * @description:
 */
public class TestEvent1 extends DomainEvent {
    
    public TestEvent1() {
        super("test.event1");
    }
    
    @Override
    public String key() {
        return null;
    }
}
