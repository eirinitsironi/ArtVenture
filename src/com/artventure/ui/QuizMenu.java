package ui;

import javax.swing.*;
import java.awt.*;

public class QuizMenu extends JPanel {

    public QuizMenu(JFrame frame) {
        setLayout(null);
        Color backgroundColor = new Color(0xD3DFB7);
        Color iconColor = new Color(0xC4D2A4);
        setBackground(backgroundColor);

        // === Button Sizes & Position ===
        int buttonWidth = 200;
        int buttonHeight = 50;
        int spacing = 30;

        int centerX = (400 - buttonWidth) / 2;
        int totalHeight = 2 * buttonHeight + spacing;
        int startY = (600 - totalHeight) / 2;

        // === Buttons ===
        JButton startQuizButton = new JButton("Start Quiz");
        startQuizButton.setBounds(centerX, startY, buttonWidth, buttonHeight);
        startQuizButton.setBackground(iconColor);
        startQuizButton.setForeground(Color.BLACK);
        startQuizButton.setFont(new Font("Arial", Font.BOLD, 14));
        startQuizButton.setUI(new RoundedButtonUI());
        startQuizButton.setFocusPainted(false);

        // Όταν πατάμε Start Quiz, δείχνει την οθόνη QuizScreen
        startQuizButton.addActionListener(ignored -> {
            frame.setContentPane(new QuizScreen(frame));
            frame.revalidate();
        });

        JButton previousQuizButton = new JButton("Your previous Quizzes");
        previousQuizButton.setBounds(centerX, startY + buttonHeight + spacing, buttonWidth, buttonHeight);
        previousQuizButton.setBackground(iconColor);
        previousQuizButton.setForeground(Color.BLACK);
        previousQuizButton.setFont(new Font("Arial", Font.BOLD, 12));
        previousQuizButton.setUI(new RoundedButtonUI());
        previousQuizButton.setFocusPainted(false);

        // Όταν πατάμε Your previous Quizzes, δείχνει την οθόνη QuizList
        previousQuizButton.addActionListener(ignored -> {
            frame.setContentPane(new QuizList(frame));
            frame.revalidate();
            frame.repaint();
        });

        add(startQuizButton);
        add(previousQuizButton);

        // === Bottom Navigation Bar ===
        JPanel navBar = new JPanel();
        navBar.setLayout(new GridLayout(1, 2));
        navBar.setBounds(0, 630, 400, 40);
        navBar.setBackground(iconColor);

        JButton homeButton = new JButton("🧭");
        JButton profileButton = new JButton("👤");

        JButton[] navButtons = {homeButton, profileButton};
        for (JButton btn : navButtons) {
            btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(iconColor);
            btn.setOpaque(true);
            navBar.add(btn);
        }

        add(navBar);
    }
}
