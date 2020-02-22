package com.msj.elearning.service.impl;

import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.mapper.UserMapper;
import com.msj.elearning.pojo.User;
import com.msj.elearning.service.UserService;
import com.msj.elearning.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("user")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServiceResult login(String username, String password, HttpSession session) {
        //1、判断该用户是否注册
        User user = userMapper.findUserByUsername(username);
        if(user == null){
            return new ServiceResult(false,"该用户不存在!");
        }
        //2、存在该用户，验证密码
        password = MD5Util.getMD5(password);

        if(!password.equals(user.getPassword())){
            return new ServiceResult(false,"用户名或密码错误");
        }
        //3、验证成功，设置缓存，保存用户信息
        user.setPassword(null);
        session.setAttribute("currentUser",user);
        return new ServiceResult(true,"登录成功",user);
    }
}
