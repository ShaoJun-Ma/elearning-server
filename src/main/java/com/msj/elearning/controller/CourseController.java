package com.msj.elearning.controller;

import com.msj.elearning.common.ApiResponse;
import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/course")
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 获取首页的数据
     * @return
     */
    @RequestMapping("/getHomeInfo")
    public ApiResponse getHomeInfo(){
        ServiceResult result = courseService.getHomeInfo();
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    /**
     * 获取列表的数据
     * @param isFree
     * @return
     */
    @RequestMapping("/getListInfo")
    public ApiResponse getListInfo(Integer isFree, @RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "2") Integer pageSize){
        ServiceResult result = courseService.getListInfo(isFree,currentPage,pageSize);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    /**
     * 获取课程
     * @param ptId 课程父类型
     * @param ctId 课程子类型
     * @param rank 课程难度
     * @param isFree 是否免费
     */
    @RequestMapping("/getCourse")
    public ApiResponse getCourse(Integer ptId,Integer ctId,String rank,Integer isFree,
                                                   @RequestParam(defaultValue = "1") Integer currentPage,
                                                   @RequestParam(defaultValue = "2") Integer pageSize){
        ServiceResult result = courseService.getCourseByPtIdAndCtIdAndRank(ptId,ctId,rank,isFree,currentPage,pageSize);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    /**
     * 获取父类型
     * @param ctId 课程子类型id
     * @return
     */
    @RequestMapping("/getParentType")
    public ApiResponse getParentType(Integer ctId){
        ServiceResult result = courseService.getParentType(ctId);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    /**
     * 获取课程详情
     * @param cId 课程id
     * @param uId 用户id
     * @return
     */
    @RequestMapping("/getDetailInfo")
    public ApiResponse getDetailInfo(Integer cId,
                                     @RequestParam(required = false,defaultValue = "0")Integer uId){
        ServiceResult result = courseService.getDetailInfo(cId,uId);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    /**
     * 获取课程评价
     * @param cId 课程id
     * @param currentPage 当前页码
     * @param pageSize 每页的条数
     * @return
     */
    @RequestMapping("/getEvaluation")
    public ApiResponse getEvaluation(Integer cId,@RequestParam(defaultValue = "1") Integer currentPage,
                                     @RequestParam(defaultValue = "2") Integer pageSize){
        ServiceResult result = courseService.getEvaluation(cId,currentPage,pageSize);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    /**
     * 获取章节
     * @param cdId 课程详情id
     * @return
     */
    @RequestMapping("/getChapter")
    public ApiResponse getChapter(Integer cdId){
        ServiceResult result = courseService.getChapter(cdId);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

}
