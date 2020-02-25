package com.msj.elearning.service.impl;

import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.dto.CourseDTO;
import com.msj.elearning.dto.CourseTypeDTO;
import com.msj.elearning.dto.HomeInfoDTO;
import com.msj.elearning.dto.ListInfoDTO;
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
     * 获取列表的数据
     *
     * @param isFree
     * @return
     */
    @Override
    public ServiceResult getListInfo(Integer isFree) {

        //1、获取课程类型
        ArrayList<CourseTypeDTO> courseTypeDTOList = new ArrayList<>();
        List<CourseType> parentCourseTypeList = courseTypeMapper.findCourseTypeByParentId(0);
        for (CourseType p : parentCourseTypeList) {
            List<CourseType> childCourseType = courseTypeMapper.findCourseTypeByParentId(p.getId());
            CourseTypeDTO courseTypeDTO = new CourseTypeDTO(p.getId(), p.getName(), childCourseType);
            if(courseTypeDTO != null){
                courseTypeDTOList.add(courseTypeDTO);
            }
        }
        //2、获取课程
        List<CourseDTO> courseDTOList = null;
        List<Course> courseList = courseMapper.findCourseByIsFree(isFree);
        if(courseList != null){
            courseDTOList = mergeCourseDTOList(courseList);
        }

        ListInfoDTO listInfoDTO = new ListInfoDTO(courseTypeDTOList,courseDTOList);
        if(listInfoDTO == null){
            return new ServiceResult(false,"获取列表页数据失败");
        }
        return new ServiceResult(true,"获取列表页数据成功",listInfoDTO);
    }

    /**
     * 获取课程
     * @param pid    课程父类型 （方向）
     * @param cid    课程子类型 （分类）
     * @param rank   课程难度
     * @param isFree 是否免费
     */
    @Override
    public ServiceResult getCourseByPidAndCidAndRank(Integer pid, Integer cid, String rank, Integer isFree) {
        List<Course> courseList = null;
        if(pid == 0){
            //1、方向：全部
            if(cid == 0){
                //1.1 分类：全部
                courseList = courseMapper.findCourseByRankAndIsFree(rank, isFree);
            }else{
                //1.2 分类：具体
                courseList = courseMapper.findCourseByCidAndRankAndIsFree(cid,rank,isFree);
            }
        }else{
            //2、方向：具体
            if(cid == 0){
                //2.1 分类：全部
                courseList = courseMapper.findCourseByPidAndRankAndIsFree(pid,rank,isFree);
            }else{
                //2.2 分类：具体
                //判断 子类型所属的父类型是否跟pid相等
                if(pid != ((courseTypeMapper.findCourseTypeById(cid)).getParentId())){
                    return new ServiceResult(false,"非法操作");
                }
                courseList = courseMapper.findCourseByCidAndRankAndIsFree(cid,rank,isFree);
            }
        }
        List<CourseDTO> courseDTOList = null;
        if(courseList != null){
            courseDTOList = mergeCourseDTOList(courseList);
        }
        if(courseDTOList == null){
            return new ServiceResult(false,"点击获取课程失败");
        }
        return new ServiceResult(true,"点击获取课程成功",courseDTOList);
    }

    /**
     * 获取子类型
     *
     * @param pid 课程父类型
     * @return
     */
    @Override
    public ServiceResult getChildType(Integer pid) {
        List<CourseType> courseTypeList = null;
        if(pid == 0){
            //1、pid为0，获取全部子类型
            courseTypeList = courseTypeMapper.findCourseTypeByParentIdNot(0);
        }else{
            //1、pid不为0，根据pid查子类型
            courseTypeList = courseTypeMapper.findCourseTypeByParentId(pid);
        }

        if(courseTypeList == null){
            return new ServiceResult(false,"获取课程类型失败");
        }
        return new ServiceResult(true,"获取课程类型成功",courseTypeList);
    }

    /**
     * 获取父类型和子类型
     *
     * @param cid 课程子类型id
     * @return
     */
    @Override
    public ServiceResult getParentAndChildType(Integer cid) {
        if(cid != 0){
            //1、cid不为0，根据cid获取父类型和子类型
            CourseType childType = courseTypeMapper.findCourseTypeById(cid);
            CourseType parentType = courseTypeMapper.findCourseTypeById(childType.getParentId());
            List<CourseType> childTypeList = courseTypeMapper.findCourseTypeByParentId(childType.getParentId());
            CourseTypeDTO courseTypeList = new CourseTypeDTO(parentType.getId(), parentType.getName(), childTypeList);
            if(childTypeList == null){
                return new ServiceResult(false,"获取课程类型失败");
            }
            return new ServiceResult(true,"获取课程类型成功",courseTypeList);
        }else{
            return null;
        }
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
