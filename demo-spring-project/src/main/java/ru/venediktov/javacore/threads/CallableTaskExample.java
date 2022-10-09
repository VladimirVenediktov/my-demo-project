package ru.venediktov.javacore.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTaskExample {

  public void execute() {
    System.out.println("Callable");

    // сервис-исполнитель (более высокоуровневая работа с потоками, чем создание руками)
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    Callable<String> task = () -> "из потока " + Thread.currentThread().getName();

    for (int i = 0; i <= 10; i++) {
      Future<String> future = executorService.submit(task);
      try {
        //get() блокирует поток до тех пор, пока задача не будет завершена
        System.out.println(future.get());
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }
    //обязательно завершаем, чтобы исполнитель не ждал вечно
    executorService.shutdown();

    System.out.println("из потока " + Thread.currentThread().getName());
  }

}
