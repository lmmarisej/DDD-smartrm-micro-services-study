package com.smartrm.smartrmpayment.payment.domain.repository;

import com.smartrm.smartrmpayment.payment.domain.Payment;

/**
 * @author: yoda
 * @description:
 */
public interface PaymentRepository {
    
    Payment getByOrderId(Long orderId);
    
    void add(Payment payment);
    
    void update(Payment payment);
    
}
