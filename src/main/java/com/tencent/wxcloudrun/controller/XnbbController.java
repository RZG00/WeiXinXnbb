package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class XnbbController {

    final Logger logger;

    public XnbbController() {
        this.logger = LoggerFactory.getLogger(XnbbController.class);
    }

    @GetMapping(value = "/api/hello")
    ApiResponse hello() {
        logger.info("/api/count get request");
        return ApiResponse.ok("hello");
    }

}
