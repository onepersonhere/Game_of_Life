package life;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameOfLife extends JFrame {
    private static JLabel GLabel = new JLabel();
    private static JLabel ALabel = new JLabel();
    private static JPanel Panel = new JPanel();
    private static JToggleButton PlayButton = new JToggleButton("Play/Pause");
    private static JButton ResetButton = new JButton("Reset");
    private static JButton NewButton = new JButton("New");
    private static JSlider slider = new JSlider();
    Timer timer = null;
    int UPDATE_SPEED = 300;
    private static int clicks = 0;
    private static int M = 0;

    public GameOfLife() {
        new Universe();
        setTitle("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 596);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }
    private void initComponents(){
        JPanel countersPanel = new JPanel(new FlowLayout());
        JPanel LabelPanel = new JPanel(new BorderLayout(5,5));
        JPanel ButtonPanel = new JPanel(new BorderLayout(5,2));
        Buttons();
        GenerationLabel();
        AliveLabel();
        Slider();
        ButtonPanel.add(NewButton, BorderLayout.NORTH);
        ButtonPanel.add(PlayButton, BorderLayout.CENTER);
        ButtonPanel.add(ResetButton, BorderLayout.SOUTH);
        LabelPanel.add(GLabel, BorderLayout.NORTH);
        LabelPanel.add(ALabel, BorderLayout.SOUTH);

        countersPanel.add(LabelPanel);
        countersPanel.add(ButtonPanel);
        countersPanel.add(new JLabel("Speed:"));
        countersPanel.add(slider);
        countersPanel.setPreferredSize(new Dimension(100, 596));
        add(countersPanel, BorderLayout.WEST);
        GamePanel();
    }
    private void GamePanel(){

        Panel = new GamePanel();
        add(Panel, BorderLayout.CENTER);

    }
    private void Slider(){
        slider.setPreferredSize(new Dimension(100,370));
        slider.setOrientation(JSlider.VERTICAL);
        slider.setMinimum(1);
        slider.setMaximum(300);
        slider.setInverted(true);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(100);
        slider.setPaintTicks(true);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                UPDATE_SPEED = ((JSlider)changeEvent.getSource()).getValue();
            }
        });
    }
    private void Buttons(){

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
        NewButton.setFocusPainted(false);

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

        NewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new Universe();
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
        GLabel.setPreferredSize(new Dimension(100, 30));
        add(GLabel);
    }
    private void AliveLabel(){
        ALabel.setName("AliveLabel");
        ALabel.setText("Alive: " + Generation.countAlive());
        ALabel.setPreferredSize(new Dimension(100, 30));
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
