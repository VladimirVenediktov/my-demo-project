package ru.venediktov.patterns.templatemethod;

/**
 * Шаблонный метод — это поведенческий паттерн,
 * задающий скелет алгоритма в суперклассе и заставляющий подклассы реализовать
 * конкретные шаги этого алгоритма.
 **/
abstract class OperationService {

  abstract Operation create();
  abstract void validate();
  abstract Operation save(Operation operation);

  // шаблонный метод
  void process() {
    Operation operation = create();
    validate();
    save(operation);
  }




}
