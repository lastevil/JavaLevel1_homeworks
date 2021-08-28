package ru.homeworks.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyWindow extends JFrame {
    public static int numwin;
    public static int clickcheck;

    public MyWindow() {
        clickcheck=0;
        numwin = (int)(Math.random()*10)+1;
        setTitle("Guess number");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 800, 130);
        //setResizable(false);
        JTextField tField = new JTextField("Добро пожаловать в игру, задано число от 1 до 10 у вас 3 попытки его угадать");
        Font font =new Font("Arial",Font.PLAIN,20);
        Font fontNG =new Font("Arial",Font.PLAIN,10);
        JPanel panel = new JPanel(new GridLayout(1,11));
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
                panel.add(button);
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
                if (number<11 && clickcheck<3){
                    clickcheck++;
                    if (number==numwin){
                        tField.setText("Вы угадали число, загаданое число: "+numwin);
                    }else if(number>numwin){
                        tField.setText("Не верно, загаданное число меньше");
                    } else {
                        tField.setText("Не верно, загаданное число больше");
                    }

                //tField.setText(String.valueOf(number));
                }
                else if (number==11){
                    numwin = (int)(Math.random()*10)+1;
                    //System.out.println(numwin);
                    clickcheck=0;
                    tField.setText("загадано новое число, игра начинается заного");
                }else if (clickcheck>=3){
                    tField.setText("Вы проиграли... начните заного");
                }
            }
        });
    }
}
