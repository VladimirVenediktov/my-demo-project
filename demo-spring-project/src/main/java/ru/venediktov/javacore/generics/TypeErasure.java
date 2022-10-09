package ru.venediktov.javacore.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Информация о типе параметра T в generic стирается после компиляции.
 */
public class TypeErasure<T> {

  private T value1;
  private T value2;

  public void printValues(TypeErasure<T> typeErasure) {
    System.out.println(String.format("%s and %s", value1, value2));
  }

  public static <T> TypeErasure<T> createAndAddValues(Object o1, Object o2) {
    TypeErasure<T> result = new TypeErasure<>();
    result.value1 = (T) o1;// тут никакой ошибки не будет, т.к кастить будет в Object (из-за стирания типа), а не в Integer
    result.value2 = (T) o2;
    return result;
  }

  public static void main(String[] args) {
    // example 1
    Double d = 22.111;
    String s = "String!";
    TypeErasure<Integer> test = createAndAddValues(d, s); // во время компиляции это стало TypeErasure<Object> test
    try {
      int intValue = test.value1.intValue();
    } catch (Exception e) {
      System.err.println(e);
    }
    test.printValues(test);

    // example 2
    List<String> strings = new ArrayList<>();
    List<Integer> numbers = new ArrayList<>();

    System.out.println(strings.getClass() == numbers.getClass()); // true
  }

}
