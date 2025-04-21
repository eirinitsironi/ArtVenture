import java.time.LocalDateTime;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n=== Welcome to ArtVenture! ===");

        User user = new User(1, "eirini");

        while (true) {
            checkUpcomingEvents(user); 

            System.out.println("\n--- Main Options ---");
            int unreadCount = user.getUnreadNotificationCount();
            String notifLabel = unreadCount > 0 ? "Notifications (" + unreadCount + ")" : "Notifications";

            System.out.println("1. Profile");
            System.out.println("2. Menu");
            System.out.println("3. Venture");
            System.out.println("4. Cart");
            System.out.println("5. " + notifLabel);
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
                    user.showNotifications();
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
                    Arrays.asList("Ancient Greece", "Middle Ages", "Renaissance", "Modern Era")),
                new Question(3, "\nWhich medium do you enjoy most in art?",
                    Arrays.asList("Oil painting", "Watercolor", "Digital", "Sculpture")),
                new Question(4, "\nWhich color palette appeals to you the most?",
                    Arrays.asList("Warm tones", "Cool tones", "Monochrome", "Vivid colors")),
                new Question(5, "\nWhat emotion do you most enjoy seeing in art?",
                    Arrays.asList("Joy", "Melancholy", "Mystery", "Awe")),
                new Question(6, "\nWhich artist do you admire the most?",
                    Arrays.asList("Leonardo da Vinci", "Pablo Picasso", "Paul Gauguin", "Pablo Picasso")),
                new Question(7, "\nDo you prefer art that tells a story or evokes a feeling?",
                    Arrays.asList("Tells a story", "Evokes a feeling", "Both", "Neither")),
                new Question(8, "\nWhich of these art movements do you find most fascinating?",
                    Arrays.asList("Impressionism", "Cubism", "Surrealism", "Expressionism")),
                new Question(9, "\nWhich cultural influence in art intrigues you the most?",
                    Arrays.asList("Asian", "African", "European", "Indigenous")),
                new Question(10, "\nHow important is technique versus creativity in art for you?",
                    Arrays.asList("Technique is key", "Creativity is key", "Both equally", "Depends on the artwork")),
                new Question(11, "\nWhat would you hang on your wall?",
                    Arrays.asList("Portrait", "Landscape", "Abstract piece", "Photograph")),
                new Question(12, "\nWhat do you think art should do?",
                    Arrays.asList("Make you think", "Make you feel", "Beautify spaces", "Challenge norms")),
                new Question(13, "\nWhich painting technique fascinates you the most?",
                    Arrays.asList("Impasto", "Glazing", "Wet-on-wet", "Dry brush")),
                new Question(14, "\nWhich of these painters' styles do you connect with?",
                    Arrays.asList("Vincent Van Gogh", "Claude Monet", "Michelangelo", "Salvador Dali")),
                new Question(15, "\nIf your mood were a brushstroke, what would it look like?",
                    Arrays.asList("Bold and chaotic", "Soft and flowing", "Sharp and precise", "Loose and spontaneous")),
                new Question(16, "\nWhat would you paint if you had only one color and a giant canvas?",
                    Arrays.asList("An emotion", "A dream", "A memory", "A mystery")),
                new Question(17, "\nIf a painting could whisper secrets to you, what would you hope to hear?",
                    Arrays.asList("Truths about the universe", "Forgotten childhood dreams", "The story of a stranger", "My future self speaking")),
                new Question(18, "\nIf you could mix your own unique color, what would it be made of?",
                    Arrays.asList("Moonlight and memory", "Laughter and leaves", "Tears and twilight", "Fire and freedom")),
                new Question(19, "\nWhere does your mind go when you get lost in a painting?",
                    Arrays.asList("A silent forest", "An old forgotten city", "A childhood room", "A planet no one has seen")),
                new Question(20, "\nIf you could paint a feeling no one else has ever seen, what would it be?",
                    Arrays.asList("The ache of a fading melody", "The joy of remembering a dream", "The peace after chaos", "The stillness before something begins"))                               
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
                System.out.println("Invalid venue.");
                return;
            }

            if (event.validate()) {
                event.preview();
                System.out.print("Confirm post? (yes/no): ");
                if (scanner.nextLine().equalsIgnoreCase("yes")) {
                    user.addPost(event);
                    System.out.println("Event post saved.");
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

    private static void checkUpcomingEvents(User user) {
        LocalDateTime now = LocalDateTime.now();

        for (Post post : user.getPosts()) {
            if (post instanceof EventPost) {
                EventPost event = (EventPost) post;
                LocalDateTime eventDate = event.getEventDateTime();

                long days = java.time.Duration.between(now, eventDate).toDays();

                if (days >= 0 && days <= 3) {
                    String msg = "Η εκδήλωση \"" + event.getEventName() + "\" Is in " + days + " days!";
                    boolean alreadyNotified = user.getNotifications().stream()
                        .anyMatch(n -> n.getMessage().equals(msg));

                    if (!alreadyNotified) {
                        user.addNotification(new Notification(msg));
                    }
                }
            }
        }
    }
}

