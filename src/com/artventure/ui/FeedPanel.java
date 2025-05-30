package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

public class FeedPanel extends JPanel {

    private JTextField searchField;

    public FeedPanel(JFrame frame) {
        setLayout(null);
        setBackground(new Color(0xD3DFB7));

        JLabel titleLabel = new JLabel("Venture", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(0, 10, 400, 30);
        add(titleLabel);

        JPanel paintingCard = createCard("painting title", true);
        paintingCard.setBounds(20, 60, 350, 130);
        add(paintingCard);

        JPanel eventCard = createCard("event title", false);
        eventCard.setBounds(20, 210, 350, 130);
        add(eventCard);

        // Search Bar
        searchField = new JTextField("Search...");
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setBounds(20, 550, 350, 40);
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(Color.GRAY);
        searchField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        add(searchField);

        searchField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
                showSearchOverlay(frame);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });

        // Bottom Navigation Bar
        JPanel navBar = new JPanel(new GridLayout(1, 2));
        navBar.setBounds(0, 630, 400, 40);
        navBar.setBackground(new Color(0xC4D2A4)); // Ίδιο χρώμα όπως στο UserProfile

        JButton homeButton = new JButton("🧭");
        JButton profileButton = new JButton("👤");

        for (JButton btn : new JButton[]{homeButton, profileButton}) {
            btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(new Color(0xC4D2A4));
            btn.setOpaque(true);
            navBar.add(btn);
        }

        homeButton.addActionListener(e -> {
            frame.setContentPane(new FeedPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        profileButton.addActionListener(e -> {
            UserProfile.createAndShowGUI();
            frame.dispose();
        });

        add(navBar);
    }

    private JPanel createCard(String title, boolean isPainting) {
        JPanel card = new JPanel(null);
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        card.setBackground(Color.WHITE);
        card.setSize(350, 130);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(10, 10, 200, 25);
        card.add(titleLabel);

        if (isPainting) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/ui/resources/painting4.png"));
            JLabel img = new JLabel(icon);
            img.setBounds(10, 40, 100, 60);
            img.setOpaque(true);
            img.setBackground(new Color(200, 230, 200));
            card.add(img);
        } else {
            JLabel venueLabel = new JLabel("Venue:");
            venueLabel.setBounds(10, 40, 200, 20);
            card.add(venueLabel);

            JLabel hoursLabel = new JLabel("Opening Hours:");
            hoursLabel.setBounds(10, 60, 200, 20);
            card.add(hoursLabel);
        }

        JLabel avgLabel = new JLabel("avg");
        avgLabel.setBounds(270, 90, 50, 20);
        card.add(avgLabel);

        JLabel heart = new JLabel("♡");
        heart.setBounds(320, 90, 20, 20);
        card.add(heart);

        return card;
    }

    private void showSearchOverlay(JFrame frame) {
        frame.setContentPane(new SearchWindow(frame));
        frame.revalidate();
        frame.repaint();
    }

}
