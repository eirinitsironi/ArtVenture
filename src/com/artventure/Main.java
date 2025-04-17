import java.util.*;
import java.time.LocalDateTime;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n=== Welcome to ArtVenture! ===");

        User user = new User(1, "eirini");

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Take Museum Preference Quiz");
            System.out.println("2. Create a New Post (Artwork/Event)");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    startQuiz(user);
                    break;
                case "2":
                    createPost(user);
                    break;
                case "0":
                    System.out.println("Goodbye from ArtVenture!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void startQuiz(User user) {
        Quiz quiz = Quiz.loadProgress();
        if (quiz != null) {
            quiz.generateQuiz();
        } else {
            List<Question> questions = List.of(
                new Question(1, "\nWhat kind of art do you prefer?",
                    Arrays.asList("Modern", "Classic", "Abstract", "Realistic")),
                new Question(2, "\nWhich historical period are you interested in?",
                    Arrays.asList("Ancient Greece", "Middle Ages", "Renaissance", "Modern Era"))
            );
            quiz = new Quiz(1, user, questions);
            quiz.generateQuiz();
        }
    }

    private static void createPost(User user) {
        System.out.println("\n--- Create Post ---");
        System.out.println("1. Artwork");
        System.out.println("2. Event");
        System.out.print("Choose type: ");
        String type = scanner.nextLine();

        if (type.equals("1")) {
            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Image path: ");
            String imagePath = scanner.nextLine();

            System.out.print("Category: ");
            String category = scanner.nextLine();

            System.out.print("Caption: ");
            String caption = scanner.nextLine();

            System.out.print("Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            Painting painting = new Painting(title, imagePath, category, caption, price);
            if (painting.validate()) {
                painting.preview();
                user.addPost(painting);
                System.out.println("Painting post saved.");
            } else {
                System.out.println("Missing required fields for artwork.");
            }

        } else if (type.equals("2")) {
            System.out.print("Event name: ");
            String eventName = scanner.nextLine();

            System.out.print("Venue name: ");
            String venueName = scanner.nextLine();
            Venue venue = new Venue(venueName, true);

            System.out.print("Image path: ");
            String imagePath = scanner.nextLine();

            System.out.print("Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.print("Time (HH:MM): ");
            String time = scanner.nextLine();
            LocalDateTime dateTime = LocalDateTime.parse(date + "T" + time);

            System.out.print("Category: ");
            String category = scanner.nextLine();

            System.out.print("Ticket Price: ");
            double ticketPrice = Double.parseDouble(scanner.nextLine());

            System.out.print("Address: ");
            String address = scanner.nextLine();

            EventPost event = new EventPost(eventName, venue, imagePath, dateTime, category, ticketPrice, address);
            if (!venue.isValid()) {
                System.out.println("This venue is not valid for the event.");
                return;
            }

            if (event.validate()) {
                event.preview();
                System.out.print("Confirm post? (yes/no): ");
                if (scanner.nextLine().equalsIgnoreCase("yes")) {
                    user.addPost(event);
                    System.out.println("Event post saved.");
                } else {
                    System.out.println("Event creation canceled.");
                }
            } else {
                System.out.println("Missing required fields for event.");
            }

        } else {
            System.out.println("Invalid choice.");
        }
    }
}
