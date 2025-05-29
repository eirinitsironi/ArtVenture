package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;

public class MakeEventPostPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(0xD3DFB7);
    private static final Color NAV_BAR_COLOR = new Color(0xC4D2A4);
    
    private JTextField eventNameField, venueNameField, dateField, timeField, categoryField, priceField, addressField;
    private JTextArea descriptionArea;
    private JLabel imageLabel;
    private String imagePath;

    public MakeEventPostPanel(JFrame parentFrame) {
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

        JLabel titleLabel = new JLabel("Create Event Post", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(0, 20, 400, 30);
        topPanel.add(titleLabel);

        // Form Fields
        eventNameField = createRoundedTextField("Event Name", 70);
        venueNameField = createRoundedTextField("Venue Name", 120);

        JButton imageButton = createRoundedButton("Select Image", new Color(0xE6E6FA));
        imageButton.setBounds(30, 180, 150, 30);
        imageButton.addActionListener(this::selectImage);
        add(imageButton);

        imageLabel = new JLabel("No file chosen");
        imageLabel.setBounds(190, 185, 200, 20);
        add(imageLabel);

        dateField = createRoundedTextField("Date (YYYY-MM-DD)", 230);
        timeField = createRoundedTextField("Time (HH:MM)", 280);
        categoryField = createRoundedTextField("Category", 330);
        priceField = createRoundedTextField("Ticket Price", 380);
        addressField = createRoundedTextField("Address", 430);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(30, 480, 100, 25);
        add(descLabel);

        descriptionArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(descriptionArea);
        scroll.setBounds(30, 510, 340, 80);
        add(scroll);

        JButton saveButton = createRoundedButton("Save", new Color(0xE6E6FA));
        saveButton.setBounds(80, 600, 100, 30);
        add(saveButton);

        JButton cancelButton = createRoundedButton("Cancel", new Color(0xE6E6FA));
        cancelButton.setBounds(200, 600, 100, 30);
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
        add(cancelButton);


    }

    private JTextField createRoundedTextField(String placeholder, int y) {
        JLabel label = new JLabel(placeholder + ":");
        label.setBounds(30, y, 150, 25);
        add(label);

        JTextField field = new JTextField();
        field.setBounds(30, y + 25, 340, 30);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        add(field);

        return field;
    }

    private JButton createRoundedButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setUI(new RoundedButtonUI());
        button.setBackground(bgColor);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }

    private void selectImage(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            imagePath = selectedFile.getAbsolutePath();
            imageLabel.setText(selectedFile.getName());
        }
    }
}
