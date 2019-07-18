package com.lyp.learn.demo.pk01.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 处理项目全局的异常
 * 1.在@ExceptionHandler 方法的入参中可以加入 Exception 类型参数，该参数即对应发生的异常对象
 * 2. @ExceptionHandler 方法的入参中不能传入Map,若希望把异常信息传递到页面上，需要使用ModelAndView 作为返回值
 * 3. @ExceptionHandler 方法标记的异常具有优先级的问题，异常会被处理它的最近匹配的处理异常处理
 * 4.@ControllerAdvce ： 如果在当前的 Handler 中找不到 @ExceptionHandler 标记的方法来处理当前方法出现的异常，则将去 @ControllerAdvice 标记的类中查找@ExceptionHandler 标记的方法处理异常
 */
@ControllerAdvice
public class HandleAllException {

    public HandleAllException(){
        System.out.println("HandleAllException controcutor......");
    }

    @ExceptionHandler
    public String handleRuntime(Exception ex){
        System.out.println("all ----->出错了 runtime  ： handleRuntime 在处理");
        System.out.println("all ----->具体异常信息：：：："+ex);
        return "error";
    }


}
