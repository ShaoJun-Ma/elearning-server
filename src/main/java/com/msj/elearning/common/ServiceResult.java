package com.msj.elearning.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResult<T> {
    private boolean success;
    private String message;
    private T result;

    public ServiceResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
