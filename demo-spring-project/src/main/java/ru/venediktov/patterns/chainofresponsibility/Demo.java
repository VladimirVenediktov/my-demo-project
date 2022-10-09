package ru.venediktov.patterns.chainofresponsibility;

public class Demo {

  public static void main(String[] args) {
    ValidEmailCheck validEmailCheck = new ValidEmailCheck();
    validEmailCheck.setNextMiddleware(new CorrectPasswordCheck());
    validEmailCheck.check("@com", "123");
  }

}
