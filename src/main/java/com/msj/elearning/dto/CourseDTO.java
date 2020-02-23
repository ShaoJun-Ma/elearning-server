package com.msj.elearning.dto;

import com.msj.elearning.pojo.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Course course;
    private String courseTypeName;
}
