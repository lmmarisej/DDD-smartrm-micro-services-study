package com.smartrm.smartrmuser.user.domain.repository;

import com.smartrm.smartrmuser.user.domain.UserAccount;

/**
 * @author: yoda
 * @description:
 */
public interface UserAccountRepository {
    
    UserAccount getByOpenId(String openId);
    
    void add(UserAccount account);
    
    void update(UserAccount account);
    
}
