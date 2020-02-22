package com.msj.elearning.service;

import com.msj.elearning.common.ServiceResult;

import javax.servlet.http.HttpSession;

public interface UserService {
    ServiceResult login(String username, String password, HttpSession session);
}
