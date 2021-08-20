package ru.homeworks.lesson6.animals;

public class Dog extends Animals{

    private static int numberDog;

    public Dog(String name){
        super(name);
        numberDog++;
    }
    @Override
    public void run(int a) {
        if (a>=0 && a<=500){
            System.out.println(name+" пробежал(а) "+a+" метров");
        } else if(a>500){
            System.out.println(name+" пробежал 500м и устал, осталось бежать "+(a-500));
        } else System.out.println("Можно бежать только вперед!");
    }

    @Override
    public void swim(int a) {
        if (a>=0 && a<=10){
            System.out.println(name+" проплыл(а) "+a+" метров");
        } else if(a>10){
            System.out.println(name+" столько плыть не может!");
        }
    }

    public static int getCount() {
        return numberDog;
    }

}
