package main;

import javax.swing.JFrame;
import frontend.Gameplay;

public class Marco extends JFrame {

    Gameplay gamePlay;

    public Marco() {
        gamePlay = new Gameplay();
        setBounds(10, 10, 700, 600);
        setTitle("Proyecto SO1");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePlay);
        setVisible(true);
    }

    public static void main(String[] args) {
        Marco marco = new Marco();
    }

}
