package com.dev.lesson26.pool;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(4);
        threadPool.scheduleAtFixedRate(() -> System.out.println("OK!"), 2L, 4L, TimeUnit.SECONDS);

        //threadPool.shutdown();
        //threadPool.awaitTermination(1L, TimeUnit.HOURS);


        /**
         *  Executors.newSingleThreadExecutor() - создает нам ThredPool который состоит всего лишь из одного
         * единственного потока.
         */
        //Executors.newSingleThreadExecutor();
        /**
         *  Executors.newFixedThreadPool(5) - аналогия с newSingleThreadExecutor() - только тут столько потоков
         * сколько мы передали.
         */
        //Executors.newFixedThreadPool(5);
        /**
         * Executors.newCachedThreadPool() - он отличается тем что он безграничных, тоисть сколько мы задач
         * одновременно отправим столько потоков и будет создан, но к примеру если мы отправили 5 задач
         * в наш Pool и у нас создалось 5 потолов на каждый из этих задач, в след. раз если мы отправляем
         * к примеру 1-2-3 любо 4-5 задач одновременно - новые потоки не будут создаваться.
         * Будут браться просто из нашего Pool-a.
         * Но если бы мы отправили в след. раз одновременно сразу же к примеру 7 задач, то у нас в нашем Pool-е
         * осталось всего ли 5 закэшированных потоков, следовательно у нас не хватает ещё 2 потока для
         * того что бы выполнить оставщиеся 2 задачи, следовательно они тоже будут созданы.
         * Это называется - CachedThreadPool.
         */
        //Executors.newCachedThreadPool();
        /**
         * newScheduledThreadPool(3) - суть его в том что мы можем выполнять какие то задачи с какой то
         * задержкой, любо с какой то переодичностью. Например: мы хотим каждые три часа, запускать какую то
         * задачу, например: для обновления информации о нашем пользователе, либо для удаления ненужных
         * файлов на нашем компьютере.
         */
        //Executors.newScheduledThreadPool(3);
        /**
         * Executors.newWorkStealingPool() - суть его в том что он создает ThreadPool на основании уже
         * другой реализации Poll-a - это ForkJoinPool. Он созлает Pool такого размера который позволяет
         * кол-во вашего процессора через объект getRuntime - мы можем получить кол-во свободных процессоров
         * у нашего компа. На основании этого значения мы можем создать Pool из оптимально кол-ва потоков.
         *
         */
        //Executors.newWorkStealingPool();

    }

    private static void test() throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        Future<Integer> future = threadPool.submit(() -> {
            Thread.sleep(2000);
            System.out.println("It's callable");
            return 1;
        });

        System.out.println("Result: " + future.get());
        threadPool.shutdown();
        threadPool.awaitTermination(1L, TimeUnit.HOURS);
        System.out.println("main end");
    }
}
