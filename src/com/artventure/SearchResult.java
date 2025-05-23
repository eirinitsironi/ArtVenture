public class SearchResult {
    private int id;

    public SearchResult(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void viewDetails(Object r) {
        System.out.println("Παραπάνω λεπτομέρειες για ID: " + id);
    }
}
