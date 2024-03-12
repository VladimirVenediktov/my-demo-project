package ru.venediktov.javacore.nested;

public class Bicycle {
    private String model;
    private float weight;
    private float seatPostDiameter;

    public Bicycle(String model, float weight, float seatPostDiameter) {
        this.model = model;
        this.weight = weight;
        this.seatPostDiameter = seatPostDiameter;
    }

    public void start() {
        System.out.println("Поехали!");
    }

    public class HandleBar {
        public void right() {
            System.out.println("Руль вправо!");
        }

        public void left() {
            System.out.println("Руль влево!");
        }
    }

    public class Seat {
        public void up() {
            System.out.println("Сиденье поднято выше!");
        }

        public void down() {
            System.out.println("Сиденье опущено ниже!");
        }

        public void getSeatPostDiameter() {
            System.out.println("Диаметер штыря для сиденья = " + Bicycle.this.seatPostDiameter);
        }
    }
}
