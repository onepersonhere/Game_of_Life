package life;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameOfLife extends JFrame {
    private static JLabel GLabel = new JLabel();
    private static JLabel ALabel = new JLabel();
    private static JPanel Panel = new JPanel();
    private static JToggleButton PlayButton = new JToggleButton("Play/Pause");
    private static JButton ResetButton = new JButton("Reset");
    Timer timer = null;
    private static int clicks = 0;
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
        JPanel LabelPanel = new JPanel(new BorderLayout());
        JPanel ButtonPanel = new JPanel(new BorderLayout(5,2));
        Buttons();
        GenerationLabel();
        AliveLabel();
        ButtonPanel.add(PlayButton, BorderLayout.WEST);
        ButtonPanel.add(ResetButton, BorderLayout.EAST);
        LabelPanel.add(GLabel, BorderLayout.NORTH);
        LabelPanel.add(ALabel, BorderLayout.SOUTH);
        countersPanel.add(LabelPanel, BorderLayout.WEST);
        countersPanel.add(ButtonPanel, BorderLayout.EAST);
        add(countersPanel, BorderLayout.NORTH);
        GamePanel();
    }
    private void GamePanel(){

        Panel = new GamePanel();
        add(Panel, BorderLayout.CENTER);

    }
    private void Buttons(){
        int UPDATE_SPEED = 300;
        ActionListener refresh = e -> {
            new Generation();
            M++;
            GLabel.setText("Generation #" + M);
            ALabel.setText("Alive: " + Generation.countAlive());
            GamePanel.setUniverse(Universe.getUniverse());
            repaint();
            setVisible(true);
        };

        PlayButton.setName("PlayToggleButton");
        PlayButton.setFocusPainted(false);
        ResetButton.setName("ResetButton");
        ResetButton.setFocusPainted(false);

        PlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(clicks % 2 == 0) {
                    timer = new Timer(UPDATE_SPEED, refresh);
                    timer.start();
                }else{
                    timer.stop();
                }
                clicks++;
            }
        });

        ResetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GamePanel.setUniverse(Universe.getOriginalUniverse());
                Generation.setUniverse(Universe.getOriginalUniverse());
                GLabel.setText("Generation #0");
                ALabel.setText("Alive: " + Generation.countAlive());
                repaint();
                M = 0;
            }
        });

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
