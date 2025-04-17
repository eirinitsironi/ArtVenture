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
        System.out.println("Προεπισκόπηση Έργου: " + title + " [" + category + "]");
    }

    public boolean validate() {
        return imagePath != null && !imagePath.isEmpty() && category != null;
    }
}
