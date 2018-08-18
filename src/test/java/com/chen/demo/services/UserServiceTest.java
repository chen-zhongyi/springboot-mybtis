package com.chen.demo.services;

import com.chen.demo.commons.IdWorker;
import com.chen.demo.enums.UserType;
import com.chen.demo.models.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void list(){
        List<User> users = userService.fetchAll();
        //Assert.assertThat(users.size(), is(3));
        Assert.assertThat(users.size(), lessThan(30));
        Assert.assertThat(users.size(), greaterThan(0));
    }

    @Test
    @Transactional
    public void insert() throws Exception{
        User user = new User();
        long id = IdWorker.getFlowIdWorkerInstance().nextId();
        user.setId(id);
        user.setType(UserType.USER);
        user.setOtherStr(User.genOtherStr());
        user.setPassword(User.password("123456", user.getOtherStr()));
        user.setPhone("18758324519");
        user.setEmail("765485378@qq.com");
        userService.add(user);
        User temp = userService.findByID(user.getId());
        //Assert.assertThat(temp, is(equalTo(user)));
        Assert.assertThat(temp.getId(), is(equalTo(user.getId())));
        Assert.assertThat(temp.getType(), is(equalTo(user.getType())));
        Assert.assertThat(temp.getOtherStr(), is(equalTo(user.getOtherStr())));
        Assert.assertThat(temp.getPassword(), is(equalTo(user.getPassword())));
        Assert.assertThat(temp.getPhone(), is(equalTo(user.getPhone())));
        Assert.assertThat(temp.getEmail(), is(equalTo(user.getEmail())));
        Assert.assertThat(temp.getCreateTime(), is(equalTo(user.getCreateTime())));
        Assert.assertThat(temp.getUpdateTime(), is(equalTo(user.getUpdateTime())));
        Assert.assertThat(temp.getDeleted(), is(equalTo(false)));
        Assert.assertThat(temp.getEnable(), is(equalTo(true)));
    }
}
