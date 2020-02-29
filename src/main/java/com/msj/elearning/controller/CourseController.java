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

    @RequestMapping("/getHomeInfo")
    public ApiResponse getHomeInfo(){
        ServiceResult result = courseService.getHomeInfo();
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    @RequestMapping("/getListInfo")
    public ApiResponse getListInfo(Integer isFree, @RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "2") Integer pageSize){
        ServiceResult result = courseService.getListInfo(isFree,currentPage,pageSize);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

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

    @RequestMapping("/getParentType")
    public ApiResponse getParentType(Integer ctId){
        ServiceResult result = courseService.getParentType(ctId);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    @RequestMapping("/getDetailInfo")
    public ApiResponse getDetailInfo(Integer cId,
                                     @RequestParam(required = false,defaultValue = "0")Integer uId){
        ServiceResult result = courseService.getDetailInfo(cId,uId);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    @RequestMapping("/getEvaluation")
    public ApiResponse getEvaluation(Integer cId,@RequestParam(defaultValue = "1") Integer currentPage,
                                     @RequestParam(defaultValue = "2") Integer pageSize){
        ServiceResult result = courseService.getEvaluation(cId,currentPage,pageSize);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    @RequestMapping("/getChapter")
    public ApiResponse getChapter(Integer cdId){
        ServiceResult result = courseService.getChapter(cdId);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

}
