package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class MyWrappedPage {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static CardLayout cardLayout;

    public static void open() {
        frame = new JFrame("My Wrapped");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xD3DFB7));

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(0xD3DFB7));

        JPanel paintingsPanel = createPaintingsPanel();
        mainPanel.add(paintingsPanel, "paintings");

        JPanel artistsPanel = createArtistsPanel();
        mainPanel.add(artistsPanel, "artists");

        JPanel museumsPanel = createMuseumsPanel(); // 🔵 Προστέθηκε η καρτέλα μουσείων
        mainPanel.add(museumsPanel, "museums");     // 🔵

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createPaintingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0xD3DFB7));

        JLabel title = new JLabel("My top paintings", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(0, 20, 400, 30);
        panel.add(title);

        String[] paintingTitles = {"Detail of Garden at Giverny (1900)", "The Starry Night",
                "Self-Portrait on the Mexican-American Border, 1932", "The Kiss"};
        String[] artistNames = {"Claude Monet", "Vincent van Gogh", "Frida Kahlo", "Gustav Klimt"};
        String[] imageResourcePaths = {
                "/ui/resources/painting1.jpg",
                "/ui/resources/painting2.jpg",
                "/ui/resources/painting3.jpeg",
                "/ui/resources/painting4.png"
        };

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(0xD3DFB7));

        for (int i = 0; i < paintingTitles.length; i++) {
            JPanel item = new JPanel(new BorderLayout());
            item.setPreferredSize(new Dimension(360, 80));
            item.setBackground(new Color(0xD3DFB7));
            item.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 10));

            JLabel imgLabel = new JLabel();
            URL imgUrl = MyWrappedPage.class.getResource(imageResourcePaths[i]);

            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image scaled = icon.getImage().getScaledInstance(50, 60, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(scaled));
            } else {
                imgLabel.setText("No image");
            }

            imgLabel.setPreferredSize(new Dimension(60, 60));
            item.add(imgLabel, BorderLayout.WEST);

            JPanel textWrapper = new JPanel(new BorderLayout());
            textWrapper.setOpaque(false);
            textWrapper.setBorder(BorderFactory.createEmptyBorder(0, 18, 0, 0));

            JLabel textLabel = new JLabel("<html>" + (i + 1) + ". " + paintingTitles[i] + "<br/>" + artistNames[i] + "</html>");
            textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            textWrapper.add(textLabel, BorderLayout.CENTER);

            item.add(textWrapper, BorderLayout.CENTER);

            String artistNameForClick = artistNames[i];

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
        panel.add(scrollPane);

        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(150, 580, 100, 30);
        nextBtn.setBackground(new Color(0xE6E6FA));
        nextBtn.addActionListener(ignored -> cardLayout.show(mainPanel, "artists"));
        panel.add(nextBtn);

        JButton restartBtn = new JButton("🔁 Start Over");
        restartBtn.setBounds(140, 620, 120, 30);
        restartBtn.setBackground(new Color(0xF5DEB3));
        restartBtn.setFocusPainted(false);
        restartBtn.addActionListener(ignored -> {
            frame.dispose();
            open();
        });
        panel.add(restartBtn);

        return panel;
    }

    private static JPanel createArtistsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0xD3DFB7));

        JLabel title = new JLabel("My top artists", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(0, 20, 400, 30);
        panel.add(title);

        String[] artistNames = {"Jadé Fadojutimi", "María Berrío", "Tony Clark", "Despina Stokou"};
        String[] imageResourcePaths = {
                "/ui/resources/artist1.png",
                "/ui/resources/artist2.png",
                "/ui/resources/artist3.png",
                "/ui/resources/artist4.png"
        };

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(0xD3DFB7));

        for (int i = 0; i < artistNames.length; i++) {
            JPanel item = new JPanel(new BorderLayout());
            item.setPreferredSize(new Dimension(80, 80));
            item.setBackground(new Color(0xD3DFB7));
            item.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 10));

            JLabel imgLabel = new JLabel();
            URL imgUrl = MyWrappedPage.class.getResource(imageResourcePaths[i]);

            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image scaled = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(scaled));
            } else {
                imgLabel.setText("No image");
            }

            imgLabel.setPreferredSize(new Dimension(60, 60));
            item.add(imgLabel, BorderLayout.WEST);

            JLabel textLabel = new JLabel((i + 1) + ". " + artistNames[i]);
            textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            textLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            item.add(textLabel, BorderLayout.CENTER);

            String artistNameForClick = artistNames[i];

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
        panel.add(scrollPane);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(100, 580, 80, 30);
        backBtn.setBackground(new Color(0xE6E6FA));
        backBtn.addActionListener(ignored -> cardLayout.show(mainPanel, "paintings"));
        panel.add(backBtn);

        JButton nextBtn = new JButton("Next");
        nextBtn.setBounds(200, 580, 80, 30);
        nextBtn.setBackground(new Color(0xE6E6FA));
        nextBtn.addActionListener(ignored -> cardLayout.show(mainPanel, "museums")); // 🔵 Πάει στα μουσεία
        panel.add(nextBtn);

        JButton restartBtn = new JButton("🔁 Start Over");
        restartBtn.setBounds(140, 620, 120, 30);
        restartBtn.setBackground(new Color(0xF5DEB3));
        restartBtn.setFocusPainted(false);
        restartBtn.addActionListener(ignored -> {
            frame.dispose();
            open();
        });
        panel.add(restartBtn);

        return panel;
    }

    private static JPanel createMuseumsPanel() { // 🔵 ΝΕΑ ΜΕΘΟΔΟΣ
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0xD3DFB7));

        JLabel title = new JLabel("My top museums", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(0, 20, 400, 30);
        panel.add(title);

        String[] museumNames = {"The Louvre", "The MET", "Tate Modern", "Van Gogh Museum"};
        String[] imagePaths = {
                "/ui/resources/museum1.png",
                "/ui/resources/museum2.png",
                "/ui/resources/museum3.png",
                "/ui/resources/museum4.png"
        };

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(0xD3DFB7));

        for (int i = 0; i < museumNames.length; i++) {
            JPanel item = new JPanel(new BorderLayout());
            item.setPreferredSize(new Dimension(360, 80));
            item.setBackground(new Color(0xD3DFB7));
            item.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 10));

            JLabel imgLabel = new JLabel();
            URL imgUrl = MyWrappedPage.class.getResource(imagePaths[i]);

            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image scaled = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(scaled));
            } else {
                imgLabel.setText("No image");
            }

            imgLabel.setPreferredSize(new Dimension(60, 60));
            item.add(imgLabel, BorderLayout.WEST);

            JLabel textLabel = new JLabel((i + 1) + ". " + museumNames[i]);
            textLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            textLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            item.add(textLabel, BorderLayout.CENTER);

            String museumName = museumNames[i];
            item.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    MuseumProfilePage.open(museumName); // 🔵 Άνοιγμα νέου παραθύρου μουσείου
                }
            });

            listPanel.add(item);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBounds(0, 60, 400, 500);
        scrollPane.setBorder(null);
        panel.add(scrollPane);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(150, 580, 100, 30);
        backBtn.setBackground(new Color(0xE6E6FA));
        backBtn.addActionListener(ignored -> cardLayout.show(mainPanel, "artists"));
        panel.add(backBtn);

        JButton restartBtn = new JButton("🔁 Start Over");
        restartBtn.setBounds(140, 620, 120, 30);
        restartBtn.setBackground(new Color(0xF5DEB3));
        restartBtn.setFocusPainted(false);
        restartBtn.addActionListener(ignored -> {
            frame.dispose();
            open();
        });
        panel.add(restartBtn);

        return panel;
    }
}
