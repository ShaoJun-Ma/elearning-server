package com.msj.elearning.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseChapter {
    private Integer id;
    //父章节id
    private Integer parentId;
    //课程详情id
    private Integer cdId;
    //章节名
    private String title;
    //章节介绍
    private String introduction;
    //章节状态
    private int is_finished;
}
