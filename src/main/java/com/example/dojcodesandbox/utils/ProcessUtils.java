package com.example.dojcodesandbox.utils;

import com.example.dojcodesandbox.model.ExecuteMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *进程工具类
 */
public class ProcessUtils {
    /**
     * 执行进程并获取信息
     * @param runProcess 运行的进程
     * @param opName 方式(编译/运行)
     * @return
     */
    public static ExecuteMessage runProcessAndGetMessage(Process runProcess, String opName){
        ExecuteMessage executeMessage = new ExecuteMessage();

        //等待程序执行，获取错误码
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            int exitValue = runProcess.waitFor();
            //分批获取正常输出
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
            List<String> outputStrList = new ArrayList<>();
            //正常退出
            if(exitValue == 0){
                System.out.println(opName + "成功");
                //逐行读取
                String compileOutputLine;
                while((compileOutputLine = bufferedReader.readLine()) != null){
                    outputStrList.add(compileOutputLine);
                }
                executeMessage.setMessage(StringUtils.join(outputStrList, "\n"));
            }else {
                //异常退出
                System.out.println(opName + "失败");
                //逐行读取
                String compileOutputLine;
                while((compileOutputLine = bufferedReader.readLine()) != null){
                    outputStrList.add(compileOutputLine);
                }
                executeMessage.setMessage(StringUtils.join(outputStrList, "\n"));
                //分批获取错误输出
                BufferedReader errorBufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                List<String> errorOutputStrList = new ArrayList<>();
                //逐行读取
                String errorCompileOutputLine;
                while((errorCompileOutputLine = errorBufferedReader.readLine()) != null){
                    errorOutputStrList.add(errorCompileOutputLine);
                }
                executeMessage.setMessage(StringUtils.join(outputStrList, "\n"));
            }
            stopWatch.stop();
            //毫秒级
            executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return executeMessage;
    }
}
