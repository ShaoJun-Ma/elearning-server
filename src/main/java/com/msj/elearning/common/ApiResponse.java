package com.msj.elearning.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private Integer status;
    private String message;
    private T result;

    public ApiResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
