public class Painting extends Post {
    private String title;
    private String imagePath;
    private String caption;
    private double price;

    public Painting(String title, String imagePath, String category, String caption, double price) {
        super(category);
        this.title = title;
        this.imagePath = imagePath;
        this.caption = caption;
        this.price = price;
    }

    @Override
    public void preview() {
        System.out.println("Painting preview: " + title + " [" + category + "]");
    }

    public boolean validate() {
        return imagePath != null && !imagePath.isEmpty() && category != null;
    }

    @Override
    public void details() {
        System.out.println("\n--- Painting Details ---");
        System.out.println("Title: " + title);
        System.out.println("Category: " + category);
        System.out.println("Caption: " + caption);
        System.out.println("Price: " + price + "€");
        System.out.println("Posted at: " + createdAt);
    }

}
