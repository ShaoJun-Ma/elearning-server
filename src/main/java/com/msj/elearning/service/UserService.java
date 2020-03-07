package com.msj.elearning.service;

import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface UserService {
    ServiceResult login(String username, String password, HttpSession session);
    ServiceResult register(String username,String password);
    ServiceResult logout(Integer uId, HttpSession session);
    ServiceResult changeUserInfo(User user, HttpSession session);
    ServiceResult uploadAvatar(MultipartFile picFile,HttpSession session);
}
