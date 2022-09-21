package com.smartrm.smartrmpayment.payment.application.remote;

import com.smartrm.smartrmpayment.payment.domain.PaymentStateChangeEvent;

/**
 * @author: yoda
 * @description:
 */
public interface TradeService {
    
    void onPaymentStateChange(PaymentStateChangeEvent event);
    
}
