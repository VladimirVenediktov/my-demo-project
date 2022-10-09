package ru.venediktov.javacore.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Дженерики (обобщения) - параметризованные типы, это особый способ описание используемых типов,
 * который компилятор сможет использовать в своей работе для обеспечения типобезопасности.
 * Дженерики являются инвариантными.
 * Говоря о дженериках мы всегда имеем две категории: типизированные типы (Generic Types) и "сырые" типы (Raw Types).
 * Сырые типы — это типы без указания "уточненения" в фигурных скобках (angle brackets): List list = new ArrayList<>();
 * <> - diamond operator, выведение типа (Type Inference) из левой части в правую <>
 */
public class GenericExample<T> {

  private T[] arrayField;

  public T[] getArrayField() {
    return arrayField;
  }

  public void setArrayField(T[] arrayField) {
    this.arrayField = arrayField;
  }

  public static <T> boolean isExistsInArray(T x, GenericExample<T> genericExample) {
    return Arrays.stream(genericExample.getArrayField())
        .anyMatch(y -> y.equals(x));
  }

  public static void main(String[] args) {
    GenericExample<Integer> integerGenericExample = new GenericExample<>();
    integerGenericExample.setArrayField(new Integer[] {1,2,3,4,5});
    boolean existsInArray = isExistsInArray(1, integerGenericExample);
    System.out.println(existsInArray);
    boolean existsInArray2 = isExistsInArray(0, integerGenericExample);
    System.out.println(existsInArray2);

    // Raw Types
    List rawList = new ArrayList<>();
    rawList.add("raw");
    rawList.add(1);
    System.out.println(rawList.get(0).getClass());
    System.out.println(rawList.get(1).getClass());
    System.out.println(rawList);
  }

}
