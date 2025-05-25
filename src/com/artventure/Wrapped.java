import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Wrapped implements Serializable {
    private static final long serialVersionUID = 1L;

    private final User user;
    private int step = 0;
    private boolean completed = false;

    public Wrapped(User user) {
        this.user = user;
    }

    public void saveProgress() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("wrapped.ser"))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.out.println("Failed to save wrapped progress.");
        }
    }

    public static Wrapped loadProgress(User user) {
        File file = new File("wrapped.ser");
        if (!file.exists()) {
            return null;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Wrapped loadedWrapped = (Wrapped) in.readObject();
            return loadedWrapped; 
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading progress!");
            return null;
        }
    }

    public void generateWrapped(Scanner scanner) {
        if (!hasEnoughActivity()) {
            System.out.println("\nYou don’t have enough activity to generate your wrapped. Come back later.");
            return;
        }

        List<String> output = new ArrayList<>();

        while (step < 5) {
            switch (step) {
                case 0:
                    printlnWithPause("Welcome to your ArtVenture Wrapped!");
                    output.add("Welcome to your ArtVenture Wrapped!");
                    break;
                case 1:
                    String paintings = getTopPaintings();
                    output.add("\nMy Top Paintings:\n" + paintings);
                    break;
                case 2:
                    String artists = getTopArtists();
                    output.add("\nMy Top Artists:\n" + artists);
                    break;
                case 3:
                    String museums = getTopMuseums();
                    output.add("\nMy Top Museums:\n" + museums);
                    break;
                case 4:
                    String summary = getSummary();
                    output.add("\nWrapped Summary & Recommendations:\n" + summary);
                    completed = true;
                    break;
            }

            step++;
            saveProgress();

            if (step < 5) {
                System.out.print("\nPress Enter to continue or type 'exit' to leave: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Wrapped saved. Resume it anytime!");
                    return;
                }
            }
        }

        printlnWithPause("\nWrapped complete! See you next year!");
    }

    private boolean hasEnoughActivity() {
        return !user.getReviews().isEmpty() || !user.getWishlist().isEmpty() || !user.getVisitedMuseums().isEmpty();
    }

    private String getTopPaintings() {
        List<Painting> top = user.getRatedPaintings().stream()
                .sorted((a, b) -> Float.compare(user.getRatingFor(b), user.getRatingFor(a)))
                .limit(3)
                .collect(Collectors.toList());

        if (top.isEmpty()) return "No rated paintings.";

        StringBuilder sb = new StringBuilder();
        for (Painting p : top) {
            sb.append("- ").append(p.getTitle())
              .append(" by ").append(p.getArtist().getUsername()).append("\n");
        }
        System.out.println("\nMy Top Paintings:\n" + sb);
        return sb.toString();
    }

    private String getTopArtists() {
        Map<Artist, List<Float>> map = new HashMap<>();
        for (Review r : user.getReviews()) {
            if (r.getPost() instanceof Painting) {
                Artist a = ((Painting) r.getPost()).getArtist();
                map.computeIfAbsent(a, k -> new ArrayList<>()).add(r.getRating());
            }
        }

        StringBuilder sb = new StringBuilder();
        map.entrySet().stream()
                .map(e -> Map.entry(e.getKey(), e.getValue().stream().mapToDouble(Float::doubleValue).average().orElse(0)))
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(3)
                .forEach(e -> sb.append("- ").append(e.getKey().getUsername())
                        .append(" (avg ").append(String.format("%.2f", e.getValue())).append(")\n"));

        if (sb.isEmpty()) sb.append("No artist interactions.");
        System.out.println("\nMy Top Artists:\n" + sb);
        return sb.toString();
    }

    private String getTopMuseums() {
        Map<Museum, Long> visits = user.getVisitedMuseums().stream()
                .collect(Collectors.groupingBy(m -> m, Collectors.counting()));

        StringBuilder sb = new StringBuilder();
        visits.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(3)
                .forEach(e -> sb.append("- ").append(e.getKey().getName())
                        .append(" (").append(e.getValue()).append(" visits)\n"));

        if (sb.isEmpty()) sb.append("No museums visited.");
        System.out.println("\nMy Top Museums:\n" + sb);
        return sb.toString();
    }

    private String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("- Total Reviews: ").append(user.getReviews().size()).append("\n");
        sb.append("- Museums Visited: ").append(user.getVisitedMuseums().size()).append("\n");
        sb.append("- Paintings Rated: ").append(user.getRatedPaintings().size()).append("\n");

        System.out.println("\nSummary:\n" + sb);
        return sb.toString();
    }
    
    private void printlnWithPause(String text) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(30);
            } catch (InterruptedException ignored) {}
        }
        System.out.println();
    }
}