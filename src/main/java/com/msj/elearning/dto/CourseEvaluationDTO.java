package com.msj.elearning.dto;

import com.msj.elearning.pojo.CourseEvaluation;
import com.msj.elearning.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEvaluationDTO {
    private int id;
    //用户名
    private String username;
    //头像
    private String faceImg;
    //评价
    private String evaluation;
    //时间差
    private String timeDiff;
}
