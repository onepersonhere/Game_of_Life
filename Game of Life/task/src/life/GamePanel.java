package life;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static int size = Universe.getUniverse_size();
    private static int[][] universe = Universe.getUniverse();
    public GamePanel(){
        setBounds(0, 40, 500, 500);
        setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        //500w && 500h
        int WIDTH = 500/size;
        int HEIGHT = 500/size;
        for ( int x = 0; x <= 500; x += WIDTH ) {
            for (int y = 0; y <= 500; y += HEIGHT) {
                g2D.drawRect(x, y, WIDTH, HEIGHT);
            }
        }
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(universe[i][j] == 0){
                    g2D.setColor(Color.black);
                    g2D.fillRect(i*WIDTH, j*HEIGHT, WIDTH, HEIGHT);
                }
            }
        }
    }

    public static void setUniverse(int[][] universe) {
        GamePanel.universe = universe;
    }
}
