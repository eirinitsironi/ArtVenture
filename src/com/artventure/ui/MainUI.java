package ui;

import javax.swing.*;

public class MainUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Profile");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 700);
            frame.setContentPane(new UserProfile());
            frame.setVisible(true);
        });
    }
}