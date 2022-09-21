package com.smartrm.smartrmpayment.payment.adapter.remote;

import com.smartrm.infracore.api.CommonResponse;
import com.smartrm.smartrmpayment.payment.application.dto.PaymentStateChangeDto;
import com.smartrm.smartrmpayment.payment.application.remote.TradeService;
import com.smartrm.smartrmpayment.payment.domain.PaymentStateChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yoda
 * @description:
 */
@Service
public class TradeServiceImpl implements TradeService {
    
    @Autowired
    private RestTemplate tradeRestTemplate;
    
    @Override
    public void onPaymentStateChange(PaymentStateChangeEvent event) {
        PaymentStateChangeDto paymentStateChangeDto = new PaymentStateChangeDto();
        paymentStateChangeDto.setPaymentId(event.getPaymentId());
        paymentStateChangeDto.setCurState(event.getCurState());
        paymentStateChangeDto.setPreState(event.getPreState());
        paymentStateChangeDto.setOrderInfo(event.getOrderInfo());
        paymentStateChangeDto.setTime(event.getTimestamp());
        CommonResponse result = tradeRestTemplate.postForObject("/paymentStateChange", paymentStateChangeDto, CommonResponse.class);
    }
}
