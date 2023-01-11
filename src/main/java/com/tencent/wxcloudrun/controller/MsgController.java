package com.tencent.wxcloudrun.controller;

import com.google.gson.Gson;
import com.tencent.wxcloudrun.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Jing Tao
 * @date 2023/1/11 17:55
 */
@RestController
@RequestMapping(value = "/msg")
@Slf4j
public class MsgController {

    @RequestMapping(method = RequestMethod.POST, produces = {"application/xml;charset=UTF-8"})
    public void receiveMessage(@RequestBody String xml, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        Map<String, Object> params = XmlUtil.xmlStrToMap(xml);
        log.info("params:{}", new Gson().toJson(params));
        System.out.println("params=" + new Gson().toJson(params));
    }
}
