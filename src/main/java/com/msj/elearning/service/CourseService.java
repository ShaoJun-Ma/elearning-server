package com.msj.elearning.service;

import com.msj.elearning.common.ServiceResult;

public interface CourseService {
    /**
     * 获取首页的数据
     * @return
     */
    ServiceResult getHomeInfo();

    /**
     * 获取列表的数据
     * @param isFree
     * @return
     */
    ServiceResult getListInfo(Integer isFree,Integer currentPage,Integer pageSize);

    /**
     * 获取课程
     * @param pId 课程父类型
     * @param cId 课程子类型
     * @param rank 课程难度
     * @param isFree 是否免费
     */
    ServiceResult getCourseByPidAndCidAndRank(Integer pId,Integer cId,String rank,Integer isFree,
                                              Integer currentPage,Integer pageSize);


    /**
     * 获取父类型
     * @param cId 课程子类型id
     * @return
     */
    ServiceResult getParentType(Integer cId);


    /**
     * 获取课程详情
     * @param cId
     * @return
     */
    ServiceResult getDetailInfo(Integer cId);

}
