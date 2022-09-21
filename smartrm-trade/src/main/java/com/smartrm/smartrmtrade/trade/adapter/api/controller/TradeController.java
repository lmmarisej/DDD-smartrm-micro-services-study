package com.smartrm.smartrmtrade.trade.adapter.api.controller;

import com.smartrm.infracore.api.CommonResponse;
import com.smartrm.smartrmtrade.trade.application.AppTradeService;
import com.smartrm.smartrmtrade.trade.application.dto.CabinetLockedNotificationDto;
import com.smartrm.smartrmtrade.trade.application.dto.PaymentStateChangeDto;
import com.smartrm.smartrmtrade.trade.application.dto.SelectCommodityCmdDto;
import com.smartrm.smartrmtrade.trade.application.dto.VendingMachineCommodityListDto;
import com.smartrm.smartrmtrade.trade.domain.PaymentQrCode;
import com.smartrm.smartrmtrade.trade.domain.share.event.CabinetVendingMachineLockedEvent;
import com.smartrm.smartrmtrade.trade.domain.share.event.PaymentStateChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yoda
 * @description:
 */
@RestController
@RequestMapping("/trade")
public class TradeController {
    
    @Autowired
    private AppTradeService tradeService;
    
    @RequestMapping(value = "/slot/listCommodity/{machineId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<VendingMachineCommodityListDto> listCommodity(
            @PathVariable Long machineId) {
        return CommonResponse.success(tradeService.queryCommodityList(machineId));
    }
    
    @RequestMapping(value = "/slot/select", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<PaymentQrCode> selectCommodity(@RequestBody SelectCommodityCmdDto cmd) {
        return CommonResponse.success(tradeService.selectCommodity(cmd));
    }
    
    @RequestMapping(value = "/cabinet/open/{machineId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse openCabinet(@PathVariable Long machineId) {
        tradeService.openCabinetVendingMachine(machineId);
        return CommonResponse.success();
    }
    
    @RequestMapping(value = "/cabinet/locked", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse cabinetLocked(@RequestBody CabinetLockedNotificationDto notification) {
        CabinetVendingMachineLockedEvent event = new CabinetVendingMachineLockedEvent();
        event.setMachineId(notification.getMachineId());
        event.setInventoryWhenLock(notification.getInventoryWhenLock());
        event.setInventorySnapshotWhenOpen(notification.getInventorySnapshotWhenOpen());
        tradeService.onCabinetLocked(event);
        return CommonResponse.success();
    }
    
    @RequestMapping(value = "/paymentStateChange", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse paymentStateChange(@RequestBody PaymentStateChangeDto dto) {
        PaymentStateChangeEvent event = new PaymentStateChangeEvent();
        event.setPaymentId(dto.getPaymentId());
        event.setCurState(dto.getCurState());
        event.setPreState(dto.getPreState());
        event.setOrderInfo(dto.getOrderInfo());
        event.setType(dto.getType());
        tradeService.onPaymentStateChange(event);
        return CommonResponse.success();
    }
}
