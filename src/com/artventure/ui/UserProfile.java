package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserProfile {

    private static final List<ReviewPopup.ReviewData> userReviews = new ArrayList<>();
    private static JFrame frame;
    private static JPanel mainContentPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserProfile::createAndShowGUI);
    }

    public static void createAndShowGUI() {
        frame = new JFrame("User Profile");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(0xD3DFB7));

        // Κύριο panel με CardLayout
        mainContentPanel = new JPanel(new CardLayout());
        mainContentPanel.setBackground(new Color(0xD3DFB7));

        // Προσθήκη των panels στο card layout
        mainContentPanel.add(createProfilePanel(), "profile");
        mainContentPanel.add(createSearchPanel(), "search");

        frame.add(mainContentPanel, BorderLayout.CENTER);
        frame.add(createNavBar(), BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createProfilePanel() {
        JPanel profilePanel = new JPanel(null);
        profilePanel.setBackground(new Color(0xD3DFB7));

        // Top panel
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 400, 180);
        topPanel.setBackground(new Color(0xD3DFB7));

        String[] icons = {"≡", "🛒", "🔔"};
        int btnY = 0;

        for (String icon : icons) {
            JButton btn = new JButton(icon);
            btn.setFont(new Font("UI Emoji", Font.PLAIN, 20));
            btn.setBounds(0, btnY, 55, 55);
            btn.setBackground(new Color(0xC4D2A4));
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setOpaque(true);

            if (icon.equals("≡")) {
                btn.addActionListener(e -> MenuPage.open());
            }

            topPanel.add(btn);
            btnY += 45;
        }

        JLabel nameLabel = new JLabel("Name Lastname");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setBounds(60, 35, 200, 30);
        topPanel.add(nameLabel);

        URL imageUrl = UserProfile.class.getResource("/ui/resources/profile.png");
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image image = icon.getImage().getScaledInstance(80, 80, Image.SCALE_AREA_AVERAGING);
            JLabel profilePic = new JLabel(new ImageIcon(image));
            profilePic.setBounds(300, 20, 80, 80);
            topPanel.add(profilePic);
        }

        JLabel pointsLabel = new JLabel("Points 0", SwingConstants.CENTER);
        pointsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        pointsLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pointsLabel.setOpaque(true);
        pointsLabel.setBackground(new Color(245, 245, 245));
        pointsLabel.setBounds(310, 105, 60, 25);
        topPanel.add(pointsLabel);

        profilePanel.add(topPanel);

        // Middle buttons
        JPanel middlePanel = new JPanel(null);
        middlePanel.setBounds(0, 250, 400, 200);
        middlePanel.setBackground(new Color(0xD3DFB7));

        String[] buttonLabels = {"My posts", "Visit history", "Wishlist", "Reviews"};
        int y = 0;

        for (String label : buttonLabels) {
            JButton button = new JButton();
            button.setLayout(new BorderLayout());
            button.setBounds(0, y, 386, 50);
            button.setBackground(new Color(0xE6E6FA));
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            button.setBorderPainted(true);
            button.setFocusPainted(false);
            button.setOpaque(true);

            JLabel textLabel = new JLabel(label);
            textLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            textLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

            JLabel arrowLabel = new JLabel(">", SwingConstants.RIGHT);
            arrowLabel.setForeground(Color.GRAY);
            arrowLabel.setFont(new Font("Arial", Font.BOLD, 18));
            arrowLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));

            button.add(textLabel, BorderLayout.WEST);
            button.add(arrowLabel, BorderLayout.EAST);

            if (label.equals("Reviews")) {
                button.addActionListener(ignored -> showReviewDialog(frame));
            }

            middlePanel.add(button);
            y += 50;
        }

        profilePanel.add(middlePanel);
        return profilePanel;
    }

    private static JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(null);
        searchPanel.setBackground(new Color(0xD3DFB7));

        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchField.setBounds(14, 55, 360, 40);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        searchField.setBackground(Color.WHITE);
        searchField.setOpaque(true);

        // Ίσως θες placeholder (προαιρετικό):
