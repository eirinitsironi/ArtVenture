package ui;

import javax.swing.*;
import java.awt.*;

public class PaintingsProfilePage {
    private static final String[] PAINTING_DESCRIPTIONS = {
            "Detail of Garden at Giverny (1900):\n" +
                    "Monet's impressionist masterpiece captures the essence of nature.\n" +
                    "This choice suggests you appreciate tranquility and find beauty in everyday scenes.",

            "The Starry Night:\n" +
                    "Van Gogh's swirling night sky shows his emotional turmoil.\n" +
                    "Your selection indicates a deep, contemplative personality with artistic sensitivity.",

            "Self-Portrait on the Mexican-American Border, 1932:\n" +
                    "Frida Kahlo's powerful self-representation blends cultural identities.\n" +
                    "This pick reveals your strong sense of identity and appreciation for cultural diversity.",

            "The Kiss:\n" +
                    "Klimt's golden depiction of love is both intimate and decorative.\n" +
                    "Choosing this painting suggests a romantic nature and appreciation for beauty in relationships."
    };

    public static void open(String artistName, int paintingIndex) {
        JFrame frame = new JFrame("Painting Profile");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0xD3DFB7));

        // Artist name label
        JLabel nameLabel = new JLabel(artistName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBounds(0, 20, 400, 30);
        frame.add(nameLabel);

        // Message
        JTextArea message = new JTextArea(
                "Learn what your favorite painting says about you:"
        );
        message.setBounds(30, 60, 340, 40);
        message.setFont(new Font("Arial", Font.PLAIN, 14));
        message.setBackground(new Color(0xD3DFB7));
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        message.setEditable(false);
        message.setFocusable(false);
        frame.add(message);

        // Painting description
        JTextArea description = new JTextArea(PAINTING_DESCRIPTIONS[paintingIndex]);
        description.setBounds(30, 110, 340, 300);
        description.setFont(new Font("Arial", Font.PLAIN, 14));
        description.setBackground(new Color(0xD3DFB7));
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setFocusable(false);
        frame.add(description);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}