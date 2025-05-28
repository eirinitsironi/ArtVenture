package ui;

import javax.swing.*;
import java.awt.*;

public class QuizScreen extends JPanel {
    public QuizScreen(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#D3DFB7"));

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.decode("#D3DFB7"));

        JButton leave = new JButton("leave quiz");
        JLabel count = new JLabel("1/X", SwingConstants.RIGHT);

        leave.setUI(new RoundedButtonUI());
        leave.setBackground(Color.decode("#C4D2A4"));
        configureTopButton(leave);
        count.setFont(new Font("Arial", Font.PLAIN, 16));
        count.setOpaque(true);
        count.setBackground(Color.decode("#C4D2A4"));
        count.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        leave.addActionListener(ignored -> {
            frame.setContentPane(new QuizMenu(frame));
            frame.revalidate();
        });

        topBar.add(leave, BorderLayout.WEST);
        topBar.add(count, BorderLayout.EAST);

        JLabel question = new JLabel("Question 1", SwingConstants.CENTER);
        question.setFont(new Font("Arial", Font.BOLD, 18));
        question.setOpaque(true);
        question.setBackground(Color.decode("#D3DFB7"));
        question.setPreferredSize(new Dimension(400, 100));

        JPanel answers = new JPanel(new GridLayout(2, 2, 0, 0));
        answers.setBackground(Color.decode("#D3DFB7"));
        answers.setPreferredSize(new Dimension(answers.getWidth(), 5 * 50)); // 5 φορές 50 pixels ύψος


        answers.add(createAnswerButton("Answer 1", "#F4CBA4"));
        answers.add(createAnswerButton("Answer 2", "#D0EACF"));
        answers.add(createAnswerButton("Answer 3", "#DCC3F7"));
        answers.add(createAnswerButton("Answer 4", "#F7F0C3"));

        add(topBar, BorderLayout.NORTH);
        add(question, BorderLayout.CENTER);
        add(answers, BorderLayout.SOUTH);
    }

    private JButton createAnswerButton(String text, String colorHex) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.decode(colorHex));
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setFocusPainted(false);
        return btn;
    }

    private void configureTopButton(JButton button) {
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
    }
}