// Placeholder συμπεριφορά
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


        searchPanel.add(searchField);
        return searchPanel;
    }

    private static JPanel createNavBar() {
        JPanel navBar = new JPanel(new GridLayout(1, 3));
        navBar.setBackground(new Color(0xC4D2A4));
        navBar.setPreferredSize(new Dimension(400, 40));

        JButton homeButton = new JButton("🧭");
        homeButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        homeButton.setFocusPainted(false);
        homeButton.setBorderPainted(false);
        homeButton.setBackground(new Color(0xC4D2A4));
        homeButton.setOpaque(true);
        homeButton.addActionListener(e -> switchToCard("search"));

        JButton empty = new JButton();
        empty.setEnabled(false);
        empty.setOpaque(false);
        empty.setBorderPainted(false);

        JButton profileButton = new JButton("👤");
        profileButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        profileButton.setFocusPainted(false);
        profileButton.setBorderPainted(false);
        profileButton.setBackground(new Color(0xC4D2A4));
        profileButton.setOpaque(true);
        profileButton.addActionListener(e -> switchToCard("profile"));

        navBar.add(homeButton);
        navBar.add(empty);
        navBar.add(profileButton);

        return navBar;
    }

    private static void switchToCard(String name) {
        CardLayout cl = (CardLayout) (mainContentPanel.getLayout());
        cl.show(mainContentPanel, name);
    }

    public static void showReviewDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Reviews Menu", true);
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(4, 1, 10, 10));

        JButton viewBtn = new JButton("View My Reviews");
        JButton addBtn = new JButton("Add Review");
        JButton editBtn = new JButton("Edit Review");
        JButton closeBtn = new JButton("Close");

        viewBtn.addActionListener(ignored -> {
            if (userReviews.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "No reviews yet.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < userReviews.size(); i++) {
                    ReviewPopup.ReviewData r = userReviews.get(i);
                    sb.append("Review ").append(i + 1).append(": ")
                            .append(r.rating).append(" stars - ")
                            .append(r.comment).append("\n\n");
                }
                JOptionPane.showMessageDialog(dialog, sb.toString(), "My Reviews", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addBtn.addActionListener(ignored -> {
            dialog.dispose();
            ReviewPopup.ReviewData review = ReviewPopup.showReviewPopup(parentFrame);
            if (review != null) {
                userReviews.add(review);
                JOptionPane.showMessageDialog(parentFrame,
                        "Thank you! You gave " + review.rating + " stars\nComment: " + review.comment);
            }
        });

        editBtn.addActionListener(ignored -> {
            if (userReviews.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "No reviews to edit.");
                return;
            }

            String[] reviewTitles = new String[userReviews.size()];
            for (int i = 0; i < userReviews.size(); i++) {
                ReviewPopup.ReviewData r = userReviews.get(i);
                reviewTitles[i] = "Review " + (i + 1) + ": " + r.rating + "★ - " + r.comment;
            }

            String selected = (String) JOptionPane.showInputDialog(dialog,
                    "Select a review to edit:",
                    "Edit Review",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    reviewTitles,
                    reviewTitles[0]);

            if (selected != null) {
                int selectedIndex = java.util.Arrays.asList(reviewTitles).indexOf(selected);
                ReviewPopup.ReviewData oldReview = userReviews.get(selectedIndex);

                ReviewPopup.ReviewData updated = ReviewPopup.showReviewPopupWithPreFilled(parentFrame, oldReview);
                if (updated != null) {
                    userReviews.set(selectedIndex, updated);
                    JOptionPane.showMessageDialog(parentFrame,
                            "Review updated! New rating: " + updated.rating + "\nComment: " + updated.comment);
                }
            }
        });

        closeBtn.addActionListener(e -> dialog.dispose());

        Font btnFont = new Font("Arial", Font.PLAIN, 16);
        for (JButton btn : new JButton[]{viewBtn, addBtn, editBtn, closeBtn}) {
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            dialog.add(btn);
        }

        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
}
