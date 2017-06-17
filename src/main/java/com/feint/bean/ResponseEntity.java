package com.feint.bean;

/**
 * Created by feint on 17/6/16.
 */
public class ResponseEntity<T> {
    private int code;
    private T data;

    public ResponseEntity() {

    }

    public ResponseEntity(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
