package ru.geekbrains.lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lesson4 {
    private static char[][] map;

    public static final char DOT_EMPTY = '_';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    //координаты последнего хода игрока
    public static int lastTurnX;
    public static int lastTurnY;
    //счетчик ходов
    public static int counterPlayer;
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;

    public static Scanner scanner;
    public static Random random;
    public static int[] max;


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        max = new int[SIZE * 2 + 2];
        counterPlayer = 0;


        initMap();
        printMap();
        if (whoFirst() == false) {
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
        lastTurnX = x;
        lastTurnY = y;
        counterPlayer++;
        addToArrayMas();
        System.out.println(Arrays.toString(max));
    }
    //ход машины
    private static void turnAI() {
        int x=0,y=0,f=0;
        random = new Random();
        int m =arrayMax();
        do {
            if(counterPlayer<1){
                    x = random.nextInt(SIZE);
                    y = random.nextInt(SIZE);
            }
            else{
                if (m<((max.length/2)-2)){ //добавляем O в строку
                    for (int i = 0; i <SIZE -1; i++) {
                        if(map[m][i]==DOT_EMPTY && map[m][i+1]==DOT_X){
                            y=i;
                            x=m;
                        } else if(map[m][i]==DOT_X && map[m][i+1]==DOT_EMPTY){
                            y=i+1;
                            x=m;
                        }
                    }
                }
                if ((m>=((max.length/2)-2)) && (m<((max.length)-2))){ //добавляем в столбец
                   int m1=m-(max.length/2 - 1);
                    for (int i = 0; i <SIZE -1; i++) {
                        if(map[i][m1]==DOT_EMPTY && map[i+1][m1]==DOT_X){
                            x=i;
                            y=m1;
                        } else if(map[i][m1]==DOT_X && map[i+1][m1]==DOT_EMPTY){
                            x=i+1;
                            y=m1;
                        }
                    }
                }
                if (m==(max.length-2)){ //добавляем в диагональ
                    for (int i = 0; i <SIZE -1; i++) {
                        if(map[i][i]==DOT_EMPTY && map[i+1][i+1]==DOT_X){
                            x=i;
                            y=i;
                        } else if(map[i][i]==DOT_X && map[i+1][i+1]==DOT_EMPTY){
                            x=i+1;
                            y=i+1;
                        }
                    }
                }
                else if (m==max.length-1){ //добавляем в вторую диагональ
                    for (int i = 0; i>SIZE-1; i--) {
                        if(map[f][i]==DOT_EMPTY && map[f+1][i-1]==DOT_X){
                            x=f;
                            y=i;
                        } else if(map[f][i]==DOT_X && map[f+1][i-1]==DOT_EMPTY){
                            x=f+1;
                            y=i-1;
                        }
                        f++;
                    }
                }
            }

        } while(!checkTurnAI(x, y));
    System.out.println("Компьютер походил в точку "+(x +1)+" "+(y +1));
    map[x][y]=DOT_O;
    clearArrayMas();
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
    private static boolean checkWin(char symb) {
        int checkColumn = 0;
        int checkLine = 0;
        int checkDiag1 = 0;
        int checkDiag2 = 0;
        int y = SIZE - 1;
        for (int i = 0; i < SIZE; i++) {                //Идем по элементам массива
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symb) {                //если найден массив с необходимым знаком
                    //проверка столбца
                    for (int k = 0; k < SIZE; k++) {    //проверяем весь столбик на наличие этого знака
                        if (map[i][k] == symb) {
                            checkColumn++;
                            if (checkColumn == DOTS_TO_WIN) { // если колличество элементов
                                                                // совпало с количеством необходимым для победы
                                return true;
                            }
                        } else checkColumn = 0;
                    }
                    checkColumn = 0;
                    //проверка строки
                    for (int k = 0; k < SIZE; k++) {
                        if (map[k][i] == symb) {
                            checkLine++;
                            if (checkLine == DOTS_TO_WIN) {
                                return true;
                            }
                        } else checkLine = 0;
                    }
                    checkLine = 0;
                }
            }
            //проверка диагоналей
            if (map[i][i] == symb){
                checkDiag1++;
            }
            else if (map[i][i]!=symb){
                checkDiag1--;
            }
            if (checkDiag1 == DOTS_TO_WIN) {
                return true;
            }

            if (map[i][y] == symb) {
                checkDiag2++;
                if (checkDiag2 == DOTS_TO_WIN) {
                    return true;
                }
                y--;
            }
        }
        return false;
    }
    //кто ходит первый
    private static boolean whoFirst() {
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
    //наполнение массива весов(вычисляем после хода игрока)
    public static void addToArrayMas(){
        int j3 = SIZE - 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_X) {
                    max[i] ++;
                } else if(map[i][j]==DOT_O){
                    max[i] --;
                }

                if (map[j][i] == DOT_X) {
                    max[i+SIZE] ++;
                }else if(map[j][i]== DOT_O){
                    max[i+SIZE] --;
                }
            }
            if (map[i][i] == DOT_X) {
                max[max.length - 2] ++;
            }else if(map[i][i] == DOT_O){
                max[max.length - 2] --;
            }
            if (map[i][j3] == DOT_X) {
                max[max.length - 1] ++;
            }else if(map[i][j3] == DOT_O){
                max[max.length - 1] --;
            }
            j3--;
        }
    }
    //очистка массива весов(будем очищать после хода AI)
    public static void clearArrayMas(){
        for (int i = 0; i <max.length ; i++) {
            max[i]=0;
        }
    }
    //идекс максимального элемента мамассива весов
    private static int arrayMax(){
        int index1=0,index2=0,index=0;
        int maximum = max[0];
        for (int i = 1; i <max.length; i++) {
            if(maximum<=max[i]){
                maximum = max[i];
                index1 = i;
            }
        }
        for (int i = max.length-1; i >=0; i--) {
            if(maximum<=max[i]){
                maximum = max[i];
                index2 = i;
            }
        }
        if (index1==index2){
        return index1;
        }
        else if(Math.round(Math.random() * 7)>3){
                index=index1;
            }else{
                index=index2;
            }
            if(indexLineIsFull(index)==false){
                index1=index2=0;
                arrayMax();
            }
        return index;
    }
    //Проверка заполнености выбраной строки
    private static boolean indexLineIsFull(int index){
        //проверка столбца
        int count =0;
        if(index<((max.length/2)-1)){
            for (int i = 0; i < SIZE; i++) {
                if (map[index][i]==DOT_EMPTY){
                    return true;
                }
            }
        }
        if ((index>=((max.length/2)-1)) && (index<((max.length)-2))) { //добавляем в столбец
            for (int i = 0; i < SIZE; i++) {
                if (map[i][index-(max.length/2 - 1)]==DOT_EMPTY){
                    return true;
                }
            }
        }
        if ((index==((max.length)-2))) { //добавляем в столбец
            for (int i = 0; i < SIZE; i++) {
                if (map[i][i]==DOT_EMPTY){
                    return true;
                }
            }
        }
        if ((index==((max.length)-1))) { //добавляем в столбец
            for (int i = SIZE-1; i >0; i--) {
                if (map[count][i]==DOT_EMPTY){
                    return true;
                }
                count++;
            }
        }
        max[index]=-100;
        System.out.println(Arrays.toString(max));
        return false;
    }
}
