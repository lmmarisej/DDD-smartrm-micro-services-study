package com.smartrm.smartrmtrade.trade.domain.service;

import com.smartrm.smartrmtrade.trade.domain.Order;
import com.smartrm.smartrmtrade.trade.domain.PaymentQrCode;
import com.smartrm.smartrmtrade.trade.domain.share.OrderInfo;
import com.smartrm.smartrmtrade.trade.domain.share.PlatformType;
import com.smartrm.smartrmtrade.trade.domain.share.UserInfo;

/**
 * @author: yoda
 * @description:
 */
public interface TradePayService {
    
    /**
     * 开始扫码支付，获取支付二维码
     *
     * @param platformType 平台类型，目前固定为微信
     * @param order        订单
     * @return 支付二维码
     */
    PaymentQrCode startQrCodePayForOrder(PlatformType platformType, Order order);
    
    /**
     * 免密扣款
     *
     * @param order    订单信息
     * @param userInfo 用户信息
     */
    void chargeForOrder(OrderInfo order, UserInfo userInfo);
    
}
