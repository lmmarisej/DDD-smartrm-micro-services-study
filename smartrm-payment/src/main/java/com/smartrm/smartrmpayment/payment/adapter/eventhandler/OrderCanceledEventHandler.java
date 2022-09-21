package com.smartrm.smartrmpayment.payment.adapter.eventhandler;

import com.smartrm.infracore.event.DomainEventHandler;
import com.smartrm.smartrmpayment.payment.application.service.PayService;
import com.smartrm.smartrmpayment.payment.domain.OrderInfo;
import com.smartrm.smartrmpayment.payment.domain.share.OrderCanceledEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description:
 */
@Component
public class OrderCanceledEventHandler extends DomainEventHandler<OrderCanceledEvent> {
    
    @Autowired
    private PayService payService;
    
    @Override
    public void onApplicationEvent(OrderCanceledEvent event) {
        payService.onOrderCanceled(
                OrderInfo.Builder().orderId(event.getOrderId())
                        .type(event.getType())
                        .machineId(event.getMachineId())
                        .totalAmount(event.getTotalAmount())
                        .build()
        );
    }
}
