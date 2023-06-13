package com.dev.lesson25;

public class SimpleRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello from Runnable: " + Thread.currentThread().getName());
    }
}
