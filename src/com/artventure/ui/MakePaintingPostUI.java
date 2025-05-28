package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MakePaintingPostUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MakePaintingPostUI::createAndShowUI);
    }

    public static void createAndShowUI() {
        JFrame frame = new JFrame("Make a Post");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0xD3DFB7));

        JLabel titleLabel = new JLabel("Make a Post");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(130, 20, 200, 30);
        frame.add(titleLabel);

        JLabel titleTextLabel = new JLabel("Title:");
        titleTextLabel.setBounds(30, 80, 100, 25);
        frame.add(titleTextLabel);

        JTextField titleField = new JTextField();
        titleField.setBounds(30, 105, 320, 30);
        frame.add(titleField);

        JLabel imageLabel = new JLabel("Upload image:");
        imageLabel.setBounds(30, 150, 200, 25);
        frame.add(imageLabel);

        JButton uploadButton = new JButton("Choose file");
        uploadButton.setBounds(30, 175, 150, 30);
        JLabel filePathLabel = new JLabel("No file chosen");
        filePathLabel.setBounds(190, 180, 180, 20);

        frame.add(uploadButton);
        frame.add(filePathLabel);

        final String[] selectedFilePath = {null};
        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                selectedFilePath[0] = selectedFile.getAbsolutePath();
                filePathLabel.setText(selectedFile.getName());
            }
        });

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(30, 220, 100, 25);
        frame.add(categoryLabel);

        String[] categories = {"Abstract", "Contemporary", "Realism", "Impressionism"};
        JComboBox<String> categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(30, 245, 320, 30);
        frame.add(categoryBox);

        JLabel captionLabel = new JLabel("Caption:");
        captionLabel.setBounds(30, 290, 100, 25);
        frame.add(captionLabel);

        JTextArea captionArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(captionArea);
        scrollPane.setBounds(30, 315, 320, 80);
        frame.add(scrollPane);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(30, 410, 100, 25);
        frame.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(30, 435, 320, 30);
        frame.add(priceField);

        JButton saveButton = new JButton("Save Post");
        saveButton.setBounds(30, 490, 150, 40);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 490, 150, 40);
        frame.add(saveButton);
        frame.add(cancelButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String imagePath = selectedFilePath[0];
                String category = (String) categoryBox.getSelectedItem();
                String caption = captionArea.getText();
                String priceText = priceField.getText();

                if (title.isEmpty() || imagePath == null || caption.isEmpty() || priceText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double price = Double.parseDouble(priceText);
                    // You can pass this info to your backend here
                    JOptionPane.showMessageDialog(frame, "Post saved successfully.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid price.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }
}
