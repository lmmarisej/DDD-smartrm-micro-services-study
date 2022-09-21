package com.smartrm.smartrmpayment.payment.adapter.simulator;

import com.smartrm.infracore.event.DomainEventHandler;
import com.smartrm.smartrmpayment.payment.application.dto.PlatformPaymentResultDto;
import com.smartrm.smartrmpayment.payment.application.dto.PlatformResultCode;
import com.smartrm.smartrmpayment.payment.application.service.PayService;
import com.smartrm.smartrmpayment.payment.domain.PaymentState;
import com.smartrm.smartrmpayment.payment.domain.PaymentStateChangeEvent;
import com.smartrm.smartrmpayment.payment.domain.PlatformType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description:
 */
@Component
public class PaymentEventHandler extends DomainEventHandler<PaymentStateChangeEvent> {
    
    @Autowired
    PayService payService;
    
    @Override
    public void onApplicationEvent(PaymentStateChangeEvent paymentStateChangeEvent) {
        if (paymentStateChangeEvent.getCurState() == PaymentState.WaitingForDeduction) {
            PlatformPaymentResultDto result = new PlatformPaymentResultDto();
            result.setPlatformType(PlatformType.Wechat);
            result.setOrderId(paymentStateChangeEvent.getOrderInfo().getOrderId());
            result.setResultCode(PlatformResultCode.Success);
            payService.onPaymentResult(result);
        }
    }
}
