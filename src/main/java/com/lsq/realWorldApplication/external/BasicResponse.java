package com.lsq.realWorldApplication.external;

public class BasicResponse {
    Boolean success;
    String message;

    public BasicResponse(Boolean success) {
        this.success = success;
    }

    public BasicResponse(Boolean success, String msg) {
        this.success = success;
        this.message = msg;
    }
}