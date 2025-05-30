package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FindVenuesPage {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static JComboBox<String> sortCombo;
    private static JPanel resultsPanel;

    public static void open() {
        frame = new JFrame("Find Venues - Museums");
        frame.setSize(450, 750);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0xD3DFB7));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(0xD3DFB7));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Date Panel
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.setBackground(new Color(0xD3DFB7));
        datePanel.setMaximumSize(new Dimension(400, 60));
        JLabel dateLabel = new JLabel("Mon, Aug 17");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        datePanel.add(dateLabel);
        JButton calendarButton = new JButton("📅");
        calendarButton.addActionListener(e -> showCalendarDialog());
        datePanel.add(calendarButton);
        mainPanel.add(datePanel);

        // Calendar
        JPanel calendarPanel = new JPanel(new GridLayout(0, 7));
        calendarPanel.setBackground(new Color(0xD3DFB7));
        calendarPanel.setMaximumSize(new Dimension(400, 150));
        JLabel monthLabel = new JLabel("August 2023", SwingConstants.CENTER);
        monthLabel.setFont(new Font("Arial", Font.BOLD, 14));
        String[] days = {"M", "T", "W", "T", "F", "S", "S"};
        for (String day : days) calendarPanel.add(new JLabel(day, SwingConstants.CENTER));
        int[] dates = {1, 2, 3, 4, 0, 0, 0,
                7, 8, 9, 10, 11, 12, 0,
                14, 15, 16, 17, 18, 19, 0,
                21, 22, 23, 24, 25, 26, 0,
                28, 29, 30, 31, 0, 0, 0};
        for (int date : dates) {
            JLabel dayLabel = new JLabel(date == 0 ? "" : String.valueOf(date), SwingConstants.CENTER);
            if (date == 17) {
                dayLabel.setOpaque(true);
                dayLabel.setBackground(Color.YELLOW);
            }
            calendarPanel.add(dayLabel);
        }
        mainPanel.add(monthLabel);
        mainPanel.add(calendarPanel);

        // Filters
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(new Color(0xD3DFB7));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filters"));
        filterPanel.setMaximumSize(new Dimension(400, 150));

        JPanel townPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        townPanel.setBackground(new Color(0xD3DFB7));
        townPanel.add(new JLabel("Town:"));
        JTextField townField = new JTextField("Athens", 15);
        townPanel.add(townField);
        filterPanel.add(townPanel);

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pricePanel.setBackground(new Color(0xD3DFB7));
        pricePanel.add(new JLabel("Price Range:"));
        JTextField minPrice = new JTextField("5", 5);
        pricePanel.add(minPrice);
        pricePanel.add(new JLabel(" - "));
        JTextField maxPrice = new JTextField("20", 5);
        pricePanel.add(maxPrice);
        filterPanel.add(pricePanel);

        JPanel capacityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        capacityPanel.setBackground(new Color(0xD3DFB7));
        capacityPanel.add(new JLabel("Max Capacity:"));
        JTextField capacityField = new JTextField("500", 5);
        capacityPanel.add(capacityField);
        filterPanel.add(capacityPanel);

        mainPanel.add(filterPanel);

        // Find Button
        JButton findButton = new JButton("Find Museums");
        findButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        findButton.setFont(new Font("Arial", Font.BOLD, 16));
        findButton.setBackground(new Color(0xE6E6FA));
        findButton.addActionListener(e -> showResults());
        mainPanel.add(findButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Results Panel Placeholder
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        resultsPanel.setBackground(new Color(0xD3DFB7));
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Search results"));
        mainPanel.add(resultsPanel);

        frame.add(new JScrollPane(mainPanel));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showCalendarDialog() {
        JOptionPane.showMessageDialog(frame, "Calendar picker would appear here");
    }

    private static void showResults() {
        resultsPanel.removeAll();

        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sortPanel.setBackground(new Color(0xD3DFB7));
        sortPanel.add(new JLabel("Sort by price: "));
        String[] sortOptions = {"Low to High", "High to Low"};
        sortCombo = new JComboBox<>(sortOptions);
        sortPanel.add(sortCombo);

        sortCombo.addActionListener(e -> refreshResults());

        resultsPanel.add(sortPanel);

        refreshResults();

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private static void refreshResults() {
        // Clear old results (except sortPanel)
        while (resultsPanel.getComponentCount() > 1) {
            resultsPanel.remove(1);
        }

        // Mock data
        List<Venue> venues = new ArrayList<>();
        venues.add(new Venue("Acropolis Museum", "Athens", 10, 500));
        venues.add(new Venue("National Archaeological Museum", "Athens", 12, 400));
        venues.add(new Venue("Museum of Byzantine Culture", "Thessaloniki", 8, 350));
        venues.add(new Venue("Archaeological Museum of Heraklion", "Heraklion", 15, 450));
        venues.add(new Venue("Benaki Museum", "Athens", 9, 300));
        venues.add(new Venue("Museum of Cycladic Art", "Athens", 11, 250));

        // Sorting
        if (sortCombo.getSelectedItem().equals("Low to High")) {
            venues.sort(Comparator.comparingInt(Venue::getPrice));
        } else {
            venues.sort(Comparator.comparingInt(Venue::getPrice).reversed());
        }

        for (Venue venue : venues) {
            JPanel venuePanel = new JPanel();
            venuePanel.setLayout(new BoxLayout(venuePanel, BoxLayout.Y_AXIS));
            venuePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            venuePanel.setBackground(Color.WHITE);
            venuePanel.setMaximumSize(new Dimension(380, 100));

            JLabel nameLabel = new JLabel(venue.getName());
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            venuePanel.add(nameLabel);
            venuePanel.add(new JLabel("Town: " + venue.getTown()));
            venuePanel.add(new JLabel("Price for entry: " + venue.getPrice() + " €"));
            venuePanel.add(new JLabel("Max Capacity: " + venue.getCapacity() + " people"));

            resultsPanel.add(venuePanel);
            resultsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    // Venue data class
    private static class Venue {
        private final String name;
        private final String town;
        private final int price;
        private final int capacity;

        public Venue(String name, String town, int price, int capacity) {
            this.name = name;
            this.town = town;
            this.price = price;
            this.capacity = capacity;
        }

        public String getName() { return name; }
        public String getTown() { return town; }
        public int getPrice() { return price; }
        public int getCapacity() { return capacity; }
    }
}
