package com.smartrm.smartrmdevice.device.domain.specification;

import com.smartrm.smartrmdevice.device.domain.VendingMachine;
import com.smartrm.smartrmdevice.device.domain.event.DeviceFailureEvent;

/**
 * @author: yoda
 * @description:
 */
public class RollBackInventorySpec {
    
    public boolean isSatisfiedBy(DeviceFailureEvent event, VendingMachine machine) {
        //TODO: 判断是否需要回滚库存
        return true;
    }
    
}
