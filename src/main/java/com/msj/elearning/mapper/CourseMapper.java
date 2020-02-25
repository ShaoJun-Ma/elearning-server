package com.msj.elearning.mapper;

import com.msj.elearning.common.CommonMapper;
import com.msj.elearning.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper extends CommonMapper<Course> {
    List<Course> findCourseByIsFreeAndOrderByCreateTime(Integer isFree);
    List<Course> findCourseByIsFree(Integer isFree);
    List<Course> findCourseByRankAndIsFree(@Param("rank") String rank, @Param("isFree") Integer isFree);
    List<Course> findCourseByCidAndRankAndIsFree(@Param("cid") Integer cid, @Param("rank") String rank, @Param("isFree") Integer isFree);
    List<Course> findCourseByPidAndRankAndIsFree(@Param("pid") Integer pid, @Param("rank") String rank, @Param("isFree") Integer isFree);
}
