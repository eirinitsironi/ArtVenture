package com.artventure.ui;
import javax.swing.*;
import java.awt.*;

public class ArtistProfilePage {
    public static void open(String artistName) {
        JFrame frame = new JFrame("Artist Profile");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0xD3DFB7));

        JLabel nameLabel = new JLabel(artistName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBounds(0, 20, 400, 30);
        frame.add(nameLabel);

        String[] sections = {"Posts", "Visit history", "Wishlist", "Reviews"};
        for (int i = 0; i < sections.length; i++) {
            JButton btn = new JButton(sections[i] + " >");
            btn.setBounds(20, 80 + i * 60, 360, 40);
            btn.setBackground(new Color(0xE6E6FA));
            btn.setFocusPainted(false);
            frame.add(btn);
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
