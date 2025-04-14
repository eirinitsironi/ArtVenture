public class Museum {
    private int museumID;
    private String name;
    private String description;

    public Museum(int museumID, String name, String description) {
        this.museumID = museumID;
        this.name = name;
        this.description = description;
    }

    public int getMuseumID() {
        return museumID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
