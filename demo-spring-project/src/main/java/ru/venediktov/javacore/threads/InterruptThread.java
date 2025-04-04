package ru.venediktov.javacore.threads;

import lombok.SneakyThrows;

public class InterruptThread {

    public static void main(String []args) {
        interrupt1();
        interrupt2();
    }

    /**
     * Прерывание потока через interrupt(), проверка через isInterrupted() покажет true
     */
    @SneakyThrows
    private static void interrupt1() {
        Runnable task = () -> {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("Process ...");
            }
            System.out.println("Interrupted. Finished");
            // флаг о прерывании потока стал true, потому что прервали через interrupt()
            System.out.println("flag = " + Thread.currentThread().isInterrupted());
        };

        Thread thread1 = new Thread(task);
        thread1.start();

        Thread.sleep(1000);

        thread1.interrupt();
    }

    /**
     * Прерывание потока через interrupt(), проверка через Thread.interrupted() приводит к сбросу флага в false
     */
    @SneakyThrows
    private static void interrupt2() {
        Runnable task = () -> {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("Process ...");
            }
            System.out.println("Interrupted. Finished");
            // флаг о прерывании потока стал false, потому что прервали через interrupt(),
            // а потом вызвали Thread.interrupted() для проверки (этот метод сбрасывает флаг в false)
            System.out.println(Thread.currentThread().getName() + " is interrupted? - " + Thread.interrupted());
            System.out.println(Thread.currentThread().getName() + " flag = " + Thread.currentThread().isInterrupted());
        };
        Thread thread1 = new Thread(task);
        thread1.start();

        Thread.sleep(1000);

        thread1.interrupt();

        Thread.sleep(1000);

        // флаг о прерывании потока стал false
        System.out.println(thread1.getName() + " flag = " + thread1.isInterrupted());
    }

}