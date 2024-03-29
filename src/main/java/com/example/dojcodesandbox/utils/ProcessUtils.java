package com.example.dojcodesandbox.utils;

import cn.hutool.core.util.StrUtil;
import com.example.dojcodesandbox.model.ExecuteMessage;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.StopWatch;

import java.io.*;
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

    /**
     * 执行交互式进程并获取信息
     *
     * @param runProcess
     * @param args
     * @return
     */
    public static ExecuteMessage runInteractProcessAndGetMessage(Process runProcess, String args) {
        ExecuteMessage executeMessage = new ExecuteMessage();

        try {
            // 向控制台输入程序
            OutputStream outputStream = runProcess.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            String[] s = args.split(" ");
            String join = StrUtil.join("\n", s) + "\n";
            outputStreamWriter.write(join);
            // 相当于按了回车，执行输入的发送
            outputStreamWriter.flush();

            // 分批获取进程的正常输出
            InputStream inputStream = runProcess.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder compileOutputStringBuilder = new StringBuilder();
            // 逐行读取
            String compileOutputLine;
            while ((compileOutputLine = bufferedReader.readLine()) != null) {
                compileOutputStringBuilder.append(compileOutputLine);
            }
            executeMessage.setMessage(compileOutputStringBuilder.toString());
            // 记得资源的释放，否则会卡死
            outputStreamWriter.close();
            outputStream.close();
            inputStream.close();
            runProcess.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return executeMessage;
    }
}
