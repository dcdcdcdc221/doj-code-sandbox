package com.example.dojcodesandbox;


import com.example.dojcodesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口自定义
 */
public interface CodeSandbox {

    /**
     *执行代码
     * @param executeCodeResponse
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeResponse executeCodeResponse);

}
