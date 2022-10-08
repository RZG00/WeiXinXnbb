package com.tencent.wxcloudrun.util;

import com.tencent.wxcloudrun.entity.Msg;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    public static Map<String,String> xmlToMap(HttpServletRequest request) {
        Map<String,String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        ServletInputStream in = null;
        try{
            in = request.getInputStream();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element e: list){
                map.put(e.getName(),e.getText());
            }
            in.close();
        }catch (Exception e){

        }
        return map;
    }

    /**
     * 文本对象转换成xml
     * @param message
     * @return
     */
    public static  String textMessageToXml(Object message){
        XStream xStream = new XStream();
        xStream.alias("xml",message.getClass());
        return xStream.toXML(message);
    }

    /**
     * 初始化文本信息
     * @param toUserName
     * @param fromUserName
     * @param msgType
     * @param content
     * @return
     */
    public static String initText(String toUserName,String fromUserName,String msgType,String content){
        Msg textMessage = new Msg();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(msgType);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setContent(content);
        return Util.textMessageToXml(textMessage);
    }

}
