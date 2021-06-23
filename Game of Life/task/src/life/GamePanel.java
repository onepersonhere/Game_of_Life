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
    public void paint(Graphics g){
        super.paint(g);
        //500w && 500h
        int WIDTH = 500/size;
        int HEIGHT = 500/size;
        for ( int x = 0; x <= 500; x += WIDTH ) {
            for (int y = 0; y <= 500; y += HEIGHT) {
                g.drawRect(x, y, WIDTH, HEIGHT);
            }
        }
    }
}
