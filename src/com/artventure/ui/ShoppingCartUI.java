package ui;

import javax.swing.*;
import java.awt.*;

public class ShoppingCartUI extends JFrame {

    private final Color backgroundColor = new Color(221, 230, 189);
    private final Color iconColor = new Color(200, 210, 170);

    public ShoppingCartUI() {
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(backgroundColor);

        JPanel topBar = createTopBar();
        JPanel centerPanel = createCenterPanel();
        JPanel bottomNav = createBottomNav();

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(backgroundColor);

        JPanel bottomContentPanel = createBottomContentPanel();
        southPanel.add(bottomContentPanel, BorderLayout.CENTER);

        southPanel.add(bottomNav, BorderLayout.SOUTH);

        mainPanel.add(topBar, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);


        add(mainPanel);
        setVisible(true);
    }

    private JPanel createTopBar() {
        JPanel topPanel = new JPanel(null);
        topPanel.setBackground(backgroundColor);
        topPanel.setPreferredSize(new Dimension(400, 140));

        String[] icons = {"≡", "🛒", "🔔"};
        int btnY = 0;

        for (String icon : icons) {
            JButton btn = new JButton(icon);
            btn.setFont(new Font("UI Emoji", Font.PLAIN, 20));
            btn.setBounds(0, btnY, 55, 55);
            btn.setBackground(iconColor);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setOpaque(true);

            if (icon.equals("≡")) {
                btn.addActionListener(ignored -> ui.MenuPage.open());
            }

            topPanel.add(btn);
            btnY += 45;
        }

        JLabel title = new JLabel("shopping cart", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(80, 20, 200, 30);
        topPanel.add(title);

        int panelWidth = 400;
        int rightMargin = 20;

        JLabel pointsLabel = new JLabel("Points 0", SwingConstants.CENTER);
        pointsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        pointsLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        pointsLabel.setOpaque(true);
        pointsLabel.setBackground(new Color(245, 245, 245));
        pointsLabel.setBounds(panelWidth - 80 - rightMargin, 10, 80, 25);
        topPanel.add(pointsLabel);

        JButton historyButton = new JButton("purchase history");
        historyButton.setFont(new Font("Arial", Font.PLAIN, 12));
        historyButton.setBounds(panelWidth - 120 - rightMargin, 45, 120, 30);
        historyButton.setBackground(iconColor);
        historyButton.setBorderPainted(false);
        historyButton.setFocusPainted(false);
        historyButton.setOpaque(true);
        historyButton.addActionListener(ignored -> JOptionPane.showMessageDialog(this, "Άνοιξε το purchase history (υλοποίησέ το)"));
        topPanel.add(historyButton);

        return topPanel;
    }

    private JPanel createBottomNav() {
        JPanel navBar = new JPanel(new GridLayout(1, 2));
        navBar.setPreferredSize(new Dimension(400, 40));
        navBar.setBackground(iconColor);

        JButton homeButton = new JButton("🧭");
        JButton profileButton = new JButton("👤");

        for (JButton btn : new JButton[]{homeButton, profileButton}) {
            btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setBackground(iconColor);
            btn.setOpaque(true);
            navBar.add(btn);
        }

        homeButton.addActionListener(ignored -> {
            this.setContentPane(new FeedPanel(this));
            this.revalidate();
            this.repaint();
        });


        profileButton.addActionListener(ignored -> {
            UserProfile.createAndShowGUI();
            this.dispose();
        });

        return navBar;
    }

    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(backgroundColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        centerPanel.add(createItemPanel("event title", "ui/resources/ticket.jpg", true));
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(createItemPanel("painting title", "ui/resources/painting.jpg", false));
        centerPanel.add(Box.createVerticalStrut(10));

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pricePanel.setBackground(backgroundColor);
        pricePanel.add(new JLabel("Total price:"));
        JTextField priceField = new JTextField(5);
        pricePanel.add(priceField);
        pricePanel.add(new JLabel("€"));


        JPanel couponPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        couponPanel.setBackground(backgroundColor);
        couponPanel.add(new JLabel("Enter coupon:"));
        JTextField couponField = new JTextField("coupon number", 10);
        couponPanel.add(couponField);

        return centerPanel;
    }

    private JPanel createBottomContentPanel() {
        JPanel bottomContentPanel = new JPanel();
        bottomContentPanel.setLayout(new BoxLayout(bottomContentPanel, BoxLayout.Y_AXIS));
        bottomContentPanel.setBackground(backgroundColor);

        // Price Panel
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pricePanel.setBackground(backgroundColor);
        pricePanel.add(new JLabel("Total price:"));
        JTextField priceField = new JTextField(5);
        pricePanel.add(priceField);
        pricePanel.add(new JLabel("€"));
        bottomContentPanel.add(pricePanel);

        // Coupon Panel
        JPanel couponPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        couponPanel.setBackground(backgroundColor);
        couponPanel.add(new JLabel("Enter coupon:"));
        JTextField couponField = new JTextField("coupon number", 10);
        couponPanel.add(couponField);
        bottomContentPanel.add(couponPanel);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(backgroundColor);

        JButton emptyCartButton = new JButton("Empty cart");
        JButton redeemPointsButton = new JButton("Redeem Points");
        JButton applyButton = new JButton("Apply");

        Color purple = new Color(92, 68, 112);

        for (JButton btn : new JButton[]{emptyCartButton, redeemPointsButton, applyButton}) {
            btn.setBackground(purple);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setFocusPainted(false);
            btn.setUI(new RoundedButtonUI());
            buttonPanel.add(btn);
        }

        bottomContentPanel.add(buttonPanel);

        return bottomContentPanel;
    }


    private JPanel createItemPanel(String title, String imagePath, boolean isEvent) {
        JPanel itemPanel = new JPanel(new BorderLayout());
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setMaximumSize(new Dimension(350, 120));

        ImageIcon iconImage = new ImageIcon(imagePath);
        Image scaledImage = iconImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel icon = new JLabel(new ImageIcon(scaledImage));
        icon.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemPanel.add(icon, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel priceLabel = new JLabel("price:"); // μπορείς να βάλεις μεταβλητή

        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(priceLabel);
        itemPanel.add(infoPanel, BorderLayout.CENTER);

        // Κάτω δεξιά: actions
        JPanel actionPanelWrapper = new JPanel(new BorderLayout());
        actionPanelWrapper.setBackground(Color.WHITE);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(Color.WHITE);
        actionPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

        if (isEvent) {
            JTextField numberField = new JTextField("1", 3);
            actionPanel.add(numberField);
            actionPanel.add(new JButton("+"));
            actionPanel.add(new JButton("-"));
        } else {
            JButton deleteButton = new JButton("🗑");
            deleteButton.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
            actionPanel.add(deleteButton);
        }

        actionPanelWrapper.add(actionPanel, BorderLayout.SOUTH);
        itemPanel.add(actionPanelWrapper, BorderLayout.EAST);

        return itemPanel;
    }
}
