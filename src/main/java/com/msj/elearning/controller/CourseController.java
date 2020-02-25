package com.msj.elearning.controller;

import com.msj.elearning.common.ApiResponse;
import com.msj.elearning.common.ServiceResult;
import com.msj.elearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ApiResponse getListInfo(Integer isFree){
        ServiceResult result = courseService.getListInfo(isFree);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

    @RequestMapping("/getCourseByPidAndCidAndRank")
    public ApiResponse getCourseByPidAndCidAndRank(Integer pid,Integer cid,String rank,Integer isFree){
        ServiceResult result = courseService.getCourseByPidAndCidAndRank(pid,cid,rank,isFree);
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

    @RequestMapping("/getParentAndChildType")
    public ApiResponse getParentAndChildType(Integer cid){
        ServiceResult result = courseService.getParentAndChildType(cid);
        if(result.isSuccess()){
            return new ApiResponse(200,result.getMessage(),result.getResult());
        }
        return new ApiResponse(0,result.getMessage());
    }

}
