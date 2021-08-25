package ru.homeworks.lesson7;

public class Main {
    public static void main(String[] args) {
    Cat[] cats = { new Cat("Барсик",5),
            new Cat("Васька",7),
            new Cat("Мурзик",3),
            new Cat("Кеша",9),
            new Cat("Салем",2)};

    Plate plate1 = new Plate(14);

        for (Cat cat : cats) {
              cat.eat(plate1);
        }
        System.out.println();

        for (Cat cat : cats) {
            cat.getInfo();
        }
        System.out.println();
        plate1.info();
        plate1.addFood(7);

        System.out.println();
        for (Cat cat : cats) {
            cat.eat(plate1);
        }
        System.out.println();

        for (Cat cat : cats) {
            cat.getInfo();
        }

        System.out.println();
        plate1.info();
    }
}
