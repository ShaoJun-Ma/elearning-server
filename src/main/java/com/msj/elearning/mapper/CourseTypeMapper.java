package com.msj.elearning.mapper;

import com.msj.elearning.common.CommonMapper;
import com.msj.elearning.pojo.CourseType;

import java.util.List;

public interface CourseTypeMapper extends CommonMapper<CourseType> {
    List<CourseType> findCourseTypeByParentId(Integer parentId);
    CourseType findCourseTypeById(Integer id);
}
