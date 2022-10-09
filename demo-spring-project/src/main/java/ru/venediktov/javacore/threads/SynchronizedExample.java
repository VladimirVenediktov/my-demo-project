package ru.venediktov.javacore.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SynchronizedExample {

  private static final int NUM_INCREMENTS = 1000;

  private static int count = 0;

  public void execute() {
    System.out.println("Synchronized VS Non synchronized method example");
    System.out.println("Count for Sync:");
    testIncrement(SynchronizedExample::incrementSync);
    System.out.println("Count for NonSync:");
    testIncrement(SynchronizedExample::increment);
  }

  private static void testIncrement(Callable<Integer> callable) {
    count = 0;
    // с помощью сервиса-исполнителя (ExecutorService) выполним задачу в нескольких потоках
    ExecutorService executor = Executors.newFixedThreadPool(3);
    IntStream.range(0, NUM_INCREMENTS)
        .forEach(i -> executor.submit(callable));
    SynchronizedExample.stop(executor);
    System.out.println(count);
  }

  /**
   * Синхронизированный метод для выполнения инкремента.
   */
  private static synchronized int incrementSync() {
    // демонстрация кейса, когда не все задачи успели выполнится (в рамках awaitTermination)
    try {
      if (count == 999) {
        Thread.sleep(900);
        System.out.println("Не прервалось");
      }
    } catch (InterruptedException e) {
      System.err.println("Прерывание");
      e.printStackTrace();
    }
    return count++;
  }

  /**
   * Несинхронизированный метод для выполнения инкремента.
   */
  private static int increment() {
    return count++;
  }

  /**
   * Работу исполнителей надо завершать явно.
   */
  public static void stop(ExecutorService executor) {
    executor.shutdown();// закрываем пул, чтобы он больше не мог принять новых задач
    try {
      // ждем заданный промежуток времени пока все задания не будут выполнены,
      // после которого если что-то не успело выполниться - будет прервано
      if (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
    }
  }
}
