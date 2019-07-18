package com.lyp.learn.demo.pk01;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Hello {

    private static final String  SUCCESS = "success";

    /**
     * 为了测试 spring 与 springMVC 整合
     */
    public Hello(){
        System.out.println("Hello constructor ...........");
    }

    /**
     * 为了测试 spring 与 springMVC 整合
     */
    @Autowired
    private UserService userService;

    /**
     * 1.使用@RequestMapping注解来映射请求的 URL
     * 2.返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver 视图解析器，会做如下的解析：
     *  通过 prefix + returnValue + suffix 这样的方式得到实际的物理视图，然后做请求转发操作
     *  /WEB-INF/views/success.jsp
     *
     */

    @RequestMapping("/hi")
    public String hiMethod(){

        System.out.println("Hello ....hiMethod....");
        return SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/testJson")
    public String testJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("a","aa");
        map.put("b","bb");
        System.out.println("test json ....");
        return JSONObject.toJSONString(map);
    }
}
