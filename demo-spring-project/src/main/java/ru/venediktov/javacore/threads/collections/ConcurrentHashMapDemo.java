package ru.venediktov.javacore.threads.collections;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Итераторы подвержены сбоям при работе в многопоточном приложении.
 * Если один поток изменяет содержимое коллекции, а второй поток обрабатывает ее итератором Iterator,
 * то при вызове метода Iterator.hasNext() или Iterator.next() будет брошено исключение ConcurrentModificationException
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {
        try {
            removeFromHashMap();
        } catch (ConcurrentModificationException e) {
            System.out.println("ConcurrentModificationException in HashMap");
        }
        removeFromConcurrentHashMap();
    }

    private static void removeFromHashMap() {
        Map<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "one");
        hashMap.put(2, "two");
        hashMap.put(3, "three");

        Iterator<Integer> iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            if (key == 2) {
                hashMap.remove(2);
            }
        }
        System.out.println("removeFromHashMap: " + hashMap.values());
    }

    private static void removeFromConcurrentHashMap() {
        Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put(1, "one");
        concurrentHashMap.put(2, "two");
        concurrentHashMap.put(3, "three");

        Iterator<Integer> iterator = concurrentHashMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            if (key == 2) {
                concurrentHashMap.remove(2);
            }
        }
        System.out.println("removeFromConcurrentHashMap: " + concurrentHashMap.values());
    }

}