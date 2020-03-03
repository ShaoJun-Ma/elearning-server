package com.msj.elearning.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

/**
 * 用户实体类
 */
@Data   //提供类的set,get等方法
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    //角色
    private String role;
    //职位
    private String job;
    //性别
    private String sex;
    //城市
    private String city;
    //个性签名
    private String signature;
    //学习时长
    private int learnTime;
    //关注人数
    private int followCounts;
    //粉丝人数
    private int fanCounts;
    //个人头像
    private String faceImg;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public User(String username, String password, String nickname, Date createTime, Date updateTime) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}

