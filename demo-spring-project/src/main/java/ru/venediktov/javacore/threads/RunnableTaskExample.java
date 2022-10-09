package ru.venediktov.javacore.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableTaskExample {

  public void execute() {
    System.out.println("Runnable");

    // сервис-исполнитель (более высокоуровневая работа с потоками, чем создание руками)
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    for (int i = 0; i <= 10; i++) {
      executorService.submit(() -> System.out.println("Из потока " + Thread.currentThread().getName()));
    }
    //обязательно завершаем, чтобы исполнитель не ждал вечно
    executorService.shutdown();

    System.out.println("из потока " + Thread.currentThread().getName());
  }

}
