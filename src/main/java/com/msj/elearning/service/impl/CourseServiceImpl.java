package com.msj.elearning.service.impl;

import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.dto.CourseDTO;
import com.msj.elearning.dto.CourseTypeDTO;
import com.msj.elearning.dto.HomeInfoDTO;
import com.msj.elearning.mapper.CourseMapper;
import com.msj.elearning.mapper.CourseTypeMapper;
import com.msj.elearning.pojo.Course;
import com.msj.elearning.pojo.CourseType;
import com.msj.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("course")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;
    @Autowired
    private CourseMapper courseMapper;

    /**
     * 获取首页的数据
     * @return
     */
    @Override
    public ServiceResult getHomeInfo() {
        //1、获取课程类型
        List<CourseTypeDTO> courseTypeList = getCourseTypeList();
        //2、获取最新课程
        List<CourseDTO> newCourseList = getNewCourseList(0);
        //3、获取免费课程
        List<CourseDTO> freeCourseList = getCourseListByIsFree(1);
        //4、获取付费课程
        List<CourseDTO> payCourseList = getCourseListByIsFree(0);
        HomeInfoDTO homeInfoDTO = new HomeInfoDTO(courseTypeList, newCourseList, freeCourseList, payCourseList);
        if (homeInfoDTO == null) {
            return new ServiceResult(false, "获取首页数据失败");
        }
        return new ServiceResult(true, "获取首页数据成功", homeInfoDTO);
    }

    /**
     * 获取课程类型
     * @return
     */
    private List<CourseTypeDTO> getCourseTypeList(){
        ArrayList<CourseTypeDTO> courseTypeDTOList = new ArrayList<>();
        //1、获取父级课程类型
        List<CourseType> parentCourseTypeList = courseTypeMapper.findCourseTypeByParentId(0);
        for (CourseType p : parentCourseTypeList) {
            //2、获取子级课程类型
            List<CourseType> childCourseType = courseTypeMapper.findCourseTypeByParentId(p.getId());
            CourseTypeDTO courseTypeDTO = new CourseTypeDTO(p.getId(),p.getName(),childCourseType);
            courseTypeDTOList.add(courseTypeDTO);
        }
        if(courseTypeDTOList == null){
            return null;
        }
        return courseTypeDTOList;
    }

    /**
     * 获取最新课程
     * @return
     */
    private List<CourseDTO> getNewCourseList(Integer isFree){
        List<CourseDTO> courseDTOList = null;
        List<Course> courseList = courseMapper.findCourseByIsFreeAndOrderByCreateTime(isFree);
        if(courseList != null){
            courseDTOList = mergeCourseDTOList(courseList);
        }
        if(courseDTOList == null){
            return null;
        }
        return courseDTOList;
    }

    /**
     * 获取课程
     * @param  isFree 是否免费
     * @return
     */
    private List<CourseDTO> getCourseListByIsFree(Integer isFree){
        List<CourseDTO> courseDTOList = null;
        //1、根据是否免费获取课程
        List<Course> courseList = courseMapper.findCourseByIsFree(isFree);
        //2、整合List<courseDTO>
        if(courseList != null){
            courseDTOList = mergeCourseDTOList(courseList);
        }
        if(courseDTOList == null){
            return null;
        }
        return courseDTOList;
    }

    /**
     * 整合课程
     * @param courseList 课程列表
     * @return
     */
    private List<CourseDTO> mergeCourseDTOList(List<Course> courseList){
        List<CourseDTO> courseDTOList = new ArrayList<CourseDTO>();
        for (Course c : courseList) {
            CourseType courseType = courseTypeMapper.findCourseTypeById(c.getCtId());
            CourseDTO courseDTO = new CourseDTO(c,courseType.getName());
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }
}
