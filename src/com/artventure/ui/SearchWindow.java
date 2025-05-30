package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchWindow extends JPanel {

    public SearchWindow(JFrame frame) {
        setLayout(null);
        setBackground(new Color(0xD3DFB7));

        // Κουμπί επιστροφής
        JButton backButton = new JButton("<-");
        backButton.setBounds(10, 10, 80, 30);
        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(new FeedPanel(frame));
            frame.revalidate();
            frame.repaint();
        });
        add(backButton);

        JTextField searchField = new JTextField("Search...");
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setBounds(10, 60, 380, 40);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(Color.GRAY);

        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (searchField.getText().equals("Search...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        add(searchField);

    }
}
