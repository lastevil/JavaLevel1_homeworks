package ru.homeworks.lesson6;

import ru.homeworks.lesson6.animals.Animals;
import ru.homeworks.lesson6.animals.Cat;
import ru.homeworks.lesson6.animals.Dog;

public class Main {
    public static void main(String[] args) {

        Cat Murzik =new Cat("Мурзик");
        Cat Vasya = new Cat("Вася");
        Dog Bobik = new Dog("Бобик");
        Animals Seriy = new Cat("Серый");

        Vasya.run(100);
        Murzik.swim(20);
        Murzik.run(300);
        Bobik.run(700);
        Bobik.run(200);
        Bobik.swim(40);
        Bobik.swim(5);

        int c = Cat.getCount();
        int d = Dog.getCount();
        int a = Animals.getCount();

        System.out.println("Животных: "+a+"\nКотов: "+c+"\nСобак: "+d);

    }
}
