package com.msj.elearning.dto;

import com.msj.elearning.pojo.CourseChapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseChapterDTO {
    private int id;
    //父章节标题
    private String title;
    //父章节介绍
    private String introduction;
    //子章节
    private List<CourseChapter> childChapterList;
}
