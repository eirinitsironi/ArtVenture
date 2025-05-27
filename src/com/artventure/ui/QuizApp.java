package ui;

import javax.swing.*;

public class QuizApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ArtVenture - Quiz");
            frame.setSize(400, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            frame.setContentPane(new QuizMenu(frame));
            frame.setVisible(true);
        });
    }
}