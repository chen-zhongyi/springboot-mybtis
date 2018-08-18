package com.chen.demo.services;

import com.chen.demo.models.User;

public interface UserService extends BaseService<User>{

    User findByUsername(String username);
}
