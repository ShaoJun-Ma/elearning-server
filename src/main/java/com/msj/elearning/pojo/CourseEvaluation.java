package com.msj.elearning.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEvaluation {
    private Integer id;
    //课程id
    private Integer cId;
    //用户id
    private Integer uId;
    //评价内容
    private String evaluation;
    //创建时间
    private Date createTime;
}
