package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReviewPopup {

    public static class ReviewData {
        public int rating;
        public String comment;

        public ReviewData(int rating, String comment) {
            this.rating = rating;
            this.comment = comment;
        }
    }

    public static ReviewData showReviewPopup(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Leave a Review", true);
        dialog.setSize(300, 230);
        dialog.setLayout(null);
        dialog.getContentPane().setBackground(new Color(0xFFFFFF));
        dialog.setLocationRelativeTo(parentFrame);

        JLabel label = new JLabel("Leave a review:");
        label.setBounds(10, 10, 200, 20);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        dialog.add(label);

        JTextArea reviewArea = new JTextArea();
        reviewArea.setLineWrap(true);
        reviewArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(reviewArea);
        scrollPane.setBounds(10, 35, 260, 60);
        dialog.add(scrollPane);

        JLabel[] brushes = new JLabel[5];
        int[] rating = {0};
        for (int i = 0; i < 5; i++) {
            JLabel brush = new JLabel("🖌️");
            brush.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
            brush.setBounds(20 + i * 30, 110, 30, 30);
            final int index = i;
            brush.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    rating[0] = index + 1;
                    for (int j = 0; j < 5; j++) {
                        brushes[j].setForeground(j <= index ? Color.RED : Color.BLACK);
                    }
                }
            });
            brushes[i] = brush;
            dialog.add(brush);
        }

        JButton saveButton = new JButton("✅");
        saveButton.setBounds(230, 110, 60, 30);
        saveButton.setFocusPainted(false);
        saveButton.setBorderPainted(false);
        saveButton.setBackground(new Color(0xFFFFFF));
        saveButton.setOpaque(true);

        final ReviewData[] result = {null};

        saveButton.addActionListener(e -> {
            int selectedRating = rating[0];
            String reviewText = reviewArea.getText().trim();
            if (selectedRating > 0) {
                result[0] = new ReviewData(selectedRating, reviewText);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Please select a rating.");
            }
        });

        dialog.add(saveButton);
        dialog.setVisible(true);

        return result[0];
    }

    public static ReviewData showReviewPopupWithPreFilled(JFrame parentFrame, ReviewData existingReview) {
        ReviewData result = showReviewPopup(parentFrame);
        if (result != null) {
            return new ReviewData(result.rating, result.comment);
        }
        return null;
    }
}
