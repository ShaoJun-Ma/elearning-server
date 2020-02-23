package com.msj.elearning.mapper;

import com.msj.elearning.common.CommonMapper;
import com.msj.elearning.pojo.Course;

import java.util.List;

public interface CourseMapper extends CommonMapper<Course> {
    List<Course> findCourseByIsFreeAndOrderByCreateTime(Integer isFree);
    List<Course> findCourseByIsFree(Integer isFree);
}
