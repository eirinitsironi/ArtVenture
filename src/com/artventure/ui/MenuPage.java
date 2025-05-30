package ui;
import javax.swing.*;
import java.awt.*;
import ui.FindVenuesPage;


public class MenuPage extends JPanel {
    public MenuPage(JFrame frame) {
        setLayout(null);
        setBackground(new Color(0xD3DFB7));

        Color backgroundColor = new Color(0xD3DFB7);
        Color middleButtonColor = new Color(0xE6E6FA);
        Color navBarColor = new Color(0xC4D2A4);

        // Top Panel
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 400, 70);
        topPanel.setBackground(backgroundColor);

        JButton backBtn = new JButton("<");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        backBtn.setBounds(10, 20, 50, 30);
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.addActionListener(ignored -> frame.dispose());
        topPanel.add(backBtn);

        JLabel title = new JLabel("Menu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(0, 20, 400, 30);
        topPanel.add(title);

        add(topPanel);

        // Menu Buttons Panel
        JPanel middlePanel = new JPanel(null);
        middlePanel.setBounds(0, 250, 386, 200);
        middlePanel.setBackground(backgroundColor);

        String[] buttonLabels = {"Preference quiz", "My wrapped", "Find venues", "Make art"};
        int y = 0;

        for (String label : buttonLabels) {
            JButton button = new JButton();
            button.setLayout(new BorderLayout());
            button.setBounds(0, y, 386, 50);
            button.setBackground(middleButtonColor);
            button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            button.setFocusPainted(false);
            button.setOpaque(true);

            JLabel textLabel = new JLabel(label);
            textLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            textLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

            JLabel arrowLabel = new JLabel(">", SwingConstants.RIGHT);
            arrowLabel.setForeground(Color.GRAY);
            arrowLabel.setFont(new Font("Arial", Font.BOLD, 18));
            arrowLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));

            button.add(textLabel, BorderLayout.WEST);
            button.add(arrowLabel, BorderLayout.EAST);

            button.addActionListener(ignored -> {
                switch (label) {
                    case "Preference quiz":
                        frame.setContentPane(new QuizMenu(frame));
                        frame.revalidate();
                        frame.repaint();
                        break;
                    case "My wrapped":
                        MyWrappedPage.open();
                        break;
                    case "Find venues":
                        FindVenuesPage.open();
                        break;
                    default:
                        JOptionPane.showMessageDialog(frame, "Opening: " + label);
                }
            });

            middlePanel.add(button);
            y += 50;
        }

        add(middlePanel);

        // Bottom Navigation Bar
        JPanel navBar = new JPanel(new GridLayout(1, 2));
        navBar.setBounds(0, 630, 400, 40);
        navBar.setBackground(navBarColor);

        JButton compassBtn = new JButton("🧭");
        JButton userBtn = new JButton("👤");

        for (JButton btn : new JButton[]{compassBtn, userBtn}) {
            btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(navBarColor);
            btn.setOpaque(true);
            navBar.add(btn);
        }

        add(navBar);
    }

    public static void open() {
        JFrame frame = new JFrame("Menu");
        frame.setSize(400, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(new MenuPage(frame));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
