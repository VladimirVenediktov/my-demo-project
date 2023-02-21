package ru.venediktov.javacore.threads;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;

@Slf4j
public class ForkJoinPoolExample {

    public static void main(String[] args) {
        execute();
    }

    public static void execute() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);

        Runnable runnable = () -> IntStream.range(0, 10)
                .parallel()
                .forEach(i -> {
                            sleep(i);
                            try {
                                simulateException(i);
                                System.out.println("вывод значения: " + i + " из потока " + Thread.currentThread().getName());
                            } catch (Exception e) {
                                System.out.println("ошибка при обработке элемента " + i + "\n" + e);
                            }
                        }
                );

        try {
            ForkJoinTask<?> forkJoinTask = forkJoinPool.submit(runnable);
            forkJoinTask.get();//или Thread.sleep(10000);
            System.out.println("THE END");
        } catch (Exception e) {
        } finally {
            if (forkJoinPool != null) {
                forkJoinPool.shutdown();
            }
        }
    }

    private static void sleep(int i) {
        try {
            if (Arrays.asList(1, 3).contains(i)) {
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void simulateException(int i) {
        if (i == 5) {
            throw new NullPointerException();
        }
    }

}