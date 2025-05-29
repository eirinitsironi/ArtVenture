package ui;

import java.awt.*;
import javax.swing.*;

public class ChoosePostPanel extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(0xD3DFB7);
    private static final Color NAV_BAR_COLOR = new Color(0xC4D2A4);

    public ChoosePostPanel(JFrame frame) {
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
            frame.setContentPane(new MyPostsPanel(frame, null)); // Pass actual user here
            frame.revalidate();
        });
        topPanel.add(backBtn);

        JLabel title = new JLabel("Create Post", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(0, 20, 400, 30);
        topPanel.add(title);

        // Main Content
        JLabel label = new JLabel("What do you want to post?");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBounds(80, 100, 300, 30);
        add(label);

        JButton paintingBtn = createRoundedButton("Painting", new Color(0xE6E6FA));
        paintingBtn.setBounds(100, 150, 200, 40);
        add(paintingBtn);

        JButton eventBtn = createRoundedButton("Event", new Color(0xE6E6FA));
        eventBtn.setBounds(100, 210, 200, 40);
        add(eventBtn);

        paintingBtn.addActionListener(e -> {
            frame.setContentPane(new MakePaintingPostPanel(frame));
            frame.revalidate();
        });
        eventBtn.addActionListener(e -> {
            frame.setContentPane(new MakeEventPostPanel(frame));
            frame.revalidate();
        });

        // Navigation Bar
        JPanel navBar = createNavigationBar();
        add(navBar);
    }

    private JButton createRoundedButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setUI(new RoundedButtonUI());
        button.setBackground(bgColor);
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