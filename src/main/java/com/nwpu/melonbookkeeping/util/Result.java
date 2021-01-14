package com.nwpu.melonbookkeeping.util;

import lombok.Data;

import java.io.Serializable;

/**
 * json反馈结果构造器
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private boolean success;
    private String errorCode;
    private String errorMessage;
    private T data;

    /**
     * Construct successful result
     *
     * @param data the data for result
     */
    public Result(T data) {
        this.success = true;
        this.data = data;
    }

    /**
     * Construct successful result
     */
    public Result() {
        this.success = true;
    }

    /**
     * Construct error result
     *
     * @param error The error code and error message for result.
     */
    public Result(String[] error) {
        this.success = false;
        this.errorCode = error[0];
        this.errorMessage = error[1];
    }
}
