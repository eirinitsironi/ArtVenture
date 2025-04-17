public class Venue {
    private String name;
    private boolean isAvailable;

    public Venue(String name, boolean isAvailable) {
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean isValid() {
        return isAvailable; // απλή λογική, μπορείς να την εμπλουτίσεις
    }
}
