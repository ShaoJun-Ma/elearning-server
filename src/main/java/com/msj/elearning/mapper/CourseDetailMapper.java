package com.msj.elearning.mapper;

import com.msj.elearning.common.CommonMapper;
import com.msj.elearning.pojo.CourseDetail;

public interface CourseDetailMapper extends CommonMapper<CourseDetail> {
    CourseDetail findCourseDetailByCid(Integer cId);
}
