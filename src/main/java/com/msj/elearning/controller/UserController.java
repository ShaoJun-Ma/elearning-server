package com.msj.elearning.controller;

import com.msj.elearning.common.ApiResponse;
import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.pojo.User;
import com.msj.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ApiResponse login(String username, String password, HttpSession session){
        ServiceResult result = userService.login(username, password,session);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    @RequestMapping("/register")
    public ApiResponse register(String username, String password){
        ServiceResult result = userService.register(username, password);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage());
        }
        return new ApiResponse(0,result.getMessage());
    }

    @RequestMapping("/logout")
    public ApiResponse login(Integer uId, HttpSession session){
        ServiceResult result = userService.logout(uId,session);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage());
        }
        return new ApiResponse(0,result.getMessage());
    }

    @RequestMapping("/changeUserInfo")
    public ApiResponse changeUserInfo(User user, HttpSession session){
        ServiceResult result = userService.changeUserInfo(user,session);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }
}
