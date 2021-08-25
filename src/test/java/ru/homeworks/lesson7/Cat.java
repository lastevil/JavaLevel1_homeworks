package ru.homeworks.lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean hungry;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        hungry=true;
    }
    public void eat(Plate p) {
        if (hungry==true){
            int remainder = p.decreaseFood(appetite);

//            Если ввести условие о том что кот может не наестся.
//            if (remainder==0){
//                System.out.println("тарелка пуста, покормите кота!");
//            }
//            else if (remainder>0){
//            System.out.println("кот поел, но остался голоден...");
//            appetite=appetite-remainder;
//            }

            if(remainder>=0){
                System.out.println("В тарелке мало еды, "+name+", ушел голодный!");
            }
            else {
                System.out.println(name+" наелся!");
                appetite=0;
                hungry=false;
            }
        }
        else {
            System.out.println(name+" не голоден");
        }
    }
    public void getInfo(){
        if (hungry==true){
        System.out.println(name+" Голоден");
        return;
        }
        System.out.println(name+" Сытый");
    }
}
