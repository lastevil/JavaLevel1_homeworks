package ru.geekbrains.lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    private static char[][] map;

    public static final char DOT_EMPTY = '_';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    //Массивы весов строк где 'X' = +1, 'O' = -1
    public static int[] columns;
    public static int[] lines;
    public static int[] mainDiagonals;
    public static int[] secondDiagonals;
    //счетчик ходов
    public static int counterPlayer;
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;

    public static Scanner scanner;
    public static Random random;



    public static void main(String[] args) {

        mainDiagonals = new int[1+(SIZE-DOTS_TO_WIN)*2];
        secondDiagonals = new int[1+(SIZE-DOTS_TO_WIN)*2];
        columns = new int[SIZE];
        lines = new  int[SIZE];
        scanner = new Scanner(System.in);
        counterPlayer = 0;

        initMap();
        printMap();
        if (firstTurn() == false) {
            turnAI();
            printMap();
        }
        while (true) {
            turnPlayer();
            printMap();
            if (checkWin(DOT_X) == true) {
                System.out.println("Вы выйграли!");
                break;
            }
            if (isMapFull() == true) {
                System.out.println("Ничья!");
                break;
            }
            turnAI();
            printMap();
            if (checkWin(DOT_O) == true) {
                System.out.println("Вы проиграли...");
                break;
            }
            if (isMapFull() == true) {
                System.out.println("Ничья!");
                break;
            }
        }


    }
    //создание поля размеров SIZExSIZE
    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    //Вывод в консоль поля
    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    //ход игрока
    private static void turnPlayer() {
        int x, y;
        do {
            System.out.println("Введите координаты хода:");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!checkTurn(x, y));
        map[x][y] = DOT_X;
        counterPlayer++;
        makeArraysWeight();
    }
    //ход машины
    private static void turnAI() {
        int x=0,y=0;
        random = new Random();
        do {
                    x = random.nextInt(SIZE);
                    y = random.nextInt(SIZE);
        } while(!checkTurnAI(x, y));
    System.out.println("Компьютер походил в точку "+(x +1)+" "+(y +1));
    map[x][y]=DOT_O;
        clearArraysWeight();
    }
    //проверка хода
    private static boolean checkTurn(int x, int y) {
        if (x < 0 | y < 0 | x >= SIZE | y >= SIZE) {
            System.out.println("Неверные координаты.");
            return false;
        }
        if (map[x][y] == DOT_EMPTY) {
            return true;
        }
        System.out.println("Клетка занята.");
        return false;
    }
    //проверка хода компьютера (без вывода ошибок ввода)
    private static boolean checkTurnAI(int x, int y) {
        if (x < 0 | y < 0 | x >= SIZE | y >= SIZE) {
            return false;
        }
        if (map[x][y] == DOT_EMPTY) {
            return true;
        }
        return false;
    }
    //проверка победы
    private static boolean checkWin(char symbol) {
       if (checkColumn(symbol,DOTS_TO_WIN)==true){
           return true;
       } else if (checkLine(symbol,DOTS_TO_WIN)==true){
           return true;
       } else if (checkMainDiagonals(symbol,(SIZE-DOTS_TO_WIN),DOTS_TO_WIN)==true){
           return true;
       } else if (checkSecondDiagonals(symbol,(SIZE-DOTS_TO_WIN),DOTS_TO_WIN)==true) {
           return true;
       }
        return false;
    }
    //кто ходит первый
    private static boolean firstTurn() {
        int x = (int) Math.round(Math.random() * 10);
        int y = (int) Math.round(Math.random() * 10);
        if (x < y) {
            System.out.println("Первый ход компьютера");
            return false;
        }
        System.out.println("Вы ходите первым");
        return true;
    }
    //проверка на ничью
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    //проверка столбцов
    private static boolean checkColumn(char symbol, int num){
        int check = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i]==symbol){
                    check++;
                }else{
                    check=0;
                }
            }
            if (check==num){
                return true;
            } else {
                check=0;
            }
        }
        return false;
    }
    //проверка строк
    private static boolean checkLine(char symbol, int num){
        int check = 0;
        for (int i = 0; i < SIZE; i++) {                //Идем по элементам массива
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j]==symbol){
                    check++;
                }else{
                    check--;
                }
            }
            if (check==num){
                return true;
            } else {
                check=0;
            }
        }
        return false;
    }
    //проверка диагоналей \
    private static boolean checkMainDiagonals(char symbol, int arg, int num){
        int check1=0;
        int check2=0;
        int a=arg;
        for(int j =0;j<=arg;j++){
            for (int i = 0; i < SIZE-j; i++) {
                if (map[i][i+j]==symbol){
                    check1++;
                } else {
                    check1=0;
                }
                if (map[i+j][i]==symbol){
                    check2++;
                } else {
                    check2=0;
                }
            }
            if (check1==num || check2==num){
                return true;
            } else {
                check1=0;
                check2=0;
            }
        }
        return false;
    }
    //проверка диагоналей /
    private static boolean checkSecondDiagonals(char symbol, int arg, int num) {
        int check1 = 0;
        int check2 = 0;
        int column = SIZE - 1;
        int a = arg;
        for (int j = 0; j <= arg; j++) {
            for (int i = 0; i < SIZE - j; i++) {
                if (map[i][column - j] == symbol) {
                    check1++;
                } else {
                    check1 = 0;
                }
                if (map[i + j][column] == symbol) {
                    check2++;
                } else {
                    check2 = 0;
                }
                column--;
            }
            column = SIZE - 1;
            if (check1 == num || check2 == num) {
                return true;
            } else {
                check1 = 0;
                check2 = 0;
            }
        }
        return false;
    }
    //наполняем весы для столбцов
    private static void makeArraysWeight(){
        addArraysColumns(true);
        addArraysLines(true);
        addArraysMainDiagonals(true);
        addArraysSecondDiagonals(true);

        System.out.println("Массив столбцов: "+Arrays.toString(columns));
        System.out.println("Массив строк: "+Arrays.toString(lines));
        System.out.println("Массив главных диагоналей: "+Arrays.toString(mainDiagonals));
        System.out.println("Массив побочных диагоналей: "+Arrays.toString(secondDiagonals));
    }
    //очистка весов
    private static void clearArraysWeight(){
        addArraysColumns(false);
        addArraysLines(false);
        addArraysMainDiagonals(false);
        addArraysSecondDiagonals(false);
    }
    private static void addArraysColumns(boolean clear){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[j][i]==DOT_X){
                    columns[i]++;
                } else if (map[j][i]==DOT_O){
                    columns[i]--;
                }
                if(clear == false){
                    columns[i]=0;
                }
            }

        }
    }
    private static void addArraysLines(boolean clear){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j]==DOT_X){
                    lines[i]++;
                } else if (map[i][j]==DOT_O){
                    lines[i]--;
                }
                if (clear == false){
                    lines[i]=0;
                }
            }

        }
    }
    private static void addArraysMainDiagonals(boolean clear){
        int a=SIZE-DOTS_TO_WIN;
        for (int i = 0; i <=a; i++) {
            for (int j = 0; j < SIZE-i; j++) {
                if(map[j][j+i]==DOT_X){
                    mainDiagonals[i]++;
                }else if(map[j][j+i]==DOT_O){
                    mainDiagonals[i]--;
                }
                if (clear == false){
                    mainDiagonals[i]=0;
                }
            }
        }
        for (int i = 1; i <=a ; i++) {
            for (int j = 0; j < SIZE-i; j++) {
                if (map[j+i][j]==DOT_X){
                    mainDiagonals[a+i]++;
                }else if(map[j+i][j]==DOT_O){
                    mainDiagonals[a+i]--;
                }
                if (clear == false){
                    mainDiagonals[a+i]=0;
                }
            }

        }

    }
    private static void addArraysSecondDiagonals(boolean clear){
        int a=SIZE-DOTS_TO_WIN;
        int column = SIZE - 1;
        for (int i = 0; i <=a; i++) {
            for (int j = 0; j < SIZE-i; j++) {
                if(map[j][column - i]==DOT_X){
                    secondDiagonals[i]++;
                }else if(map[j][column - i]==DOT_O){
                    secondDiagonals[i]--;
                } if(clear==false){
                    secondDiagonals[i]=0;
                }
                column--;
            }
            column = SIZE - 1;
        }
        column = SIZE - 1;
        for (int i = 1; i <=a ; i++) {
            for (int j = 0; j < SIZE-i; j++) {
                if (map[j+i][column]==DOT_X){
                    secondDiagonals[a+i]++;
                }else if(map[j+i][column]==DOT_O){
                    secondDiagonals[a+i]--;
                } if(clear==false){
                    secondDiagonals[a+i]=0;
                }
                column--;
            }
            column = SIZE - 1;
        }
    }

}
