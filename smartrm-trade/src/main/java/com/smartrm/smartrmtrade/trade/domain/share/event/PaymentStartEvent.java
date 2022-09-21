package com.smartrm.smartrmtrade.trade.domain.share.event;

import com.smartrm.infracore.event.DomainEvent;

/**
 * @author: yoda
 * @description:
 */
public class PaymentStartEvent extends DomainEvent {
    
    private Long paymentId;
    private Long orderId;
    private Long time;
    
    public PaymentStartEvent() {
        super("PaymentStartEvent");
    }
    
    public Long getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    
    public Long getTime() {
        return time;
    }
    
    public void setTime(Long time) {
        this.time = time;
    }
    
    @Override
    public String key() {
        return paymentId.toString();
    }
}
