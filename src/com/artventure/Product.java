
public class Product {
    private String name;
    private double price;
    private Category category;

    public Product(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public Category getCategory() { return category; }

    public void showDetails() {
        System.out.println("- " + name + " | " + price + "€ | " + category.name());
    }

    public enum Category {
        PAINTING,
        TICKET;

        public static Category fromString(String input) {
            for (Category c : Category.values()) {
                if (c.name().equalsIgnoreCase(input.trim())) {
                    return c;
                }
            }
            return null;
        }

        public static void showCategories() {
            System.out.println("Available categories:");
            for (Category c : Category.values()) {
                System.out.println("- " + c.name());
            }
        }
    }
}
