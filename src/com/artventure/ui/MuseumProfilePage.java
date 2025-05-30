package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MuseumProfilePage {

    // 🔵 Προσθήκη πληροφοριών για τα μουσεία
    private static final Map<String, String[]> museumData = new HashMap<>();

    static {
        museumData.put("The Louvre", new String[]{
                "contact@louvre.fr",
                "Leonardo da Vinci: The Inventor",
                "Art, Antiquities",
                "Rue de Rivoli, 75001 Paris, France"
        });
        museumData.put("The MET", new String[]{
                "info@metmuseum.org",
                "Ancient Egypt: Beyond the Nile",
                "Art, History",
                "1000 5th Ave, New York, NY 10028, USA"
        });
        museumData.put("Tate Modern", new String[]{
                "contact@tate.org.uk",
                "Yayoi Kusama: Infinity Mirror Rooms",
                "Modern Art",
                "Bankside, London SE1 9TG, UK"
        });
        museumData.put("Van Gogh Museum", new String[]{
                "info@vangoghmuseum.nl",
                "Van Gogh’s Letters",
                "Post-Impressionism",
                "Museumplein 6, 1071 DJ Amsterdam, Netherlands"
        });
    }

    public static void open(String museumName) {
        JFrame frame = new JFrame("Museum Profile");
        frame.setSize(400, 550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xF0F8FF));
        frame.setLayout(new BorderLayout());

        // 🔵 Τίτλος
        JLabel nameLabel = new JLabel(museumName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        frame.add(nameLabel, BorderLayout.NORTH);

        // 🔵 Κεντρικό πάνελ με φωτογραφία + info
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(0xF0F8FF));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // 🔵 Φωτογραφία
        JLabel imgLabel = new JLabel("", SwingConstants.CENTER);
        String resourceName = "/ui/resources/" + museumName.toLowerCase().replace(" ", "_") + "_profile.png";
        URL imgUrl = MuseumProfilePage.class.getResource(resourceName);
        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(scaled));
        } else {
            imgLabel.setText("[ No image available ]");
            imgLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        }
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(imgLabel);

        // 🔵 Info πεδία
        String[] info = museumData.getOrDefault(museumName, new String[]{"-", "-", "-", "-"});

        JLabel contactLabel = new JLabel("📧 Contact: " + info[0]);
        JLabel exhibitionLabel = new JLabel("🖼️ Current Exhibition: " + info[1]);
        JLabel genreLabel = new JLabel("🎨 Genre: " + info[2]);
        JLabel addressLabel = new JLabel("📍 Address: " + info[3]);

        for (JLabel label : new JLabel[]{contactLabel, exhibitionLabel, genreLabel, addressLabel}) {
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            centerPanel.add(label);
        }

        frame.add(centerPanel, BorderLayout.CENTER);

        // 🔵 Κουμπί Close
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.setBackground(new Color(0xE6E6FA));
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> frame.dispose());

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(0xF0F8FF));
        btnPanel.add(closeButton);
        frame.add(btnPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
