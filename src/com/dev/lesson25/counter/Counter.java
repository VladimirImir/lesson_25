package com.dev.lesson25.counter;

/**
 * 1. Создать класс Counter с одним полем:
 * private int count;
 * <p>
 * Добавить методы:
 * - getCount() - для получение поля count
 * - increment() - для увеличения значения поля на единицу
 * - decrement() - для уменьшения значения поля на единицу
 */

public class Counter {

    private static String description;
    private int count;

    public static void init() {
        synchronized (Counter.class) {
            if (description == null) {
                description = "Test description";
            }
        }
    }

    /** synchronized - ключевое слово. */
    /** synchronized - ключевое слово на уровне метода. */
    /** synchronized - помещая в метод, таким образом говоря что в один и тот же момент времени только один */
    /** поток у одного и того же объекта может вызвать этот метод. */

    /** synchronized - ключевое слово. */
    /** synchronized - ключевое слово на уровне блока. */
    /**
     * synchronized() - в () указывает объект который мы хотим синхронизировать.
     */

    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public synchronized void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
