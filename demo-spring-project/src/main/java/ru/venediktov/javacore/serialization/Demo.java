package ru.venediktov.javacore.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lombok.SneakyThrows;

public class Demo {

    @SneakyThrows
    public static void main(String[] args) {

        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(new FileOutputStream("person.txt"));
        objectOutputStream.writeObject(new Person(23, "Jack", 186));
        objectOutputStream.flush();
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("person.txt"));
        Object personFromFile = (Person) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println("Десериализованный объект: " + personFromFile);
    }
}
