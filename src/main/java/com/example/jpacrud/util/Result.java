package com.example.jpacrud.util;

import com.example.jpacrud.enums.ResponseStatus;
import lombok.Data;

import java.util.List;

@Data
public class Result<T> {
    private int status;
    private String message;
    private T data;
    private List<String> errors;

    public Result(ResponseStatus responseStatus, T data) {
        this.status = responseStatus.getCode();
        this.message = responseStatus.getMessage();
        this.data = data;
    }

    public Result(ResponseStatus responseStatus, List<String> errors) {
        this.status = responseStatus.getCode();
        this.message = responseStatus.getMessage();
        this.errors = errors;
    }

    // Getters and Setters
}

