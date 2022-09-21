package com.smartrm.smartrmtrade.trade.domain.service;

import com.smartrm.smartrmtrade.trade.domain.share.UserInfo;

/**
 * @author: yoda
 * @description:
 */
public interface TradeUserService {
    
    /**
     * 获取用户信息
     *
     * @param openId
     * @return
     */
    UserInfo getUserInfo(String openId);
    
    
}
