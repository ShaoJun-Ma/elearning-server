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
     * @param ptId 课程父类型
     * @param ctId 课程子类型
     * @param rank 课程难度
     * @param isFree 是否免费
     */
    ServiceResult getCourseByPtIdAndCtIdAndRank(Integer ptId,Integer ctId,String rank,Integer isFree,
                                              Integer currentPage,Integer pageSize);


    /**
     * 获取父类型
     * @param ctId 课程子类型id
     * @return
     */
    ServiceResult getParentType(Integer ctId);


    /**
     * 获取课程详情
     * @param cId 课程id
     * @param uId 用户id
     * @return
     */
    ServiceResult getDetailInfo(Integer cId,Integer uId);

    /**
     * 获取课程评价
     * @param cId 课程id
     * @param currentPage 当前页码
     * @param pageSize 每页的条数
     * @return
     */
    ServiceResult getEvaluation(Integer cId,Integer currentPage,Integer pageSize);

    /**
     * 获取章节
     * @param cdId 课程详情id
     * @return
     */
    ServiceResult getChapter(Integer cdId);

}
