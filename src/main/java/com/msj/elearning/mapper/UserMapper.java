package com.msj.elearning.mapper;

import com.msj.elearning.common.CommonMapper;
import com.msj.elearning.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends CommonMapper<User> {
    User findUserByUsername(String username);
    User findUserById(Integer id);
    Integer updateFaceImgById(@Param("faceImg")String faceImg,@Param("id")Integer id);
}
