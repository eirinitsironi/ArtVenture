import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class WrappedService implements Serializable {
    private static final long serialVersionUID = 1L;

    private final User user;
    private int step = 0; // χρησιμοποιείται για να ξέρουμε από πού να συνεχίσουμε

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
            System.out.println("Failed to save wrapped progress.");
        }
    }

    public void generateWrapped(Scanner scanner) {
        if (!hasEnoughActivity()) {
            System.out.println("You don’t have enough activity to generate your wrapped. Come back later.");
            return;
        }

        while (step < 5) {
            switch (step) {
                case 0:
                    System.out.println("\n🎉 Welcome to your 2024 Wrapped!");
                    break;
                case 1:
                    showTopPaintings();
                    break;
                case 2:
                    showTopArtists();
                    break;
                case 3:
                    showTopMuseums();
                    break;
                case 4:
                    showSummaryAndRecommendations();
                    break;
            }

            step++;
            saveProgress(); // αποθήκευση μετά από κάθε βήμα

            if (step < 5) {
                System.out.print("Press Enter to continue or type 'exit' to leave: ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Wrapped progress saved. You can return later!");
                    return;
                }
            }
        }

        System.out.println("\n✅ Wrapped complete! See you next year!");
        new File("wrapped_" + user.getUsername() + ".ser").delete(); // Διαγραφή αποθηκευμένης προόδου
    }

    private boolean hasEnoughActivity() {
        return !user.getReviews().isEmpty() || !user.getWishlist().isEmpty() || !user.getVisitedMuseums().isEmpty();
    }

    private void showTopPaintings() {
        System.out.println("\n🎨 My top paintings!");
        List<Painting> top = user.getRatedPaintings().stream()
                .sorted((a, b) -> Float.compare(user.getRatingFor(b), user.getRatingFor(a)))
                .limit(3)
                .collect(Collectors.toList());

        if (top.isEmpty()) System.out.println("No rated paintings.");
        for (Painting p : top) {
            System.out.println("- " + p.getTitle() + " by " + p.getArtist().getUsername());
        }
    }

    private void showTopArtists() {
        System.out.println("\n🧑‍🎨 My top artists!");
        Map<Artist, List<Float>> map = new HashMap<>();
        for (Review r : user.getReviews()) {
            if (r.getItem() instanceof Painting) {
                Artist a = ((Painting) r.getItem()).getArtist();
                map.computeIfAbsent(a, k -> new ArrayList<>()).add(r.getRating());
            }
        }

        map.entrySet().stream()
                .map(e -> Map.entry(e.getKey(), e.getValue().stream().mapToDouble(Float::doubleValue).average().orElse(0)))
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .limit(3)
                .forEach(e -> System.out.printf("- %s (avg %.2f)\n", e.getKey().getUsername(), e.getValue()));
    }

    private void showTopMuseums() {
        System.out.println("\n🏛️ My top museums!");
        Map<Museum, Long> visits = user.getVisitedMuseums().stream()
                .collect(Collectors.groupingBy(m -> m, Collectors.counting()));

        visits.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(3)
                .forEach(e -> System.out.println("- " + e.getKey().getName() + " (" + e.getValue() + " visits)"));
    }

    private void showSummaryAndRecommendations() {
        System.out.println("\n📊 Wrapped Summary:");
        System.out.println("- Total Reviews: " + user.getReviews().size());
        System.out.println("- Museums Visited: " + user.getVisitedMuseums().size());
        System.out.println("- Paintings Rated: " + user.getRatedPaintings().size());

        System.out.println("\n✨ Based on your activity, you might like:");
        System.out.println("- Artist: Emerging Star");
        System.out.println("- Painting: Vibrant Dreams");
        System.out.println("- Museum: The NeoGallery");
    }
}
