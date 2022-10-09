package ru.venediktov.javacore.datastructure.sorting;

/**
 * Сортировка выбором, O(n^2).
 */
public class SelectionSort {

  public static void main(String[] args) {
    int[] array = {5, 2, 4, 0, 3, 7, 1, 8, -2, 6};
    System.out.println("Исходный массив: " + Utils.arrayToString(array));
    selectionSort(array);
    System.out.println("Отсортированный массив: " + Utils.arrayToString(array));
  }

  private static int[] selectionSort(int[] array) {
    for (int i = 0; i < array.length; i++) {
      swap(array, i, findMinValueIndex(array, i));
    }
    return array;
  }

  private static int findMinValueIndex(int[] array, int start) {
    int minValueIndex = start;
    for (int i = start + 1; i < array.length; i++) {
      if (array[i] < array[minValueIndex]) {
        minValueIndex = i;
      }
    }
    return minValueIndex;
  }

  private static void swap(int[] array, int x, int y) {
    int temp = array[x];
    array[x] = array[y];
    array[y] = temp;
  }

}
