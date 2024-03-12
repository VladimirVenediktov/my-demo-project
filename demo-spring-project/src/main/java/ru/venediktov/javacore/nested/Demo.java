package ru.venediktov.javacore.nested;

public class Demo {

    public static void main(String[] args) {
        Bicycle bicycle = new Bicycle("BMX", 20, 4);
        Bicycle.HandleBar handleBar = bicycle.new HandleBar();
        Bicycle.Seat seat = bicycle.new Seat();
        seat.up();//приподнять сиденье
        bicycle.start();
        handleBar.left();
        handleBar.right();

        seat.getSeatPostDiameter();//доступ к переменным внешнего класса
    }
}