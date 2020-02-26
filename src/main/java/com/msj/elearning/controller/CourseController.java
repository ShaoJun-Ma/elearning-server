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
    public ApiResponse getCourse(Integer pid,Integer cid,String rank,Integer isFree,
                                                   @RequestParam(defaultValue = "1") Integer currentPage,
                                                   @RequestParam(defaultValue = "2") Integer pageSize){
        ServiceResult result = courseService.getCourseByPidAndCidAndRank(pid,cid,rank,isFree,currentPage,pageSize);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    @RequestMapping("/getChildType")
    public ApiResponse getChildType(Integer pid){
        ServiceResult result = courseService.getChildType(pid);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    @RequestMapping("/getParentType")
    public ApiResponse getParentType(Integer cid){
        ServiceResult result = courseService.getParentType(cid);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

}
