package com.msj.elearning.mapper;

import com.msj.elearning.pojo.ChapterStatus;
import org.apache.ibatis.annotations.Param;

public interface ChapterStatusMapper {
    ChapterStatus findChapterStatus(@Param("uId") Integer uId,@Param("cdId") Integer cdId,@Param("ccId") Integer ccId);
}
