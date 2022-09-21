package com.smartrm.smartrmpayment.payment.domain.share;


import com.smartrm.infracore.event.DomainEvent;

import java.math.BigDecimal;

/**
 * @author: yoda
 * @description:订单取消事件
 */
public class OrderCanceledEvent extends DomainEvent {
    
    private long machineId;
    private long orderId;
    private OrderType type;
    private BigDecimal totalAmount;
    
    public OrderCanceledEvent() {
        super("OrderCanceledEvent");
    }
    
    
    public long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public OrderType getType() {
        return type;
    }
    
    public void setType(OrderType type) {
        this.type = type;
    }
    
    public long getMachineId() {
        return machineId;
    }
    
    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
    
    @Override
    public String key() {
        return Long.toString(machineId);
    }
}
