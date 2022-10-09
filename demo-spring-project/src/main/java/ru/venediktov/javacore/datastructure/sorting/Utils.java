package ru.venediktov.javacore.datastructure.sorting;

public class Utils {

  public static String arrayToString(int[] array) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < array.length; i++) {
      if (i > 0) {
        sb.append(", ");
      }
      sb.append(array[i]);
    }
    sb.append("]");
    return sb.toString();
  }

}
