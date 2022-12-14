package com.smartrm.smartrmtrade.trade.application.executor;

import com.smartrm.infracore.scheduler.RetryExecutorBase;
import com.smartrm.smartrmtrade.trade.domain.Order;
import com.smartrm.smartrmtrade.trade.domain.SlotVendingMachine;
import com.smartrm.smartrmtrade.trade.domain.repository.OrderRepository;
import com.smartrm.smartrmtrade.trade.domain.repository.VendingMachineRepository;
import com.smartrm.smartrmtrade.trade.domain.share.event.DeviceFailureEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author: yoda
 * @description:
 */
@Component
public class DeviceFailureExecutor extends RetryExecutorBase {
    
    private static Logger LOGGER = LoggerFactory.getLogger(DeviceFailureExecutor.class);
    
    @Autowired
    VendingMachineRepository machineRepository;
    
    @Autowired
    OrderRepository orderRepository;
    
    @Override
    @Transactional
    public void run(Map<String, Object> params) {
        DeviceFailureEvent event = (DeviceFailureEvent) params.get("event");
        SlotVendingMachine machine = machineRepository.getSlotVendingMachineById(
                event.getMachineId());
        LOGGER.info("running: {}", event.toString());
        if (machine.getCurOrder() != null
                && machine.getCurOrder().getOrderId() == event.getOrderId()) {
            machine.cancelOrder();
        } else {
            Order order = orderRepository.getOrderById(event.getOrderId());
            order.cancel();
            orderRepository.updateOrder(order);
        }
    }
}
