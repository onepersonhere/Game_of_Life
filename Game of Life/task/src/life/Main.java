package life;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        new Universe();
        SwingUtilities.invokeLater(GameOfLife::new);
    }
}
