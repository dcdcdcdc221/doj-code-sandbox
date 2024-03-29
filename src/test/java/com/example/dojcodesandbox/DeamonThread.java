package com.example.dojcodesandbox;


public class DeamonThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(99999999);//模拟IO等待
            } catch (InterruptedException e) {
                System.out.println("强制中断");
            }
            break;
        }
    }
}