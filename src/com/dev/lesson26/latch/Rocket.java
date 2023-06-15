package com.dev.lesson26.latch;

import java.util.concurrent.CountDownLatch;

public class Rocket implements Runnable{

    private final CountDownLatch countDawnLatch;

    public Rocket(CountDownLatch countDawnLatch) {
        this.countDawnLatch = countDawnLatch;
    }

    @Override
    public void run() {
        System.out.println("Ракета готовится к запуску...");
        try {
            countDawnLatch.await();
            System.out.println("Пуск!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
