package life;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;


public class GameOfLife extends JFrame {
    private static JLabel GLabel = new JLabel();
    private static JLabel ALabel = new JLabel();
    private static JPanel Panel = new JPanel();
    private static int M = 0;

    public GameOfLife() {
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(516, 596);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }
    private void initComponents(){
        JPanel countersPanel = new JPanel(new BorderLayout());
        GenerationLabel();
        AliveLabel();
        countersPanel.add(GLabel, BorderLayout.NORTH);
        countersPanel.add(ALabel, BorderLayout.SOUTH);
        add(countersPanel, BorderLayout.NORTH);
        GamePanel();
    }
    private void GamePanel(){

        Panel = new GamePanel();
        add(Panel, BorderLayout.CENTER);
        Timer timer = null;
        Timer finalTimer = timer;
        ActionListener refresh = e -> {
            new Generation();
            M++;
            GLabel.setText("Generation #" + M);
            ALabel.setText("Alive: " + Generation.countAlive());
            GamePanel.setUniverse(Universe.getUniverse());
            repaint();
            setVisible(true);
            if(M == 20){
                assert false;
                finalTimer.setRepeats(false);
            }
        };
        int UPDATE_SPEED = 300;
        timer = new Timer(UPDATE_SPEED, refresh);
        timer.start();
        
    }

    private void GenerationLabel(){
        GLabel.setName("GenerationLabel");
        GLabel.setText("Generation #0");
        GLabel.setBounds(5,0,100,20);
        add(GLabel);
    }
    private void AliveLabel(){
        ALabel.setName("AliveLabel");
        ALabel.setText("Alive: " + Generation.countAlive());
        ALabel.setBounds(5,20,100,20);
        add(ALabel);
    }

    public static JLabel getALabel() {
        return ALabel;
    }

    public static JLabel getGLabel() {
        return GLabel;
    }

    public static JPanel getPanel() {
        return Panel;
    }

    public static void setPanel(JPanel panel) {
        Panel = panel;
    }
}
