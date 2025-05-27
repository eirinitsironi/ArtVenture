package com.artventure.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import com.artventure.ui.ArtistProfilePage;
import ui.UserProfile;

public class MyWrappedPage {
    public static void open() {
        JFrame frame = new JFrame("My Wrapped");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0xD3DFB7));

        JLabel title = new JLabel("My top paintings", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(0, 20, 400, 30);
        frame.add(title);

        // Dummy δεδομένα
        String[] paintingTitles = {"Detail of Garden at Giverny (1900)", "Painting Title 2", "Painting Title 3", "Painting Title 4"};
        String[] artistNames = {"Claude Monet", "Artist Name 2", "Artist Name 3", "Artist Name 4"};
        String[] imageResourcePaths = {
                "/ui/resources/painting1.jpg",
                "/images/img2.png",
                "/images/img3.png",
                "/images/img4.png"
        };

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(0xD3DFB7));

        for (int i = 0; i < paintingTitles.length; i++) {
            JPanel item = new JPanel(new BorderLayout());
            item.setPreferredSize(new Dimension(360, 80));
            item.setBackground(new Color(0xD3DFB7));
            item.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 10));

            // Εικόνα
            JLabel imgLabel = new JLabel();
            URL imgUrl = MyWrappedPage.class.getResource(imageResourcePaths[i]);

            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image scaled = icon.getImage().getScaledInstance(150, 60, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(scaled));
            } else {
                imgLabel.setText("No image");
            }

            imgLabel.setPreferredSize(new Dimension(60, 60));
// Προσθέτουμε την εικόνα
            item.add(imgLabel, BorderLayout.WEST);

// Δημιουργούμε wrapper panel για το κείμενο ώστε να έχει padding
            JPanel textWrapper = new JPanel(new BorderLayout());
            textWrapper.setOpaque(false); // Διάφανο background
            textWrapper.setBorder(BorderFactory.createEmptyBorder(0, 18, 0, 0)); // Πιο δεξιά

            JLabel textLabel = new JLabel("<html>" + (i + 1) + ". " + paintingTitles[i] + "<br/>" + artistNames[i] + "</html>");
            textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            textWrapper.add(textLabel, BorderLayout.CENTER);

// Προσθέτουμε το wrapper panel στο κέντρο
            item.add(textWrapper, BorderLayout.CENTER);

            String artistNameForClick = artistNames[i]; // final μεταβλητή για χρήση στο click

            // Κλικ → προφίλ καλλιτέχνη
            item.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    ArtistProfilePage.open(artistNameForClick);
                }
            });

            listPanel.add(item);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBounds(0, 60, 400, 500);
        scrollPane.setBorder(null);
        frame.add(scrollPane);

        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(150, 580, 100, 30);
        nextBtn.setBackground(new Color(0xE6E6FA));
        frame.add(nextBtn);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

