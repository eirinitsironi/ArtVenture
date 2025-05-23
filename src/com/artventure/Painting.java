public class Painting extends Post implements Priced {
    private float price;
    private String imagePath;
    private String caption;

    public Painting(String title, String category, String imagePath, String caption, float price) {
        super(title, category);
        this.imagePath = imagePath;
        this.caption = caption;
        this.price = price;
    }

    @Override
    public float getPrice() {
        return price;
    }

    public boolean validate() {
        return imagePath != null && !imagePath.isEmpty() && category != null;
    }

    @Override
    public void preview() {
        System.out.println("Preview: " + title);
    }

    @Override
    public void details() {
        System.out.println("--- Painting Details ---");
        System.out.println("Title: " + title);
        System.out.println("Category: " + category);
        System.out.println("Caption: " + caption);
        System.out.println("Image: " + imagePath);
        System.out.println("Price: " + price + "€");
        System.out.println("Posted at: " + createdAt);
    }
}
