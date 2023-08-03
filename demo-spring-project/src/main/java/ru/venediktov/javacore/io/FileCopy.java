package ru.venediktov.javacore.io;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class FileCopy {

    @SneakyThrows
    public static void main(String[] args) {
        //fileBytesCopy();
        //fileCharsCopy();
        bufferedFileBytesCopy();
    }

    // копирование байтов
    public static void fileBytesCopy() throws IOException {
        FileInputStream fis;
        FileOutputStream fos;
        try {
            fis = new FileInputStream("file-for-copy.txt");
            fos = new FileOutputStream("copied-file.txt");
        } catch (FileNotFoundException e) {
            log.error("File not found", e);
            return;
        }
        int a;
        try {
            while ((a = fis.read()) != -1) {
                fos.write(a);
                log.info(String.format("Код символа (DEC): %d, символ: %c", a, (char) a));
            }
        } finally {
            fis.close();
            fos.close();
        }
    }

    // копирование символов
    public static void fileCharsCopy() throws IOException {
        FileReader fileReader;
        FileWriter fileWriter;
        try {
            fileReader = new FileReader("file-for-copy.txt");
            fileWriter = new FileWriter("copied-file.txt");
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("File not found", fileNotFoundException);
            return;
        }
        int c;
        try {
            while ((c = fileReader.read()) != -1) {
                fileWriter.write(c);
                log.info(String.format("Код символа (DEC): %d, символ: %c", c, (char) c));
            }
        } finally {
            fileReader.close();
            fileWriter.close();
        }
    }

    // буфферизированное чтение байтов (сразу пачкой)
    public static void bufferedFileBytesCopy() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("file-for-copy.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 50);
        int b;
        try {
            while ((b = bufferedInputStream.read()) != -1) {
                log.info(String.format("Код символа (DEC): %d, символ: %c", b, (char) b));
            }
        } finally {
            fileInputStream.close();
        }
    }
}
