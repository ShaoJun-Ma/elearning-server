package com.msj.elearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeOptionDTO {
    //类型id
    private Integer value;
    //类型名
    private String label;
}
