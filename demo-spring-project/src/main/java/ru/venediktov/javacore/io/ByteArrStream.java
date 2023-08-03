package ru.venediktov.javacore.io;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ByteArrStream {

    public static void main(String[] args) {
        convertStrToBytes("А");
        readByteArray("qwerty");
    }

    /**
     * Под капотом: для каждого символа из строки находится кодовая точка в Unicode,
     * это значение преобразуется в 2-ую систему, а потом преобразуется в байт(-ы) согласно
     * шаблону UTF-8: если
     * 8—11 бит - 110xxxxx 10xxxxxx
     * 12-16 бит - 1110xxxx 10xxxxxx 10xxxxxx
     * 17-21 бит - 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
     * Полученные байты преобразуются в 10-ую сис-му.
     * Но в java значение байт имеет диапазон [-128;127], поэтому если полученные числа больше 127,
     * то из них вычитается 256 и именно массив таких чисел мы видим.
     */
    public static void convertStrToBytes(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        System.out.println("массив байт исходной строки: " + Arrays.toString(bytes));
        System.out.println("сопоставим байты:");
        for (byte b: bytes) {
            int unsignByte = Byte.toUnsignedInt(b);// int f = b & 0xff;
            System.out.printf("беззнаковый байт = %d ---- байт со знаком = %d\n", unsignByte, (byte) unsignByte);
        }
    }

    public static void readByteArray(String s) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
        int b;
        while ((b = inputStream.read()) != -1) {
            System.out.printf("unicode = %d   char = %c\n", b, (char) b);
        }
    }
}