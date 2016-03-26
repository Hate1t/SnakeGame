package ua.kovtunovych;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JPanel implements ActionListener {

    public static final int SCALE = 32;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public static final int SPEED = 10;

    public Apple a = new Apple(
         (int) (Math.random()*(WIDTH-1)),
         (int) (Math.random()*(HEIGHT-2)));
    public Snake s = new Snake(10, 10, 9, 10);
    public Timer t = new Timer(1000/SPEED, this);

    public SnakeGame() {
        t.start();
        addKeyListener(new Keyboard());
        setFocusable(true);
    }

    public void paint(Graphics g) {
        g.setColor(color(5, 50, 10));
        g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);

        g.setColor(color(255, 216, 0));
        for(int xx = 0; xx <= WIDTH*SCALE; xx += SCALE) {
            g.drawLine(xx, 0, xx, WIDTH*SCALE);
        }

        for (int yy = 0; yy <= HEIGHT*SCALE; yy += SCALE) {
            g.drawLine(0, yy, HEIGHT*SCALE, yy);
        }

        g.setColor(color(20, 30, 150));
        for(int d = 0; d < s.length; d++) {
            g.fillRect(s.snakeX[d]*SCALE+1, s.snakeY[d]*SCALE+1, SCALE-1, SCALE-1);
        }

        g.setColor(color(255, 0, 0));
        g.fillRect(a.posX*SCALE+1, a.posY*SCALE+1, SCALE-1, SCALE-1);
    }

    public Color color(int red, int green, int blue) {
        return new Color(red, green, blue);
    }

    public static void main(String[] args){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(WIDTH * SCALE, HEIGHT * SCALE+5);
        f.setLocationRelativeTo(null);
        f.add(new SnakeGame());
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        s.move();

        if(s.snakeX[0] == a.posX & s.snakeY[0] == a.posY) {
            if( s.length + 1 == 60 ) {
                JOptionPane.showMessageDialog(null, "Победа!");
                System.exit(0);
            }
            a.setRandomPosition();
            s.length++;
        }

        for(int k = 1; k < s.length; k++) {
            if(s.snakeX[k] == a.posX & s.snakeY[k] == a.posY) {
                a.setRandomPosition();
            }
        }

        repaint();
    }


    private class Keyboard extends KeyAdapter {
        public void keyPressed(KeyEvent kEvt) {
            int key = kEvt.getKeyCode();

            if( (key == KeyEvent.VK_RIGHT) & !s.direction.equals(Direction.LEFT) ) {
                s.direction = Direction.RIGHT;
            }
            if( (key == KeyEvent.VK_DOWN) & !s.direction.equals(Direction.UP) ) {
                s.direction = Direction.DOWN;
            }
            if( (key == KeyEvent.VK_LEFT) & !s.direction.equals(Direction.RIGHT) )  {
                    s.direction = Direction.LEFT;
            }
            if( (key == KeyEvent.VK_UP) & !s.direction.equals(Direction.DOWN) ) {
                s.direction = Direction.UP;
            }
        }
    }
}