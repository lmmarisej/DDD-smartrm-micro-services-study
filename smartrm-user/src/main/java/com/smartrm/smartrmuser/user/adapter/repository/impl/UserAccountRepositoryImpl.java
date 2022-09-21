package com.smartrm.smartrmuser.user.adapter.repository.impl;

import com.smartrm.smartrmuser.user.domain.UserAccount;
import com.smartrm.smartrmuser.user.domain.repository.UserAccountRepository;
import com.smartrm.smartrmuser.user.infrastructure.dataobject.UserAccountDo;
import com.smartrm.smartrmuser.user.infrastructure.mapper.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author: yoda
 * @description:
 */
@Repository
public class UserAccountRepositoryImpl implements UserAccountRepository {
    
    @Autowired
    private UserAccountMapper userAccountMapper;
    
    @Override
    public UserAccount getByOpenId(String openId) {
        UserAccountDo accountDo = userAccountMapper.selectByOpenId(openId);
        if (accountDo == null) {
            return null;
        }
        UserAccount account = UserAccount.Builder().accountId(accountDo.getAccountId())
                .wxOpenId(accountDo.getWxOpenId()).wxUnionId(accountDo.getWxUnionId())
                .contractId(accountDo.getContractId()).build();
        return account;
    }
    
    @Override
    public void add(UserAccount account) {
        UserAccountDo accountDo = new UserAccountDo();
        accountDo.setWxOpenId(account.getWxOpenId());
        account.setWxUnionId(account.getWxUnionId());
        userAccountMapper.insert(accountDo);
        account.setAccountId(accountDo.getAccountId());
    }
    
    @Override
    public void update(UserAccount account) {
        UserAccountDo accountDo = new UserAccountDo();
        accountDo.setAccountId(account.getAccountId());
        accountDo.setWxOpenId(account.getWxOpenId());
        accountDo.setWxUnionId(account.getWxUnionId());
        accountDo.setContractId(account.getContractId());
        userAccountMapper.update(accountDo);
    }
}
