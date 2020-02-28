package com.msj.elearning.mapper;

import com.msj.elearning.pojo.CourseEvaluation;

import java.util.List;

public interface CourseEvaluationMapper {
    List<CourseEvaluation> findEvaluationByCId(Integer cId);
}
