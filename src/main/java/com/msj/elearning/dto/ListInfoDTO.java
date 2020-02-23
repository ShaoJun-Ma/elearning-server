package com.msj.elearning.dto;

import com.msj.elearning.pojo.CourseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInfoDTO {
    private List<CourseType> parentCourseTypeList;
    private List<CourseType> childCourseTypeList;
    private List<CourseDTO> courseList;
}
