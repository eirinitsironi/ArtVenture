package ui;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    private final JTextField searchField;
    private final JFrame frame;

    public SearchPanel(JFrame frame) {
        this.frame = frame;
        setLayout(null);
        setBackground(new Color(0xD3DFB7));

        // Search Field Setup
        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchField.setBounds(14, 55, 360, 40);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        searchField.setBackground(Color.WHITE);
        searchField.setOpaque(true);

        // Placeholder
        String placeholder = "Search...";
        searchField.setForeground(Color.GRAY);
        searchField.setText(placeholder);

        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (searchField.getText().equals(placeholder)) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText(placeholder);
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        add(searchField);
    }

    public String getSearchText() {
        return searchField.getText();
    }
}
