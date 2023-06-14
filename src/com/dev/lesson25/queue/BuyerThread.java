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
            synchronized (cashBoxes) {
                while (true) {
                    if (!cashBoxes.isEmpty()) {
                        CashBox cashBox = cashBoxes.remove();
                        System.out.println(Thread.currentThread().getName() + " обслуживается в кассе " + cashBox);

                        /** метод wait() - отпускает монитор объекта. */
                        /** метод wait(0L) - тоже самое, что и вызов wait() без параметров. */
                        cashBoxes.wait(5L);

                        System.out.println(Thread.currentThread().getName() + " освобождаю кассу " + cashBox);
                        cashBoxes.add(cashBox);
                        /** wait(), notify(), notifyAll() - нельзя вызывать у объектов монитов который
                         * мы не захватили!
                         * notify(), notifyAll() - эти методы нужны только для того что бы уведомить другие
                         * потоки которые вызвали на этом же самом объекте метод wait() - что они могут
                         * продолжить свое выполнение как только поток который вызвал notify() любо notifyAll()
                         * отпустит монито объекта - в нашем случае это был - cashBoxes.
                         * */
                        cashBoxes.notifyAll();
                        break;
                    } else {
                        System.out.println(Thread.currentThread().getName() + " ожидает свободную кассу ");
                        /** wait() - нужен для того что бы поток который вызвал его на объекте ждал
                         *  определенное время, олюбо не определенное время, все зависит от того
                         *  какой и перегруженных методов wait() мы вызвали.
                        /**  */
                        cashBoxes.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
