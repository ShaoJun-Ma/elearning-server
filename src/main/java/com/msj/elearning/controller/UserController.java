package com.msj.elearning.controller;

import com.msj.elearning.common.ApiResponse;
import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
