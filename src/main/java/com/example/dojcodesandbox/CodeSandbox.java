package com.example.dojcodesandbox;


import com.example.dojcodesandbox.model.ExecuteCodeRequest;
import com.example.dojcodesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {

    ExecuteCodeResponse executeCode(ExecuteCodeRequest request);
}
