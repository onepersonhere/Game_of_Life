package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    private static JLabel GLabel = new JLabel();
    private static JLabel ALabel = new JLabel();
    private static JPanel Panel = new JPanel();

    public GameOfLife() {
        setName("GameOfLife");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(516, 579);
        setLayout(null);
        setLocationRelativeTo(null);
        GenerationLabel();
        AliveLabel();
        GamePanel();
        setVisible(true);
    }
    private void GamePanel(){
        Panel = new GamePanel();
        add(Panel);
    }

    private void GenerationLabel(){
        GLabel.setName("GenerationLabel");
        GLabel.setText("Generation #0");
        GLabel.setBounds(5,0,100,20);
        add(GLabel);
    }
    private void AliveLabel(){
        ALabel.setName("AliveLabel");
        ALabel.setText("Alive: 0");
        ALabel.setBounds(5,20,100,20);
        add(ALabel);
    }

    public static JLabel getALabel() {
        return ALabel;
    }

    public static JLabel getGLabel() {
        return GLabel;
    }
}
