package com.chen.demo.controllers;

import com.chen.demo.exceptions.BusinessException;
import com.chen.demo.requests.AccountLoginRequest;
import com.chen.demo.services.AccountService;
import com.chen.demo.vos.ResponseData;
import com.chen.demo.vos.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController extends BaseController{

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseData login(@RequestBody AccountLoginRequest request) throws Exception{
        boolean isRight = accountService.isRightPassword(request.getUsername(), request.getPassword());
        if(! isRight){
            throw new BusinessException(ResponseStatus.LOGIN_USERNAME_OR_PASSWORD_ERROR);
        }
        return ResponseData.success();
    }
}
