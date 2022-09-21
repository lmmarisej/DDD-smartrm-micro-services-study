package com.smartrm.smartrmtrade.trade.adapter.remote;

import com.smartrm.infracore.api.CommonError;
import com.smartrm.infracore.api.CommonResponse;
import com.smartrm.infracore.exception.DomainException;
import com.smartrm.smartrmtrade.trade.application.dto.ChargeCommandDto;
import com.smartrm.smartrmtrade.trade.application.dto.PaymentQrCodeDto;
import com.smartrm.smartrmtrade.trade.application.dto.StartQrCodePayCommandDto;
import com.smartrm.smartrmtrade.trade.domain.Order;
import com.smartrm.smartrmtrade.trade.domain.PaymentQrCode;
import com.smartrm.smartrmtrade.trade.domain.service.TradePayService;
import com.smartrm.smartrmtrade.trade.domain.share.OrderInfo;
import com.smartrm.smartrmtrade.trade.domain.share.PlatformType;
import com.smartrm.smartrmtrade.trade.domain.share.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yoda
 * @description:
 */
@Service
public class TradePayServiceImpl implements TradePayService {
    
    private static Logger LOGGER = LoggerFactory.getLogger(TradePayServiceImpl.class);
    
    @Autowired
    RestTemplate payRestTemplate;
    
    @Override
    public PaymentQrCode startQrCodePayForOrder(PlatformType platformType, Order order) {
        
        StartQrCodePayCommandDto cmd = new StartQrCodePayCommandDto();
        cmd.setOrderId(order.getOrderId());
        cmd.setMachineId(order.getMachineId());
        cmd.setPlatformType(platformType);
        cmd.setTotalAmount(order.totalAmount());
        ParameterizedTypeReference<CommonResponse<PaymentQrCodeDto>> reference = new ParameterizedTypeReference<CommonResponse<PaymentQrCodeDto>>() {
        };
        ResponseEntity<CommonResponse<PaymentQrCodeDto>> response = payRestTemplate
                .exchange("/startQrcodePay",
                        HttpMethod.POST,
                        new HttpEntity(cmd),
                        reference
                );
        if (!response.getStatusCode().is2xxSuccessful()) {
            LOGGER.error("fail to call remote pay service: " + response.getBody().getMsg());
            throw new DomainException(CommonError.UnExpected)
                    .withMsg(response.getStatusCode().toString());
        } else if (response.getBody().getCode() != CommonError.NoError.getCode()) {
            throw new DomainException(CommonError.UnExpected).withMsg(response.getBody().getMsg());
        }
        return new PaymentQrCode(response.getBody().getData().getPaymentId(),
                response.getBody().getData().getCodeUrl());
    }
    
    @Override
    public void chargeForOrder(OrderInfo order, UserInfo userInfo) {
        ChargeCommandDto cmd = new ChargeCommandDto();
        cmd.setTotalAmount(order.getTotalAmount());
        cmd.setAccountId(userInfo.getAccountId());
        cmd.setOrderId(order.getOrderId());
        cmd.setContractId(userInfo.getContractId());
        cmd.setMachineId(order.getMachineId());
        CommonResponse response = payRestTemplate
                .postForObject("/chargeForOrder", cmd, CommonResponse.class);
    }
}
