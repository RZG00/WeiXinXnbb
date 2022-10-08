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
    String talk(@RequestBody Msg msg, HttpServletRequest req) {
        System.out.println("ruanzhenguo:"+msg.toString());
        System.out.println(req.toString());

        StringBuilder remsg=new StringBuilder("<xml>\n" +
                "  <ToUserName>"+msg.getFromUserName()+"</ToUserName>\n" +
                "  <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[你好]]></Content>\n" +
                "</xml>");

        return remsg.toString();
    }


}
