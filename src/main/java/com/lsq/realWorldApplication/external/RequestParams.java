package com.lsq.realWorldApplication.external;

import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.QueryParam;

public class RequestParams {

    @QueryParam("pageSize")
    public Long pageSize;

    @QueryParam("page")
    public Long page;

}