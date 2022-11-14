import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.Random;

public class GamePanel1 extends JPanel implements KeyListener, ActionListener {
    //Images for game//
    ImageIcon snakeTitle=new ImageIcon(getClass().getResource("snaketitle.jpg"));
    ImageIcon down=new ImageIcon(getClass().getResource("downmouth.png"));
    ImageIcon up=new ImageIcon(getClass().getResource("upmouth.png"));
    ImageIcon left=new ImageIcon(getClass().getResource("leftmouth.png"));
    ImageIcon right=new ImageIcon(getClass().getResource("rightmouth.png"));
    ImageIcon extend=new ImageIcon(getClass().getResource("snakeimage.png"));
    ImageIcon enemy=new ImageIcon(getClass().getResource("enemy.png"));
    // to locate inside the game
    int[] lengthXaxis=new int[750];
    int[] lengthYaxis =new int[750];
    boolean leftturn=false;
    boolean rightturn=true;
    boolean upturn=false;
    boolean downturn=false;
    int move=0;
    int lengthofSnake=3;
    boolean gameover=false;
    int delay=150;
    int[] xposEnemy={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    int[] yposEnemy={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    int enemyX=150,enemyY=200;
    Random random=new Random();
    Timer time;
    int score=0;
    GamePanel1(){
        addKeyListener(this);
        setFocusable(true);
        time=new Timer(delay,this);
        time.start();
    }
    // logic for any type of interaction
    public  void newEnemy(){
        enemyX=xposEnemy[random.nextInt(34)];
        enemyY=yposEnemy[random.nextInt(23)];
        for(int i=lengthofSnake-1;i>=0;i--){
            if(lengthXaxis[i]==enemyX && lengthYaxis[i]==enemyY){
                newEnemy();
            }
        }
    }
    public void collideWithEnemy() {
        if (lengthXaxis[0] == enemyX && lengthYaxis[0] == enemyY) {
            newEnemy();
            score++;
            lengthofSnake++;
            lengthXaxis[lengthofSnake - 1] = lengthXaxis[lengthofSnake - 2];
            lengthYaxis[lengthofSnake - 1] = lengthYaxis[lengthofSnake - 2];
        }
    }
    public void colldeWithBody(){
        for (int i=lengthofSnake-1;i>0;i--){
            if(lengthXaxis[i]==lengthXaxis[0] && lengthYaxis[i]==lengthYaxis[0]){
                time.stop();
                gameover=true;
            }
        }
    }
    @Override
    //paint function to create objects on screen;
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.drawRect(24,10,851,55);
        g.drawRect(24,74,851,576);
        //title part
        snakeTitle.paintIcon(this,g,25,11);
        //game zone
        g.setColor(Color.BLACK);
        g.fillRect(25,75,851,576);
        //game logic for beginning of the game
        if(move==0){
            lengthXaxis[0]=100;
            lengthXaxis[1]=75;
            lengthXaxis[2]=50;

            lengthYaxis[0]=100;
            lengthYaxis[1]=100;
            lengthYaxis[2]=100;
            move++;
        }
        if(leftturn){
            left.paintIcon(this,g,lengthXaxis[0],lengthYaxis[0]);
        }
        if(rightturn){
            right.paintIcon(this,g,lengthXaxis[0],lengthYaxis[0]);
        }
        if(upturn){
            up.paintIcon(this,g,lengthXaxis[0],lengthYaxis[0]);
        }
        if(downturn){
            down.paintIcon(this,g,lengthXaxis[0],lengthYaxis[0]);
        }
        for(int i=1;i<lengthofSnake;i++){
            extend.paintIcon(this,g,lengthXaxis[i],lengthYaxis[i]);
        }
        enemy.paintIcon(this,g,enemyX,enemyY);
        if(gameover==true){
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
            g.drawString("Game Over",350,300);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,10));
            g.drawString("Press Space Key to Restart",370,360);

        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN,15));
        g.drawString("Score :"+score,750,30);
        g.drawString("Length :"+lengthofSnake,750,50);
        g.dispose();

    }
//key function assignment
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE && gameover){
            restart();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT && (!rightturn)){
            leftturn=true;
            rightturn=false;
            upturn=false;
            downturn=false;
            move++;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!leftturn)){
            leftturn=false;
            rightturn=true;
            upturn=false;
            downturn=false;
            move++;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN && (!upturn)){
            leftturn=false;
            rightturn=false;
            upturn=false;
            downturn=true;
            move++;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP &&(!downturn)){
            leftturn=false;
            rightturn=false;
            upturn=true;
            downturn=false;
            move++;
        }
    }

    private void restart() {
        gameover=false;
        lengthofSnake=3;
        move=0;
        leftturn=false;
        rightturn=true;
        downturn=false;
        upturn=false;
        score=0;
        time.start();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    // action performed by pressing keys

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=lengthofSnake-1;i>0;i--){
            lengthXaxis[i]=lengthXaxis[i-1];
            lengthYaxis[i]=lengthYaxis[i-1];
        }
        if(leftturn){
            lengthXaxis[0]=lengthXaxis[0]-25;
        }
        if(rightturn){
            lengthXaxis[0]=lengthXaxis[0]+25;
        }
        if(downturn){
            lengthYaxis[0]=lengthYaxis[0]+25;
        }
        if(upturn){
            lengthYaxis[0]=lengthYaxis[0]-25;
        }
        if(lengthXaxis[0]>850) lengthXaxis[0]=25;
        if(lengthXaxis[0]<25) lengthXaxis[0]=850;
        if(lengthYaxis[0]>625) lengthYaxis[0]=75;
        if(lengthYaxis[0]<75) lengthYaxis[0]=625;
        collideWithEnemy();
        colldeWithBody();
        repaint();
    }
}
