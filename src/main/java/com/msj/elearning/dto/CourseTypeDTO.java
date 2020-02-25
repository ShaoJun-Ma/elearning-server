package com.msj.elearning.dto;
import com.msj.elearning.pojo.CourseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTypeDTO {
    private Integer id;
    //父类型名
    private String name;
    //子类型列表
    private List<CourseType> childCourseType;
}
