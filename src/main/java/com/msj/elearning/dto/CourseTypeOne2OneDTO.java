package com.msj.elearning.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTypeOne2OneDTO {
    //父类型名
    private String ctName;
    //子类型名
    private String ptName;
}
