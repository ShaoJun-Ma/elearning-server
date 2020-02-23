package com.msj.elearning.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 课程实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private Integer id;
    //课程类别id
    private Integer ctId;
    //课程名
    private String name;
    //课程封面路径
    private String coverImg;
    //课程难易程度
    private String rank;
    //课程是否免费
    private Integer isFree;
    //课程价格
    private double price;
    //课程学习人数
    private int learnCounts;
    //课程描述
    private String description;
    //课程的状态
    private String status;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
