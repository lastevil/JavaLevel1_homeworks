package ru.homeworks.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyWindow extends JFrame {
    public static int numWin;
    public static int clickCheck;
    public static boolean youWin;

    public MyWindow() {
        clickCheck=0;
        youWin=false;
        numWin = (int)(Math.random()*10)+1;
        setTitle("Guess number");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 650, 130);
        //setResizable(false);
        JTextField tField = new JTextField("Добро пожаловать в игру, задано число от 1 до 10 у вас 3 попытки его угадать");
        Font font =new Font("Arial",Font.PLAIN,20);
        Font fontNG =new Font("Arial",Font.PLAIN,15);
        tField.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel panel = new JPanel(new GridLayout(1,10));
        tField.setFont(font);
        add(panel);
        add(tField, BorderLayout.NORTH);
        //создание кнопок
        //System.out.println(numwin);
        for (int i = 1; i <= 11; i++) {
            if (i<11){
            JButton button = new JButton(String.valueOf(i));
            button.setFont(font);
            panel.add(button);
            buttonClick(button,tField,i);
            }else{
                JButton button = new JButton("New Game");
                button.setFont(fontNG);
                add(button,BorderLayout.SOUTH);
                buttonClick(button,tField,i);
            }
        }
        setVisible(true);
    }

    //METHODS
    private void buttonClick(JButton button,JTextField tField, int number){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!youWin && number!=11){
                if (number<11 && clickCheck<3){
                    clickCheck++;
                    if (number==numWin){
                        tField.setText("Вы угадали число, загаданое число: "+numWin);
                        youWin=true;
                    }else if(number>numWin){
                        tField.setText("Не верно, загаданное число меньше");
                    } else {
                        tField.setText("Не верно, загаданное число больше");
                    }

                //tField.setText(String.valueOf(number));
                }
                else if (clickCheck>=3){
                    tField.setText("Вы проиграли... было загадано "+numWin+", начните заного");
                }
            }
                else if (number==11) {
                    numWin = (int) (Math.random() * 10) + 1;
                    //System.out.println(numwin);
                    clickCheck = 0;
                    youWin = false;
                    tField.setText("загадано новое число, игра начинается заного");
                }
                else{
                    tField.setText("Число угадано, начните новую игру");
                }
            }
        });
    }
}
