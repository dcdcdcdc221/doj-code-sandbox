package com.example.dojcodesandbox.controller;

import org.springframework.web.bind.annotation.RestController;



@RestController("/")
public class MainController {
    //定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

}
