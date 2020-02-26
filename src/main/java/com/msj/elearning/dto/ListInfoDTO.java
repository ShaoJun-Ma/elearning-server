package com.msj.elearning.dto;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInfoDTO {
    private List<CourseTypeDTO> courseTypeList;
    private PageInfo<CourseDTO> courseList;
}
