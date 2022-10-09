package ru.venediktov.javacore.threads;

/**
 * Многопоточность.
 */
public class ThreadsExample {

  public static void main(String[] args) {
    // простейшее создание отдельного потока
    Runnable task = () -> System.out.println("New thread is created!");
    new Thread(task).start();
    // или
    new NewThread().start();

    //new RunnableTaskExample().execute();
    //new CallableTaskExample().execute();
    //new SynchronizedExample().execute();
  }

  public static class NewThread extends Thread {
    @Override
    public void run() {
      System.out.println("Hello from your new thread!");
    }
  }

}
