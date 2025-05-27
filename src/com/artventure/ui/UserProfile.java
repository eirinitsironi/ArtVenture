package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UserProfile extends JPanel {

    private static final List<ReviewPopup.ReviewData> userReviews = new ArrayList<>();

    public UserProfile() {
        setLayout(null);
        setBackground(new Color(0xD3DFB7));

        Color backgroundColor = new Color(0xD3DFB7);
        Color iconColor = new Color(0xC4D2A4);
        Color middleButtonColor = new Color(0xE6E6FA);

        // === Top Panel ===
        JPanel topPanel = new JPanel(null);
        topPanel.setBackground(backgroundColor);
        topPanel.setBounds(0, 0, 400, 180);

        // === Top Left Icon Buttons ===
        String[] icons = {"≡", "🛒", "🔔"};
        int btnY = 0;
        for (String icon : icons) {
            JButton btn = new JButton(icon);
            btn.setFont(new Font("UI Emoji", Font.PLAIN, 20));
            btn.setBounds(0, btnY, 55, 55);
            btn.setBackground(iconColor);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(true);
            btn.setOpaque(true);
            topPanel.add(btn);
            btnY += 45;
        }

        JLabel nameLabel = new JLabel("Name Lastname");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameLabel.setBounds(60, 35, 200, 30);
        topPanel.add(nameLabel);

        // === Profile Picture ===
        URL imageUrl = UserProfile.class.getResource("/ui/resources/profile.png");
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image image = icon.getImage().getScaledInstance(80, 80, Image.SCALE_AREA_AVERAGING);
            JLabel profilePic = new JLabel(new ImageIcon(image));
            profilePic.setBounds(300, 20, 80, 80);
            topPanel.add(profilePic);
        } else {
            System.out.println("Η εικόνα profile.png δεν βρέθηκε!");
        }

        // === Points Label ===
        JLabel pointsLabel = new JLabel("Points 0", SwingConstants.CENTER);
        pointsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        pointsLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pointsLabel.setOpaque(true);
        pointsLabel.setBackground(new Color(245, 245, 245));
        pointsLabel.setBounds(310, 105, 60, 25);
        topPanel.add(pointsLabel);

        add(topPanel);

        // === Middle Buttons Panel ===
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(null);
        middlePanel.setBounds(0, 250, 400, 200);
        middlePanel.setBackground(backgroundColor);

        String[] buttonLabels = {"My posts", "Visit history", "Wishlist", "Reviews"};
        int y = 0;

        for (String label : buttonLabels) {
            JButton button = new JButton();
            button.setLayout(new BorderLayout());
            button.setBounds(0, y, 386, 50);
            button.setBackground(middleButtonColor);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            button.setBorderPainted(true);
            button.setFocusPainted(false);
            button.setOpaque(true);

            JLabel textLabel = new JLabel(label);
            textLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            textLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
            textLabel.setVerticalAlignment(SwingConstants.CENTER);

            JLabel arrowLabel = new JLabel(">", SwingConstants.RIGHT);
            arrowLabel.setForeground(Color.GRAY);
            arrowLabel.setFont(new Font("Arial", Font.BOLD, 18));
            arrowLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
            arrowLabel.setVerticalAlignment(SwingConstants.CENTER);

            button.add(textLabel, BorderLayout.WEST);
            button.add(arrowLabel, BorderLayout.EAST);

            if (label.equals("Reviews")) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                button.addActionListener(e -> showReviewDialog(frame));
            }

            middlePanel.add(button);
            y += 50;
        }

        add(middlePanel);

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
    public static void showReviewDialog(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Reviews Menu", true);
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(4, 1, 10, 10));

        JButton viewBtn = new JButton("View My Reviews");
        JButton addBtn = new JButton("Add Review");
        JButton editBtn = new JButton("Edit Review");
        JButton closeBtn = new JButton("Close");

        viewBtn.addActionListener(e -> {
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

        addBtn.addActionListener(e -> {
            dialog.dispose();
            ReviewPopup.ReviewData review = ReviewPopup.showReviewPopup(parentFrame);
            if (review != null) {
                userReviews.add(review);
                JOptionPane.showMessageDialog(parentFrame,
                        "Thank you! You gave " + review.rating + " stars\nComment: " + review.comment);
            }
        });

        editBtn.addActionListener(e -> {
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

                // Show pre-filled popup
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