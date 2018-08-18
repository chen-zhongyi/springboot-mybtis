package com.chen.demo.services.imp;

import com.chen.demo.commons.EncryptUtil;
import com.chen.demo.mappers.AccountMapper;
import com.chen.demo.models.Account;
import com.chen.demo.services.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp extends BaseServiceImp<Account> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private void setBaseMapper(){
        this.setBaseMapper(accountMapper);
    }

    @Override
    public boolean isRightPassword(String username, String password){
        Account account = accountMapper.findByUsername(username);
        if(account == null) return false;
        if(StringUtils.equals(EncryptUtil.md5(Account.getPasswordAndOtherStr(account.getOtherStr(), password)), account.getPassword()))    return true;
        return false;
    }
}
