package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchWindow extends JPanel {

    public SearchWindow(JFrame frame) {
        setLayout(null);
        setBackground(new Color(0xD3DFB7));

        JButton backButton = new JButton("<");
        backButton.setBounds(10, 10, 80, 30);
        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(new FeedPanel(frame));
            frame.revalidate();
            frame.repaint();
        });
        add(backButton);

        JPanel searchBar = new JPanel(null);
        searchBar.setBounds(10, 60, 365, 40);
        searchBar.setBackground(new Color(0xECE5F4));
        searchBar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        searchBar.setOpaque(true);

        // Search Field
        JTextField searchField = new JTextField("");
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBounds(10, 5, 220, 30);
        searchField.setBorder(null);
        searchField.setBackground(new Color(0xECE5F4));
        searchField.setForeground(Color.BLACK);
        searchBar.add(searchField);

        // Settings Button
        JButton settingsButton = new JButton("⚙");
        settingsButton.setBounds(275, 5, 30, 30);
        styleIconButton(settingsButton);
        settingsButton.addActionListener(e -> showFilterPopup(frame));
        searchBar.add(settingsButton);

        // Search Button
        JButton searchButton = new JButton("🔍");
        searchButton.setBounds(315, 5, 30, 30);
        styleIconButton(searchButton);
        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim();
            JOptionPane.showMessageDialog(frame, "Searching for: " + query);
        });
        searchBar.add(searchButton);

        add(searchBar);
    }

    private void showFilterPopup(JFrame frame) {
        JDialog filterDialog = new JDialog(frame, "Filters", true);
        filterDialog.setSize(250, 200);
        filterDialog.setLocationRelativeTo(frame);
        filterDialog.setLayout(new GridLayout(4, 1, 10, 10));

        JButton genreButton = new JButton("🎨 Painting genre");
        JButton dateButton = new JButton("🕒 Date and time");
        JButton priceButton = new JButton("💰 Price");
        JButton closeButton = new JButton("Close");

        genreButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Select painting genre..."));
        dateButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Select date and time..."));
        priceButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Select price range..."));

        closeButton.addActionListener(e -> filterDialog.dispose());

        filterDialog.add(genreButton);
        filterDialog.add(dateButton);
        filterDialog.add(priceButton);
        filterDialog.add(closeButton);

        filterDialog.setVisible(true);
    }

    // Emoji-style button
    private void styleIconButton(JButton button) {
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
