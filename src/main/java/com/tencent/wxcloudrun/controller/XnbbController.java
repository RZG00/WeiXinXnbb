package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.entity.Msg;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.util.Util;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class XnbbController {

    final Logger logger;
    private static final String TO_USER_NAME = "ToUserName";
    private static final String FROM_USER_NAME = "FromUserName";
    private static final String CREATETIME = "CreateTime";
    private static final String MSGTYPE = "MsgType";
    private static final String CONTENT = "Content";

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

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(TO_USER_NAME,map.get(FROM_USER_NAME));
        hashMap.put(FROM_USER_NAME,map.get(TO_USER_NAME));
        hashMap.put(CREATETIME,String.valueOf(System.currentTimeMillis()));
        switch (msgType) {
            case "text" :
                try {
                    hashMap.put(MSGTYPE,"text");
                    hashMap.put(CONTENT,content);
                    message = Util.mapToXml(hashMap);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }



        return message;
//        return "";
    }


    @GetMapping("/api/chat")
    public String checkLink(@RequestParam String signature,
                            @RequestParam String timestamp,
                            @RequestParam String nonce,
                            @RequestParam String echostr
    ){
        return echostr;

    }

}
