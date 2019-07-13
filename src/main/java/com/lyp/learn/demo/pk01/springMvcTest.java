package com.lyp.learn.demo.pk01;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/smtest")
@Controller
public class springMvcTest {

    private static final String SUCCESS = "success";

    @RequestMapping(value = "/testRequestMapping")
    public String testRequestMapping(){
        System.out.println("testRequestMapping ....");
        return SUCCESS;
    }

    @RequestMapping(value = "testMethodParams",params = {"userName","age != 10"})
    public String testMethodParams(){
        System.out.println("testMethodParams ....");
        return SUCCESS;
    }

    @RequestMapping(value = "/testPathVariable/{userName}/list")
    public String testPathVariable(@PathVariable("userName") String un){
        System.out.println("testPathVariable  userName = " + un);
        return SUCCESS;
    }
}
