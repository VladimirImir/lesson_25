package com.dev.lesson25.queue;

import java.util.Queue;

public class BuyerThread implements Runnable {

    private final Queue<CashBox> cashBoxes;

    public BuyerThread(Queue<CashBox> cashBoxes) {
        this.cashBoxes = cashBoxes;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!cashBoxes.isEmpty()) {
                    CashBox cashBox = cashBoxes.remove();
                    System.out.println(Thread.currentThread().getName() + " обслуживается в кассе " + cashBox);

                    Thread.sleep(5L);

                    System.out.println(Thread.currentThread().getName() + " освобождаю кассу " + cashBox);
                    cashBoxes.add(cashBox);
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + " ожидает свободную кассу ");
                    Thread.sleep(5L);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
