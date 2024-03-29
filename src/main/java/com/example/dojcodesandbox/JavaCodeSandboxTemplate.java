package com.example.dojcodesandbox;

import cn.hutool.core.io.FileUtil;
import com.example.dojcodesandbox.model.ExecuteCodeResponse;
import com.example.dojcodesandbox.model.ExecuteMessage;
import com.example.dojcodesandbox.utils.ProcessUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Java代码沙箱实现
 */
@Slf4j
public class JavaCodeSandboxTemplate implements CodeSandbox{
    private static final String GLOBAL_CODE_DIR_NAME = "tmpCode";

    private static final String GLOBAL_JAVA_CLASS_NAME = "Main.java";

    private static final long TIME_OUT = 5000L;


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeResponse executeCodeResponse) {
        return null;
    }

    /**
     * 1.把用户的代码保存为文件
     * @param code
     * @return
     */
    public File saveCodeToFile(String code) {

        //文件存储路径
        String userDir = System.getProperty("user.dir");
        String globalCodePathName = userDir + File.separator + GLOBAL_CODE_DIR_NAME;

        //判断全局代码目录是否存在，不存在则创建
        if(!FileUtil.exist(globalCodePathName)){
            FileUtil.mkdir(globalCodePathName);
        }

        //把用户的代码隔离存放
        String userCodeParentPath = globalCodePathName + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentPath + File.separator + GLOBAL_JAVA_CLASS_NAME;
        File file = FileUtil.writeString(code, userCodePath, "utf-8");
        return file;
    }


    /**
     * 2.编译用户代码
     * @param userCodeFile
     * @return
     */
    public ExecuteMessage compileFile(File userCodeFile){
        String compileCmd = String.format("javac -encoding UTF-8 %s", userCodeFile.getAbsolutePath());
        return null;
    }

    /**
     *3.执行结果，获得执行结果列表
     * @param userCodeFile
     * @param inputList
     * @return
     */
    public List<ExecuteMessage> runFile(File userCodeFile, List<String> inputList){
        String userCodeParentPath = userCodeFile.getParentFile().getAbsolutePath();
        ArrayList<ExecuteMessage> executeMessageList = new ArrayList<>();
        for(String inputArgs: inputList){
            String runCmd = String.format("java -Xmx256m -Dfile.encoding=UTF-8 %s Main %s", userCodeParentPath, inputArgs);
            try {
                Process runProcess = Runtime.getRuntime().exec(runCmd);
                new Thread(() -> {
                    try {
                        Thread.sleep(TIME_OUT);
                        System.out.println("超时了，中断！");
                        runProcess.destroy();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
                ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(runProcess, "运行");
                System.out.println(executeMessage);
                executeMessageList.add(executeMessage);
            } catch (Exception e) {
                throw new RuntimeException("执行错误",e);
            }

        }
        return executeMessageList;
    }


    /**
     * 获取输出结果
     * @param executeMessageList
     * @return
     */
    public ExecuteCodeResponse getOutputCodeResponse(List<ExecuteMessage> executeMessageList){

    }
}
