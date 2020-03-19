package com.msj.elearning.service;

import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface UserService {
    ServiceResult login(String username, String password, HttpSession session);
    ServiceResult register(String username,String password);
    ServiceResult logout(Integer uId, HttpSession session);
    /**
     * 修改用户信息
     * @param user 用户信息
     * @param session
     * @return
     */
    ServiceResult changeUserInfo(User user, HttpSession session);
    /**
     * 上传头像
     * @param picFile 图片文件
     * @param session
     * @return
     */
    ServiceResult uploadAvatar(MultipartFile picFile,HttpSession session);

    ServiceResult changePassword(Integer id,String oldPass,String newPass,HttpSession session);
}
