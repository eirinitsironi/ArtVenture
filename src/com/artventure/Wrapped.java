import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class WrappedService implements Serializable {
    private static final long serialVersionUID = 1L;

    private final User user;
    private int step = 0;
    private boolean completed = false;

    public WrappedService(User user) {
        this.user = user;
    }

    public static WrappedService loadProgress(User user) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("wrapped_" + user.getUsername() + ".ser"))) {
            return (WrappedService) in.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    public void saveProgress() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("wrapped_" + user.getUsername() + ".ser"))) {
            out.writeObject(this);
        } catch (IOException e) {
            System.out.println("⚠️ Failed to save wrapped progress.");
        }
    }

    public void generateWrapped(Scanner scanner) {
        if (!hasEnoughActivity()) {
            System.out.println("\n❌ You don’t have enough activity to generate your wrapped. Come back later.");
            return;
        }

        List<String> output = new ArrayList<>();

        while (step < 5) {
            switch (step) {
                case 0:
                    printlnWithPause("🎁 Welcome to your ArtVenture Wrapped!");
                    output.add("🎁 Welcome to your ArtVenture Wrapped!");
                    break;
                case 1:
                    String paintings = getTopPaintings();
                    output.add("\n🎨 My Top Paintings:\n" + paintings);
                    break;
                case 2:
                    String artists = getTopArtists();
                    output.add("\n🧑‍🎨 My Top Artists:\n" + artists);
                    break;
                case 3:
                    String museums = getTopMuseums();
                    output.add("\n🏛️ My Top Museums:\n" + museums);
                    break;
                case 4:
                    String summary = getSummary();
                    output.add("\n📊 Wrapped Summary & Recommendations:\n" + summary);
                    completed = true;
                    break;
            }

            step++;
            saveProgress();

            if (step < 5) {
                System.out.print("\nPress Enter to continue or type 'exit' to leave: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("🔒 Wrapped saved. Resume it anytime!");
                    return;
                }
            }
        }

        printlnWithPause("\n✅ Wrapped complete! See you next year!");
        deleteProgressFile();
        saveToFile(output);
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
        System.out.println("\n🎨 My Top Paintings:\n" + sb);
        return sb.toString();
    }

    private String getTopArtists() {
        Map<Artist, List<Float>> map = new HashMap<>();
        for (Review r : user.getReviews()) {
            if (r.getItem() instanceof Painting) {
                Artist a = ((Painting) r.getItem()).getArtist();
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
        System.out.println("\n🧑‍🎨 My Top Artists:\n" + sb);
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
        System.out.println("\n🏛️ My Top Museums:\n" + sb);
        return sb.toString();
    }

    private String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("- Total Reviews: ").append(user.getReviews().size()).append("\n");
        sb.append("- Museums Visited: ").append(user.getVisitedMuseums().size()).append("\n");
        sb.append("- Paintings Rated: ").append(user.getRatedPaintings().size()).append("\n");

        sb.append("\n✨ Based on your activity, we recommend:\n");
        sb.append("- Artist: Emerging Star\n");
        sb.append("- Painting: Hidden Gem\n");
        sb.append("- Museum: ArtSpot Athens\n");

        System.out.println("\n📊 Summary:\n" + sb);
        return sb.toString();
    }

    private void deleteProgressFile() {
        File f = new File("wrapped_" + user.getUsername() + ".ser");
        if (f.exists()) f.delete();
    }

    private void saveToFile(List<String> content) {
        try (PrintWriter writer = new PrintWriter("wrapped_report_" + user.getUsername() + ".txt")) {
            for (String line : content) writer.println(line);
            System.out.println("📝 Your Wrapped has been saved to file!");
        } catch (IOException e) {
            System.out.println("❌ Could not save wrapped to file.");
        }
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