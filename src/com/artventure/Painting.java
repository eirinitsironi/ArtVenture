import java.util.Objects;

public class Painting extends Post implements Priced, Item {
    private double price;
    private String imagePath;
    private String caption;
    private Artist artist;

    public Painting(String id, String title, String category, String imagePath, String caption, double price) {
        super(id, title, category);
        this.imagePath = imagePath;
        this.caption = caption;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public boolean validate() {
        return imagePath != null && !imagePath.isEmpty() && category != null;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Painting)) return false;
        Painting p = (Painting) o;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

