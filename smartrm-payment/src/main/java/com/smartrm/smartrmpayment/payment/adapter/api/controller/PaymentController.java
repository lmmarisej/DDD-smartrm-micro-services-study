package com.smartrm.smartrmpayment.payment.adapter.api.controller;

import com.smartrm.infracore.api.CommonResponse;
import com.smartrm.smartrmpayment.payment.application.dto.ChargeCommandDto;
import com.smartrm.smartrmpayment.payment.application.dto.PaymentQrCodeDto;
import com.smartrm.smartrmpayment.payment.application.dto.StartQrCodePayCommandDto;
import com.smartrm.smartrmpayment.payment.application.service.PayService;
import com.smartrm.smartrmpayment.payment.domain.AccountInfo;
import com.smartrm.smartrmpayment.payment.domain.OrderInfo;
import com.smartrm.smartrmpayment.payment.domain.share.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yoda
 * @description:
 */
@RestController
@RequestMapping("/pay")
public class PaymentController {
    
    @Autowired
    PayService payService;
    
    @RequestMapping(value = "/startQrcodePay", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<PaymentQrCodeDto> startQrCodePayForOrder(
            @RequestBody StartQrCodePayCommandDto request) {
        OrderInfo orderInfo = OrderInfo.Builder().orderId(request.getOrderId())
                .machineId(request.getMachineId())
                .type(OrderType.SlotQrScanePaid)
                .totalAmount(request.getTotalAmount()).build();
        return CommonResponse.success(
                payService.startQrCodePayForOrder(request.getPlatformType(), orderInfo));
    }
    
    @RequestMapping(value = "/chargeForOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse chargeForOrder(@RequestBody ChargeCommandDto request) {
        OrderInfo orderInfo = OrderInfo.Builder().orderId(request.getOrderId())
                .machineId(request.getMachineId())
                .type(OrderType.CabinetAutoDeduction)
                .totalAmount(request.getTotalAmount()).build();
        AccountInfo accountInfo = new AccountInfo(request.getAccountId(), request.getContractId());
        payService.chargeForOrder(orderInfo, accountInfo);
        return CommonResponse.success();
    }
    
}
