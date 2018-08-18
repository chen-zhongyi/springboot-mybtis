package com.chen.demo.services;

import com.chen.demo.models.Account;

public interface AccountService extends BaseService<Account>{

    boolean isRightPassword(String username, String password);
}
