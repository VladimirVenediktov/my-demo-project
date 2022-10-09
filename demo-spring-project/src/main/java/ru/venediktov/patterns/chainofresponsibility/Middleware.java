package ru.venediktov.patterns.chainofresponsibility;

/**
 * Базовый класс проверок
 */
abstract class Middleware {

  private Middleware nextMiddleware;

  /**
   * Помогает строить цепь из объектов-проверок.
   */
  public Middleware setNextMiddleware(Middleware nextMiddleware) {
    this.nextMiddleware = nextMiddleware;
    return nextMiddleware;
  }

  /**
   * Подклассы реализуют в этом методе конкретные проверки.
   */
  public abstract boolean check(String email, String password);

  /**
   * Запускает проверку в следующем объекте или завершает проверку, если мы в
   * последнем элементе цепи.
   */
  protected boolean checkNext(String email, String password) {
    if (nextMiddleware == null) {
      return true;
    }
    return nextMiddleware.check(email, password);
  }
}
