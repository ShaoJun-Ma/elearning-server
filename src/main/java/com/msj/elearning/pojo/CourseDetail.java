package com.msj.elearning.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程详情实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetail {
    private Integer id;
    //课程id
    private Integer cId;
    //作者id
    private Integer uId;
    //课程简介
    private String introduction;
    //课程时长
    private String time;
    //课程须知
    private String tip;
    //课程学习内容
    private String learn;
}
