package com.chen.demo.controllers;

import com.chen.demo.annotations.NotValidate;
import com.chen.demo.commons.VoUtil;
import com.chen.demo.enums.UserType;
import com.chen.demo.exceptions.BusinessException;
import com.chen.demo.models.User;
import com.chen.demo.services.UserService;
import com.chen.demo.vos.PageData;
import com.chen.demo.vos.ResponseData;
import com.chen.demo.vos.ResponseStatus;
import com.chen.demo.vos.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseData add(@RequestBody UserVo vo, HttpServletRequest request) throws Exception{
        User user = (User) VoUtil.voToModel(vo, User.class);
        user.setType(UserType.USER);
        userService.add(user);
        return ResponseData.success(new UserVo(user));
    }

    @PutMapping("/{id}")
    public ResponseData edit(@PathVariable Long id, @RequestBody UserVo vo) throws BusinessException, Exception{
        User user = userService.findByID(id);
        if(user == null){
            throw new BusinessException(ResponseStatus.USER_NOT_EXIST);
        }
        User u = (User) VoUtil.voToModel(vo, User.class);
        u.setId(id);
        userService.update(u);
        return ResponseData.success(new UserVo(userService.findByID(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id) throws BusinessException{
        User user = userService.findByID(id);
        if(user == null){
            throw new BusinessException(ResponseStatus.USER_NOT_EXIST);
        }
        userService.delete(id);
        return ResponseData.success();
    }

    @GetMapping("/{id}")
    public ResponseData info(@PathVariable @NotValidate Long id) throws BusinessException{
        User user = userService.findByID(id);
        if(user == null) {
            throw new BusinessException(ResponseStatus.USER_NOT_EXIST);
        }
        return ResponseData.success(new UserVo(user));
    }

    @GetMapping
    public ResponseData list(){
        List<User> users = userService.fetchAll();
        List<UserVo> vos = users.stream().map(u -> new UserVo(u)).collect(Collectors.toList());
        return ResponseData.success(new PageData(vos));
    }
}
