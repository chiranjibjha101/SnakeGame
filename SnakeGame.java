import javax.swing.*;
import java.awt.*;

public class SnakeGame {
    JFrame frame;
    SnakeGame(){
        frame=new JFrame("Snake Game");
        frame.setBounds(10,10,905,700);

        GamePanel1 panel=new GamePanel1();
        panel.setBounds(10,10,905,700);
        panel.setBackground(Color.gray);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    public static void main(String[] args){
        SnakeGame snakeGame=new SnakeGame();
    }

}
