package com.msj.elearning.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程类型 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseType {

    private Integer id;
    //类别名
    private String name;
    //父类别id
    private Integer parentId;
}
