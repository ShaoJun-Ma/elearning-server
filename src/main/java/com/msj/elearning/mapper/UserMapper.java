package com.msj.elearning.mapper;

import com.msj.elearning.common.CommonMapper;
import com.msj.elearning.pojo.User;

public interface UserMapper extends CommonMapper<User> {
    User findUserByUsername(String username);
    User findUserById(Integer id);
}
