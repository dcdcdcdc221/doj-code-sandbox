package com.example.dojcodesandbox;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TimeOutThread  extends Thread{



    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("超时结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DeamonThread t1 = new DeamonThread();
        TimeOutThread t2 = new TimeOutThread();
        t1.setDaemon(true);
        t1.start();
        t2.start();

    }

}
