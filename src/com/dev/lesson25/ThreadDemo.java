package com.dev.lesson25;

public class ThreadDemo {

    public static void main(String[] args) {
        var simpleThread = new SimpleThread();
        System.out.println(simpleThread.getName() + ": " + simpleThread.getState());
        var runnableThread = new Thread(new SimpleRunnable(), "My Name");
        var lambdaThread = new Thread(() -> System.out.println("Hello from lambda: " + Thread.currentThread().getName()));

        simpleThread.start();
        System.out.println(simpleThread.getName() + ": " + simpleThread.getState());

        runnableThread.start();
        lambdaThread.start();
        try {
            /** Если simpleThread не успеет выполниться за 100 миллисекунд -
             *  потом main все равно продолжить выполнение дальше. */
            simpleThread.join(100L);
            System.out.println(simpleThread.getName() + ": " + simpleThread.getState()); 

            runnableThread.join();
            lambdaThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
