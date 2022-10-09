package ru.venediktov.patterns.templatemethod;

public class CreditPayingOperation extends OperationService {

  @Override
  Operation create() {
    System.out.println("Created Credit Paying Operation");
    return new CreditPaying();
  }

  @Override
  void validate() {
    System.out.println("Validate Credit Paying Operation");
  }

  @Override
  Operation save(Operation operation) {
    System.out.println("Saving Credit Paying Operation");
    return operation;
  }
}
