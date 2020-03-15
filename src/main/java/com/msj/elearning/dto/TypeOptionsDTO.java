package com.msj.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOptionsDTO {
    //类型id
    private Integer value;
    //父类型名
    private String label;
    //子类型列表
    private List<TypeOptionDTO> children;
}
