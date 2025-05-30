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
        avgLabel.setBounds(270, 90, 30, 20);
        card.add(avgLabel);

        JLabel heart = new JLabel("♡");
        heart.setBounds(320, 90, 20, 20);
        card.add(heart);

        return card;
    }

    private void showSearchOverlay(JFrame frame) {
        JPanel searchOverlay = new JPanel(null);
        searchOverlay.setBackground(new Color(0xD3DFB7));

        // Back button
        JButton backButton = new JButton("<");
        backButton.setBounds(10, 10, 80, 30);
        backButton.addActionListener((ActionEvent e) -> {
            frame.setContentPane(new FeedPanel(frame));
            frame.revalidate();
            frame.repaint();
        });
        searchOverlay.add(backButton);

        // Copy current search field into the new view
        JTextField searchFieldCopy = new JTextField(searchField.getText());
        searchFieldCopy.setFont(new Font("Arial", Font.PLAIN, 16));
        searchFieldCopy.setBounds(20, 60, 350, 40);
        searchFieldCopy.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        searchOverlay.add(searchFieldCopy);

        frame.setContentPane(searchOverlay);
        frame.revalidate();
        frame.repaint();
    }
}
