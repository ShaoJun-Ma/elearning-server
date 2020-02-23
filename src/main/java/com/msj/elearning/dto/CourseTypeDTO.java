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
    private String name;
    private List<CourseType> childCourseType;
}
