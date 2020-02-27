package com.msj.elearning.mapper;

import com.msj.elearning.common.CommonMapper;
import com.msj.elearning.pojo.CourseChapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseChapterMapper extends CommonMapper<CourseChapter> {
    List<CourseChapter> findChapterByCdIdAndParentId(@Param("cdId")Integer cdId, @Param("parentId")Integer parentId);
}
