package com.lyp.learn.demo.pk01.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户名错误,类级别")
public class UserNameException extends RuntimeException {
}
