package ru.venediktov.patterns.chainofresponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidEmailCheck extends Middleware{

  @Override
  public boolean check(String email, String password) {
    Pattern pattern = Pattern.compile("@com");
    Matcher matcher = pattern.matcher(email);
    System.out.println("Email check ...");
    if (matcher.find()) {
      return this.checkNext(email, password);
    }
    return false;
  }

}
