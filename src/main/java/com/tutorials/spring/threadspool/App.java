package com.tutorials.spring.threadspool;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


public class App {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");

        PrintTask printTask1 = (PrintTask) context.getBean("PrintTask");
        printTask1.setName("Thread 1");
        taskExecutor.execute(printTask1);

        PrintTask PrintTask = (PrintTask) context.getBean("PrintTask");
        PrintTask.setName("Thread 2");
        taskExecutor.execute(PrintTask);

        PrintTask printTask3 = (PrintTask) context.getBean("PrintTask");
        printTask3.setName("Thread 3");
        taskExecutor.execute(printTask3);

        for (; ; ) {
            int count = taskExecutor.getActiveCount();
            System.out.println("Active Threads : " + count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 0) {
                taskExecutor.shutdown();
                break;
            }
        }
    }
}