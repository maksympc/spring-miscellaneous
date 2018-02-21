package com.tutorials.spring.threadspool;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "PrintTask")
@Scope("prototype")
public class PrintTask implements Runnable {
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void run() {

        System.out.println(name + " is running");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " is running");

    }

}