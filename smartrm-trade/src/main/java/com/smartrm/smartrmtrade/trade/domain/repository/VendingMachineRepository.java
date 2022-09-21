package com.smartrm.smartrmtrade.trade.domain.repository;

import com.smartrm.smartrmtrade.trade.domain.CabinetVendingMachine;
import com.smartrm.smartrmtrade.trade.domain.SlotVendingMachine;

/**
 * @author: yoda
 * @description:
 */
public interface VendingMachineRepository {
    
    SlotVendingMachine getSlotVendingMachineById(long id);
    
    CabinetVendingMachine getCabinetVendingMachineById(long id);
    
    void updateSlotVendingMachine(SlotVendingMachine machine);
    
    void updateCabinetVendingMachine(CabinetVendingMachine machine);
    
}
