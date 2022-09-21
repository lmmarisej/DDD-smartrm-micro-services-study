package com.smartrm.smartrmtrade.trade.domain.share.event;

import com.smartrm.infracore.event.DomainEvent;

/**
 * @author: yoda
 * @description:
 */
public class PopSuccessEvent extends DomainEvent {
    
    private long machineId;
    private long orderId;
    
    public PopSuccessEvent() {
        super("PopSuccessEvent");
    }
    
    public long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
    
    public long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    @Override
    public String key() {
        return Long.toString(machineId);
    }
}
