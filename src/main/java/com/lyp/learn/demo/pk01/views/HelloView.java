package com.lyp.learn.demo.pk01.views;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 *  自定义视图
 *  1. 实现 View 接口，并且让spring 管理
 *  2. 在 spring 配置文件中，配置 Bean 视图解析器，并指定order
 *  3. 在测试方法中，返回 helloView 字符串
 */
@Component
public class HelloView implements View {
    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().write("helloView 自定义视图 time :" + new Date());
    }
}
