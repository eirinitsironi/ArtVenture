package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class MakeEventPostUI extends JFrame {
    private JTextField eventNameField, venueNameField, dateField, timeField, categoryField, priceField, addressField;
    private JTextArea descriptionArea;
    private JLabel imageLabel;
    private String imagePath;

    public MakeEventPostUI() {
        setTitle("Create Event Post");
        setSize(400, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(0xD3DFB7));

        JLabel titleLabel = new JLabel("Create Event Post");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(100, 10, 200, 30);
        add(titleLabel);

        eventNameField = createTextField("Event Name", 50);
        venueNameField = createTextField("Venue Name", 100);

        JButton imageButton = new JButton("Select Image");
        imageButton.setBounds(20, 150, 150, 25);
        imageButton.addActionListener(this::selectImage);
        add(imageButton);

        imageLabel = new JLabel("No file chosen");
        imageLabel.setBounds(180, 150, 200, 25);
        add(imageLabel);

        dateField = createTextField("Date (YYYY-MM-DD)", 190);
        timeField = createTextField("Time (HH:MM)", 230);
        categoryField = createTextField("Category", 270);
        priceField = createTextField("Ticket Price", 310);
        addressField = createTextField("Address", 350);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(20, 390, 100, 25);
        add(descLabel);

        descriptionArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(descriptionArea);
        scroll.setBounds(20, 420, 340, 80);
        add(scroll);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(80, 520, 100, 30);
        add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 520, 100, 30);
        cancelButton.addActionListener(e -> dispose());
        add(cancelButton);
    }

    private JTextField createTextField(String placeholder, int y) {
        JLabel label = new JLabel(placeholder + ":");
        label.setBounds(20, y, 150, 25);
        add(label);

        JTextField field = new JTextField();
        field.setBounds(150, y, 200, 25);
        add(field);

        return field;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MakeEventPostUI().setVisible(true));
    }
}
