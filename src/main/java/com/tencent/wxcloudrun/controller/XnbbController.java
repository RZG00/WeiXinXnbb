package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.entity.Msg;
import com.tencent.wxcloudrun.model.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class XnbbController {

    final Logger logger;

    public XnbbController() {
        this.logger = LoggerFactory.getLogger(XnbbController.class);
    }

    @RequestMapping(value = "/api/talk")
    ApiResponse talk(@RequestBody Msg msg, HttpServletRequest req) {
        System.out.println("ruanzhenguo:"+msg.toString());
        System.out.println(req.toString());
        return ApiResponse.ok("hello");
    }

}
