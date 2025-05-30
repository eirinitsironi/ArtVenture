package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class ArtistProfilePage {
    public static void open(String artistName) {
        JFrame frame = new JFrame("Artist Profile");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0xD3DFB7));

        // 1. Τίτλος καλλιτέχνη (υπάρχων κώδικας)
        JLabel nameLabel = new JLabel(artistName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBounds(0, 20, 400, 30);
        frame.add(nameLabel);

        // 2. ΠΡΟΣΘΗΚΗ: Το νέο μήνυμα εδώ - πριν τα κουμπιά
        JTextArea welcomeMessage = new JTextArea(
                "Explore your favorite artist. See what they post and what other people think about them!"
        );
        welcomeMessage.setBounds(30, 70, 340, 50);  // Θέση κάτω από τον τίτλο
        welcomeMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        welcomeMessage.setBackground(new Color(0xD3DFB7));  // Ίδιο χρώμα με το φόντο
        welcomeMessage.setLineWrap(true);  // Αυτόματη αλλαγή γραμμής
        welcomeMessage.setWrapStyleWord(true);  // Ολόκληρες λέξεις
        welcomeMessage.setEditable(false);
        welcomeMessage.setFocusable(false);
        frame.add(welcomeMessage);

        // 3. Τα υπάρχοντα κουμπιά (υπάρχων κώδικας)
        String[] sections = {"Posts", "Reviews"};
        for (int i = 0; i < sections.length; i++) {
            JButton btn = new JButton(sections[i] + " >");
            btn.setBounds(20, 140 + i * 60, 340, 40);  // Αλλαγή θέσης (140 αντί για 80)
            btn.setBackground(new Color(0xE6E6FA));
            btn.setFocusPainted(false);
            frame.add(btn);
        }

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}