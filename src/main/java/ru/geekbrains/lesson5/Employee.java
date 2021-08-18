package ru.geekbrains.lesson5;

public class Employee {
    private String FIO;
    private String post;
    private String email;
    private String telephone;
    private int salary;
    private int age;

    public Employee(String FIO,String post,String email,
                    String telephone, int salary,int age){
    this.FIO=FIO;
    this.post=post;
    if (email.contains("@") && email.contains(".")){
    this.email=email;
    }
    else {
        System.out.println("неверный формат email");
        this.email="wrong@err.ru";}
    this.telephone=telephone;
    this.salary=salary;
    if (age>0 && age<100){
    this.age=age;
    }else {
        System.out.println("неверно указан возраст");
        this.age=0;
    }
    }
    public void info(){
        System.out.println(FIO+" "+ post+" "+email+" "+telephone+" "+salary+" "+age);
    }
}
