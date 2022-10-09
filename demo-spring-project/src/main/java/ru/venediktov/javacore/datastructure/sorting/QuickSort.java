package ru.venediktov.javacore.datastructure.sorting;

/**
 * Быстрая сортировка, O(n log n).
 */
public class QuickSort {

  public static void main(String[] args) {
    int [] array = new int[] {64, 42, 73, 41, 32, 53, 16, 24, 57, 42, 74, 55, 36};
    System.out.println(Utils.arrayToString(array));
    quickSort(array, 0, array.length - 1);
    System.out.println(Utils.arrayToString(array));
  }

  private static void quickSort(int[] array, int from, int to) {
    if (from < to) {
      int divideIndex = partition(array, from, to);
      quickSort(array, from, divideIndex - 1);
      quickSort(array, divideIndex, to);
    }
  }

  //выбор опорного элемента из массива
  private static int partition(int[] array, int from, int to) {
    int leftIndex = from;
    int rightIndex = to;

    int pivot = array[from + (to - from) / 2];

    while (leftIndex <= rightIndex) {
      while (array[leftIndex] < pivot) {
        leftIndex++;
      }

      while (array[rightIndex] > pivot) {
        rightIndex--;
      }

      if (leftIndex <= rightIndex) {
        swap(array, rightIndex, leftIndex);
        leftIndex++;
        rightIndex--;
      }
    }
    return leftIndex;
  }

  private static void swap(int[] array, int index1, int index2) {
    int tmp  = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
  }

}
