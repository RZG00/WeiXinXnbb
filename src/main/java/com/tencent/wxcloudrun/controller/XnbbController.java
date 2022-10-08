package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.entity.Msg;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.Map;
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

        StringBuilder remsg=new StringBuilder("<xml>\n" +
                "  <ToUserName>"+msg.getFromUserName()+"</ToUserName>\n" +
                "  <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[你好]]></Content>\n" +
                "</xml>");

        return ApiResponse.ok(remsg.toString()) ;
    }

    /**
     * 实现你问我答
     * @param request
     * @return
     */
    @PostMapping("/api/chat")
    public String replyMessage(HttpServletRequest request){

        Map<String,String> map = Util.xmlToMap(request);
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String content = map.get("Content");
        String message = "success";

        message = Util.initText(toUserName,fromUserName, msgType,content);
//        if (MessageType.MESSAGE_TEXT.equals(msgType)){
//            //回复文本信息触发的消息
//            message = MessageUtil.initText(toUserName,fromUserName, TrayIcon.MessageType.MESSAGE_TEXT,content);
//
//        }else if (MessageType.MESSAGE_EVENT.equals(msgType)){
//            //关注时触发的消息
//            String event = map.get("Event");
//            if (MessageType.MESSAGE_SUBSCRIBE.equals(event)){
//                message = MessageUtil.initText(toUserName,fromUserName,MessageType.MESSAGE_TEXT,"欢迎关注evan波的公众号");
//            }
//        }
        return message;
    }


}
