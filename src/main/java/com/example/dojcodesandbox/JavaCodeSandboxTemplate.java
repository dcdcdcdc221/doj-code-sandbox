package com.example.dojcodesandbox;

import com.example.dojcodesandbox.model.ExecuteCodeResponse;

import java.io.File;

/**
 * 代码沙箱模板实现
 */
public class JavaCodeSandboxTemplate implements CodeSandbox{
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeResponse executeCodeResponse) {
        return null;
    }

    /**
     * 1.把用户的代码保存为文件
     * @param code
     * @return
     */
    public File saveCodeToFile(String code){

    }
}
