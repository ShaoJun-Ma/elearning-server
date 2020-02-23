package com.msj.elearning.service.impl;

import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.mapper.UserMapper;
import com.msj.elearning.pojo.User;
import com.msj.elearning.service.UserService;
import com.msj.elearning.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

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

    @Override
    public ServiceResult register(String username, String password) {
        //1、判断该用户是否注册
        User user = userMapper.findUserByUsername(username);
        if(user != null){
            return new ServiceResult(false,"该用户已存在!");
        }
        //2、未注册过，才可以进行注册
        password = MD5Util.getMD5(password);
        user = new User(username,password,username,new Date(),new Date());
        int result = userMapper.insertSelective(user);
        if(result <= 0){
            return new ServiceResult(false,"注册失败!");
        }else{
            return new ServiceResult(true,"注册成功,请进行登录!");
        }
    }
}
