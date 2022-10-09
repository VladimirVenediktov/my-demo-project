package ru.venediktov.patterns.chainofresponsibility;

public class CorrectPasswordCheck extends Middleware{

  @Override
  public boolean check(String email, String password) {
    System.out.println("Password check ...");
    return "123".equals(password);
  }
}
