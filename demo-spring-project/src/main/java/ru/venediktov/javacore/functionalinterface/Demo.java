package ru.venediktov.javacore.functionalinterface;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Функциональный интерфейс — это интерфейс (с @FunctionalInterface), который содержит
 * ровно один абстрактный метод, то есть описание метода без тела.
 * Лямбды - это компактный синтаксис, для передачи блока кода в качестве параметра в другой код (метод).
 * Но чтобы у этого блока кода был какой-то объектный тип, нужен функциональный интерфейс.
 * Лямбда-выражение не выполняется само по себе, а нужно как раз для реализации метода,
 * который определён в функциональном интерфейсе
 * Через функциональный интерфейс мы обращаемся к лямбда-выражению для его реализации.
 */
public class Demo {


  public static void main(String[] args) {

    //Predicate<T> проверяет соблюдение некоторого условия
    Predicate<Integer> isPositiveNumber = x -> x > 0;
    System.out.println(isPositiveNumber.test(5));

    //Supplier<T> не принимает никаких аргументов, но должен возвращать объект типа T
    Supplier<Double> getRandomNumber = Math::random;
    System.out.println(getRandomNumber.get());

    //Function<T,R> представляет функцию перехода от объекта типа T к объекту типа R:
    Function<Integer, String> convertIntToString = x -> Integer.toString(x);
    System.out.println(convertIntToString.apply(777));

    //свой функциональный интерфейс
    Operationable operation = (x, y) -> x+y;
    System.out.println(operation.calculate(4,5));
  }

}

@FunctionalInterface
interface Operationable {
  int calculate(int a, int b);
}
