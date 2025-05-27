package ui;

import javax.swing.*;
import java.awt.*;

public class MyWrappedPage {

    public static void open() {
        JFrame frame = new JFrame("My Wrapped");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0xD3DFB7));

        Color backgroundColor = new Color(0xD3DFB7);
        Color buttonColor = new Color(0xE6E6FA);
        Color navBarColor = new Color(0xC4D2A4);

        // Top Panel
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 400, 70);
        topPanel.setBackground(backgroundColor);

        JButton backBtn = new JButton("<");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        backBtn.setBounds(10, 20, 50, 30);
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(e -> frame.dispose());
        topPanel.add(backBtn);

        JLabel title = new JLabel("My wrapped", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(0, 20, 400, 30);
        topPanel.add(title);

        frame.add(topPanel);

        // Intro Text
        JLabel introText = new JLabel("<html><center>Welcome to this years My wrapped!<br>" +
                "Click start to see your top paintings, artists and museums this year!</center></html>",
                SwingConstants.CENTER);
        introText.setFont(new Font("Arial", Font.PLAIN, 14));
        introText.setBounds(25, 100, 350, 100);
        frame.add(introText);

        // Start Button
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        startButton.setBounds(120, 230, 150, 50);
        startButton.setBackground(buttonColor);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setOpaque(true);
        startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        frame.add(startButton);

        startButton.addActionListener(e -> {
            // Έλεγχος αν έχει αρκετή δραστηριότητα
            boolean hasActivity = checkUserActivity();

            if (hasActivity) {
                JOptionPane.showMessageDialog(frame, "Generating your wrapped...");
                // συνέχισε με τις υπόλοιπες οθόνες
                // openTopPaintings(); etc.
            } else {
                JOptionPane.showMessageDialog(frame, "You don’t have enough activity to generate your wrapped. Come back later.");
            }
        });

        // Bottom Navigation Bar
        JPanel navBar = new JPanel(new GridLayout(1, 2));
        navBar.setBounds(0, 630, 400, 40);
        navBar.setBackground(navBarColor);

        JButton compassBtn = new JButton("🧭");
        JButton userBtn = new JButton("👤");

        for (JButton btn : new JButton[]{compassBtn, userBtn}) {
            btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(navBarColor);
            btn.setOpaque(true);
            navBar.add(btn);
        }

        frame.add(navBar);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static boolean checkUserActivity() {
        // TODO: Υλοποίηση ελέγχου αν έχει ο χρήστης επαρκή δραστηριότητα
        // Προς το παρόν επιστρέφει τυχαία true για δοκιμή
        return Math.random() > 0.5;
    }
}
