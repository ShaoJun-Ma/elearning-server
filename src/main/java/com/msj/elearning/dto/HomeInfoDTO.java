package com.msj.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeInfoDTO {
    private List<CourseTypeDTO> courseTypeList;
    private List<CourseDTO> newCourseList;
    private List<CourseDTO> freeCourseList;
    private List<CourseDTO> payCourseList;
}
