package ru.venediktov.javacore.datastructure.sorting;

/**
 * Сортировка "пузырьком", O(n^2).
 */
public class BubbleSort {

  public static void main(String[] args) {
    int[] array = {1, 4, 2, 3, 9, 6, 0, 12, -1};
    System.out.println(Utils.arrayToString(array));
    bubbleSort(array);
    System.out.println(Utils.arrayToString(array));
  }

  private static void bubbleSort(int[] array) {
    for (int outer = array.length - 1; outer > 0; outer--) {
      for (int i = 0; i < outer; i++) {
        if (array[i] > array[i + 1]) {
          swap(array, i, i + 1);
        }
      }
    }
  }

  private static void swap(int[] array, int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

}