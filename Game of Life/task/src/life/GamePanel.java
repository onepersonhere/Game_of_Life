package life;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static int size = Universe.getUniverse_size();
    private static int[][] universe = Universe.getUniverse();
    int w = 1000;
    int h = 1000;
    public GamePanel(){
        setBounds(0, 40, w, h);
        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        //500w && 500h
        int WIDTH = w/size;
        int HEIGHT = h/size;
        //generate grid
        /*
        for ( int x = 0; x <= w; x += WIDTH ) {
            for (int y = 0; y <= h; y += HEIGHT) {
                g2D.drawRect(x, y, WIDTH, HEIGHT);
            }
        }*/
        //generate boxes
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(universe[i][j] == 0){
                    g2D.setColor(Color.black);
                    g2D.fillRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);
                }
            }
        }
    }

    public static void setSize(int size) {
        GamePanel.size = size;
    }

    public static void setUniverse(int[][] universe) {
        GamePanel.universe = universe;
    }
}
