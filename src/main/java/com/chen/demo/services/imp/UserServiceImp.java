package com.chen.demo.services.imp;

import com.chen.demo.commons.EncryptUtil;
import com.chen.demo.enums.UserType;
import com.chen.demo.mappers.UserMapper;
import com.chen.demo.models.User;
import com.chen.demo.services.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp extends BaseServiceImp<User> implements UserService, InitializingBean{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public void setBaseMapper(){
        super.setBaseMapper(userMapper);
    }

    @Override
    public User findByUsername(String username){
        return userMapper.findByUsername(username);
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        initAdmin();
        updateColumn();
    }

    private void initAdmin() throws Exception{
        if(fetchAll().size() > 0)   return ;
        User user = new User();
        user.setUsername("admin");
        user.setType(UserType.ADMIN);
        user.setPhone("18758324519");
        user.setOtherStr(EncryptUtil.gen(12));
        user.setPassword(EncryptUtil.md5(EncryptUtil.gen(6) + user.getOtherStr()));
        add(user);
    }

    private void updateColumn(){
        List<User> users = fetchAll();
        users.stream().filter(u -> u.getType() == null).forEach(u -> {
            u.setType(UserType.USER);
            try {
                update(u);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
