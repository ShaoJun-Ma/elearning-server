package com.msj.elearning.dto;

import com.msj.elearning.pojo.Course;
import com.msj.elearning.pojo.CourseDetail;
import com.msj.elearning.pojo.CourseType;
import com.msj.elearning.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailInfoDTO {

    private CourseTypeOne2OneDTO courseType;
    private User author;
    private Course course;
    private CourseDetail courseDetail;
    private List<CourseChapterDTO>  courseChapterList;
}
