package ui;

import javax.swing.*;
import java.awt.*;

public class QuizList extends JPanel {
    public QuizList(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#D3DFB7"));

        // Top panel με το κουμπί επιστροφής
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.decode("#D3DFB7"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // padding

        JButton backButton = new JButton("<");
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(ignored -> {
            frame.setContentPane(new QuizMenu(frame));
            frame.revalidate();
            frame.repaint();
        });

        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);

        // Panel με τα τρία κουμπιά των quizzes
        JPanel quizzesPanel = new JPanel();
        quizzesPanel.setBackground(Color.decode("#D3DFB7"));
        quizzesPanel.setLayout(new BoxLayout(quizzesPanel, BoxLayout.Y_AXIS));

        JButton quiz1 = createQuizButton("Quiz 1", "#D0EACF");
        quiz1.setPreferredSize(new Dimension(Integer.MAX_VALUE, 110));
        quiz1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        quiz1.addActionListener(ignored -> JOptionPane.showMessageDialog(frame, "Quiz 1 clicked!"));

        JButton quiz2 = createQuizButton("Quiz 2", "#F7F0C3");
        quiz2.setPreferredSize(new Dimension(Integer.MAX_VALUE, 110));
        quiz2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        quiz2.addActionListener(ignored -> JOptionPane.showMessageDialog(frame, "Quiz 2 clicked!"));

        JButton quiz3 = createQuizButton("Quiz 3", "#DCC3F7");
        quiz3.setPreferredSize(new Dimension(Integer.MAX_VALUE, 110));
        quiz3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        quiz3.addActionListener(ignored -> JOptionPane.showMessageDialog(frame, "Quiz 3 clicked!"));

        quizzesPanel.add(quiz1);
        quizzesPanel.add(quiz2);
        quizzesPanel.add(quiz3);

        add(quizzesPanel, BorderLayout.CENTER);
    }

    private JButton createQuizButton(String text, String colorHex) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.decode(colorHex));
        btn.setFont(new Font("Arial", Font.PLAIN, 18));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btn.setLayout(new BorderLayout());

        JLabel arrow = new JLabel(">");
        arrow.setFont(new Font("Arial", Font.PLAIN, 20));
        arrow.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        btn.add(arrow, BorderLayout.EAST);

        return btn;
    }
}
