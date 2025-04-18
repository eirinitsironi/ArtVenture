import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n=== Welcome to ArtVenture! ===");

        User user = new User(1, "eirini");

        while (true) {
            System.out.println("\n--- Main Options ---");
            System.out.println("1. Profile");
            System.out.println("2. Menu");
            System.out.println("3. Venture");
            System.out.println("4. Cart");
            System.out.println("5. Notifications");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    profileMenu(user);
                    break;
                case "2":
                    mainMenu(user);
                    break;
                case "3":
                    System.out.println("Venture not implemented yet.");
                    break;
                case "4":
                    System.out.println("Cart not implemented yet.");
                    break;
                case "5":
                    System.out.println("Notifications not implemented yet.");
                    break;
                case "0":
                    System.out.println("Goodbye from ArtVenture!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void profileMenu(User user) {
        while (true) {
            System.out.println("\n--- Profile ---");
            System.out.println("1. My Posts");
            System.out.println("2. Visit History");
            System.out.println("3. Wishlist");
            System.out.println("4. Reviews");
            System.out.println("0. Back");

            System.out.print("Choose option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    myPostsMenu(user);
                    break;
                case "2":
                    System.out.println("No visit history available.");
                    break;
                case "3":
                    System.out.println("Wishlist is currently empty.");
                    break;
                case "4":
                    System.out.println("You haven't submitted any reviews yet.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void myPostsMenu(User user) {
        while (true) {
            System.out.println("\n--- My Posts ---");
            System.out.println("1. Create New Post");
            System.out.println("2. View My Posts");
            System.out.println("0. Back");

            System.out.print("Choose option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    createPost(user);
                    break;
                case "2":
                    user.showAllPosts();
                    if (!user.getPosts().isEmpty()) {
                        System.out.print("View details? (yes/no): ");
                        if (scanner.nextLine().equalsIgnoreCase("yes")) {
                            System.out.print("Enter post number: ");
                            int index = Integer.parseInt(scanner.nextLine()) - 1;
                            List<Post> posts = user.getPosts();
                            if (index >= 0 && index < posts.size()) {
                                posts.get(index).details();
                            } else {
                                System.out.println("Invalid post number.");
                            }
                        }
                    }
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void mainMenu(User user) {
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Take Museum Preference Quiz");
            System.out.println("2. Wrapped (not implemented yet)");
            System.out.println("3. Find Venues (not implemented yet)");
            System.out.println("4. Make Art (not implemented yet)");
            System.out.println("0. Back");

            System.out.print("Choose option: ");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    startQuiz(user);
                    break;
                case "2":
                case "3":
                case "4":
                    System.out.println("This feature is not implemented yet.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void startQuiz(User user) {
        Quiz quiz = Quiz.loadProgress();
        if (quiz != null) {
            quiz.generateQuiz(scanner);
        } else {
            List<Question> questions = List.of(
                new Question(1, "\nWhat kind of art do you prefer?",
                    Arrays.asList("Modern", "Classic", "Abstract", "Realistic")),
                new Question(2, "\nWhich historical period are you interested in?",
                    Arrays.asList("Ancient Greece", "Middle Ages", "Renaissance", "Modern Era"))
            );
            quiz = new Quiz(1, user, questions);
            quiz.generateQuiz(scanner);
        }
    }

    private static void createPost(User user) {
        System.out.println("\n--- Create Post ---");
        System.out.println("1. Painting");
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
                System.out.println("Missing required fields.");
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
                System.out.println(" Invalid venue.");
                return;
            }

            if (event.validate()) {
                event.preview();
                System.out.print("Confirm post? (yes/no): ");
                if (scanner.nextLine().equalsIgnoreCase("yes")) {
                    user.addPost(event);
                    System.out.println(" Event post saved.");
                } else {
                    System.out.println("Cancelled.");
                }
            } else {
                System.out.println("Missing required fields.");
            }

        } else {
            System.out.println("Invalid choice.");
        }
    }
}
