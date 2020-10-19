package com.lsq.realWorldApplication.external;

import java.util.List;

public class BasicResponse<T> {
    public Boolean success;
    public String message;
    public List<T> data;

    public BasicResponse(Boolean success) {
        this.success = success;
    }

    public BasicResponse(Boolean success, String msg) {
        this.success = success;
        this.message = msg;
    }

    public BasicResponse(List<T> data) {
        this.success = true;
        this.message = "Found " + data.size() + " entries.";
        this.data = data;
    }

}