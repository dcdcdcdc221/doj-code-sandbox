package com.example.dojcodesandbox.controller;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/")
public class MainController {
<<<<<<< HEAD
    //定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

    @Resource
=======
    //定义健全请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_KEY = "secretKey";

    @Resource
    private JavaNative
>>>>>>> 3cf484a0f6c3261106140b616b1e5b81e3d8f837
}
