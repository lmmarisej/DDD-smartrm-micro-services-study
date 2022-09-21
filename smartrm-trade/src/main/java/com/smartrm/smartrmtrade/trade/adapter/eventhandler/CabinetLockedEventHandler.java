package com.smartrm.smartrmtrade.trade.adapter.eventhandler;

import com.smartrm.infracore.event.DomainEventHandler;
import com.smartrm.smartrmtrade.trade.application.AppTradeService;
import com.smartrm.smartrmtrade.trade.domain.share.event.CabinetVendingMachineLockedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description:
 */
@Component
public class CabinetLockedEventHandler extends
        DomainEventHandler<CabinetVendingMachineLockedEvent> {
    
    @Autowired
    AppTradeService tradeService;
    
    @Override
    public void onApplicationEvent(CabinetVendingMachineLockedEvent event) {
        tradeService.onCabinetLocked(event);
    }
}
