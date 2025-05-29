package ui;

import java.awt.*;
import java.io.File;
import javax.swing.*;

public class MakePaintingPostPanel extends JPanel {
    
    private static final Color BACKGROUND_COLOR = new Color(0xD3DFB7);
    private static final Color NAV_BAR_COLOR = new Color(0xC4D2A4);

    public MakePaintingPostPanel(JFrame parentFrame) {
        setLayout(null);
        setBackground(BACKGROUND_COLOR);

        // Top Panel (Back button + Title)
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 400, 70);
        topPanel.setBackground(BACKGROUND_COLOR);
        add(topPanel);

        JButton backBtn = new JButton("<");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        backBtn.setBounds(10, 20, 50, 30);
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(e -> {
            parentFrame.setContentPane(new ChoosePostPanel(parentFrame));
            parentFrame.revalidate();
        });
        topPanel.add(backBtn);

        JLabel titleLabel = new JLabel("Make a Post", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(0, 20, 400, 30);
        topPanel.add(titleLabel);

        // Form Content
        JLabel titleTextLabel = new JLabel("Title:");
        titleTextLabel.setBounds(30, 80, 100, 25);
        add(titleTextLabel);

        JTextField titleField = new JTextField();
        titleField.setBounds(30, 105, 320, 30);
        add(titleField);

        JLabel imageLabel = new JLabel("Upload image:");
        imageLabel.setBounds(30, 150, 200, 25);
        add(imageLabel);

        JButton uploadButton = createRoundedButton("Choose file", new Color(0xE6E6FA));
        uploadButton.setBounds(30, 175, 150, 30);
        JLabel filePathLabel = new JLabel("No file chosen");
        filePathLabel.setBounds(190, 180, 180, 20);
        add(uploadButton);
        add(filePathLabel);

        final String[] selectedFilePath = {null};
        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(parentFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                selectedFilePath[0] = selectedFile.getAbsolutePath();
                filePathLabel.setText(selectedFile.getName());
            }
        });

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(30, 220, 100, 25);
        add(categoryLabel);

        String[] categories = {"Abstract", "Contemporary", "Realism", "Impressionism"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(30, 245, 320, 30);
        add(categoryBox);

        JLabel captionLabel = new JLabel("Caption:");
        captionLabel.setBounds(30, 290, 100, 25);
        add(captionLabel);

        JTextArea captionArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(captionArea);
        scrollPane.setBounds(30, 315, 320, 80);
        add(scrollPane);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(30, 410, 100, 25);
        add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(30, 435, 320, 30);
        add(priceField);

        JButton saveButton = createRoundedButton("Save Post", new Color(0xE6E6FA));
        saveButton.setBounds(30, 490, 150, 40);
        JButton cancelButton = createRoundedButton("Cancel", new Color(0xE6E6FA));
        cancelButton.setBounds(200, 490, 150, 40);
        add(saveButton);
        add(cancelButton);

        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            String imagePath = selectedFilePath[0];
            String category = (String) categoryBox.getSelectedItem();
            String caption = captionArea.getText();
            String priceText = priceField.getText();

            if (title.isEmpty() || imagePath == null || caption.isEmpty() || priceText.isEmpty()) {
                JOptionPane.showMessageDialog(parentFrame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double price = Double.parseDouble(priceText);
                // Pass info to backend here
                JOptionPane.showMessageDialog(parentFrame, "Post saved successfully.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parentFrame, "Invalid price.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(
                parentFrame,
                "Are you sure you want to cancel? All unsaved changes will be lost.",
                "Confirm Cancel",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (option == JOptionPane.YES_OPTION) {
                parentFrame.setContentPane(new ChoosePostPanel(parentFrame));
                parentFrame.revalidate();
            }
        });

        // Navigation Bar
        JPanel navBar = createNavigationBar();
        add(navBar);
    }

    private JButton createRoundedButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setUI(new RoundedButtonUI());
        button.setBackground(bgColor);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }

    private JPanel createNavigationBar() {
        JPanel navBar = new JPanel(new GridLayout(1, 2));
        navBar.setBounds(0, 630, 400, 40);
        navBar.setBackground(NAV_BAR_COLOR);

        JButton compassBtn = new JButton("🧭");
        JButton userBtn = new JButton("👤");

        for (JButton btn : new JButton[]{compassBtn, userBtn}) {
            btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(NAV_BAR_COLOR);
            btn.setOpaque(true);
            navBar.add(btn);
        }

        return navBar;
    }
}