package ru.venediktov.patterns;

/**
 * Ленивая инициализация для однопоточного приложения.
 */
public class LazyInitializedSingleton {

  private static LazyInitializedSingleton instance;

  private LazyInitializedSingleton() {
  }

  public static LazyInitializedSingleton getInstance() {
    if (instance == null) {
      return new LazyInitializedSingleton();
    }
    return instance;
  }
}
