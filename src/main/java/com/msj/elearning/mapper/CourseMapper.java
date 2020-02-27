package com.msj.elearning.mapper;

import com.msj.elearning.common.CommonMapper;
import com.msj.elearning.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper extends CommonMapper<Course> {
    List<Course> findCourseByIsFreeAndOrderByCreateTime(Integer isFree);
    List<Course> findCourseByIsFree(Integer isFree);
    List<Course> findCourseByRankAndIsFree(@Param("rank") String rank, @Param("isFree") Integer isFree);
    List<Course> findCourseBycIdAndRankAndIsFree(@Param("cId") Integer cId, @Param("rank") String rank, @Param("isFree") Integer isFree);
    List<Course> findCourseBypIdAndRankAndIsFree(@Param("pId") Integer pId, @Param("rank") String rank, @Param("isFree") Integer isFree);
    Course findCourseById(Integer id);
}
