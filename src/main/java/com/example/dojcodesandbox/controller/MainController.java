package com.example.dojcodesandbox.controller;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/")
public class MainController {
    //定义健全请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_KEY = "secretKey";

    @Resource
    private JavaNative
}
