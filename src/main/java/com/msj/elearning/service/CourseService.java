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
    ServiceResult getListInfo(Integer isFree);
}
