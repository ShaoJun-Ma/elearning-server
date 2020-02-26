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
     * @param pid 课程父类型
     * @param cid 课程子类型
     * @param rank 课程难度
     * @param isFree 是否免费
     */
    ServiceResult getCourseByPidAndCidAndRank(Integer pid,Integer cid,String rank,Integer isFree,
                                              Integer currentPage,Integer pageSize);


    /**
     * 获取子类型
     * @param pid 课程父类型id
     * @return
     */
    ServiceResult getChildType(Integer pid);

    /**
     * 获取父类型
     * @param cid 课程子类型id
     * @return
     */
    ServiceResult getParentType(Integer cid);
}
