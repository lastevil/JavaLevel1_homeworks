package ru.homeworks.lesson6.animals;

public class Cat extends Animals{
    private static int numberCat;

    public Cat(String name){
        super(name);
        numberCat++;
    }

    @Override
    public void run(int a) {
        if (a>=0 && a<=200){
            System.out.println(name+" пробежал(а) "+a+" метров");
        } else if(a>200){
            System.out.println(name+" пробежал 200м и устал, осталось бежать "+(a-200));
        } else System.out.println("Можно бежать только вперед!");
    }

    @Override
    public void swim(int a) {
        System.out.println("Коты не умеют плавать!");
    }

    public static int getCount() {
        return numberCat;
    }
}
