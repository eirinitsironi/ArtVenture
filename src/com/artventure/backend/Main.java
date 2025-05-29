package backend;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                    ventureFeed(user);
                    break;
                case "4":
                    cartMenu(user);
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
            System.out.println("5. My Points");
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
                    reviewsMenu(user); 
                    break;
                case "5":
                    pointsMenu(user);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void myPostsMenu(User user) {

         List<Venue> venues = initializeVenues();

        while (true) {
            System.out.println("\n--- My Posts ---");
            System.out.println("1. Create New Post");
            System.out.println("2. View My Posts");
            System.out.println("0. Back");

            System.out.print("Choose option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    createPost(user, venues);
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
        
         List<Venue> venues = initializeVenues();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Take Museum Preference Quiz");
            System.out.println("2. Wrapped");
            System.out.println("3. Find Venues");
            System.out.println("4. Make Art");
            System.out.println("5. 2D Design");
            System.out.println("0. Back");

            System.out.print("Choose option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    startQuiz(user);
                    break;
                case "2":
                    myWrapped(user);
                    break;
                case "3":
                    findVenues(venues, user.getBalance());
                    break;
                case "4":
                    makeArt(user);    
                    break;
                case "5":
                    start2DDesign(user);
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
                    Arrays.asList("Modern Era", "Renaissance", "Middle Ages", "Ancient Greece")),
                new Question(3, "\nWhich medium do you enjoy most in art?",
                    Arrays.asList("Digital", "Oil painting", "Watercolor", "Sculpture")),
                new Question(4, "\nWhich color palette appeals to you the most?",
                    Arrays.asList("Vivid colors", "Cool tones", "Monochrome", "Warm tones")),
                new Question(5, "\nWhat emotion do you most enjoy seeing in art?",
                    Arrays.asList("Joy", "Awe", "Mystery", "Melancholy")),
                new Question(6, "\nWhich artist do you admire the most?",
                    Arrays.asList("Paul Gauguin", "Leonardo da Vinci", "Pablo Picasso", "Henri Matisse")),
                new Question(7, "\nDo you prefer art that tells a story or evokes a feeling?",
                    Arrays.asList("Both", "Neither", "Evokes a feeling", "Tells a story")),
                new Question(8, "\nWhich of these art movements do you find most fascinating?",
                    Arrays.asList("Expressionism", "Impressionism", "Cubism", "Surrealism")),
                new Question(9, "\nWhich cultural influence in art intrigues you the most?",
                    Arrays.asList("European", "Asian", "African", "Indigenous")),
                new Question(10, "\nHow important is technique versus creativity in art for you?",
                    Arrays.asList("Both equally", "Depends on the artwork", "Creativity is key", "Technique is key")),
                new Question(11, "\nWhat would you hang on your wall?",
                    Arrays.asList("Photograph", "Landscape", "Abstract piece", "Portrait")),
                new Question(12, "\nWhat do you think art should do?",
                    Arrays.asList("Make you think", "Beautify spaces", "Challenge norms", "Make you feel")),
                new Question(13, "\nWhich painting technique fascinates you the most?",
                    Arrays.asList("Glazing", "Dry brush", "Wet-on-wet", "Impasto")),
                new Question(14, "\nWhich of these painters' styles do you connect with?",
                    Arrays.asList("Vincent Van Gogh", "Claude Monet", "Salvador Dali", "Michelangelo")),
                new Question(15, "\nIf your mood were a brushstroke, what would it look like?",
                    Arrays.asList("Bold and chaotic", "Soft and flowing", "Sharp and precise", "Delicate and patterned")),
                new Question(16, "\nWhat would you paint if you had only one color and a giant canvas?",
                    Arrays.asList("An emotion", "A dream", "A mystery", "A memory")),
                new Question(17, "\nIf a painting could whisper secrets to you, what would you hope to hear?",
                    Arrays.asList("My future self speaking", "Forgotten childhood dreams", "Truths about the universe", "The story of a stranger")),
                new Question(18, "\nIf you could mix your own unique color, what would it be made of?",
                    Arrays.asList("Tears and twilight", "Laughter and leaves", "Fire and freedom", "Moonlight and memory")),
                new Question(19, "\nWhere does your mind go when you get lost in a painting?",
                    Arrays.asList("A silent forest", "An old forgotten city", "A planet no one has seen", "A childhood room")),
                new Question(20, "\nIf you could paint a feeling no one else has ever seen, what would it be?",
                    Arrays.asList("The stillness before something begins", "The joy of remembering a dream", "The peace after chaos", "The ache of a fading melody"))                               
            );
            quiz = new Quiz(1, user, questions);
            quiz.generateQuiz(scanner);       
        }
    }

private static void createPost(User user, List<Venue> venues) {
    System.out.println("\n--- Create Post ---");
    System.out.println("1. Painting");
    System.out.println("2. Event");
    System.out.print("Choose type: ");
    String type = scanner.nextLine();

    switch (type) {
        case "1":
            createPaintingPost(user);
            break;
        case "2":
            createEventPost(user, venues);
            break;
        default:
            System.out.println("Invalid choice.");
    }
}

private static void createPaintingPost(User user) {
    System.out.print("Title: ");
    String title = scanner.nextLine();

    System.out.print("Image path: ");
    String imagePath = scanner.nextLine();

    System.out.print("Category: ");
    String category = scanner.nextLine();

    System.out.print("Caption: ");
    String caption = scanner.nextLine();

    double price = promptForDouble("Price");

    String id = UUID.randomUUID().toString();
    Painting painting = new Painting(id, title, category, imagePath, caption, price);

    if (painting.validate()) {
        painting.preview();
        user.addPost(painting);
        System.out.println("Painting post saved.");
    } else {
        System.out.println("Missing required fields.");
    }
}

private static void createEventPost(User user, List<Venue> venues) {
    System.out.print("Event name: ");
    String eventName = scanner.nextLine();

    Venue venue = chooseVenue(venues);
    if (venue == null) return;

    System.out.print("Image path: ");
    String imagePath = scanner.nextLine();

    LocalDate date = promptForDate("Date (YYYY-MM-DD)");
    LocalTime time = promptForTime("Time (HH:MM)");

    LocalDateTime dateTime = LocalDateTime.of(date, time);

    System.out.print("Category: ");
    String category = scanner.nextLine();

    double ticketPrice = promptForDouble("Ticket Price");

    System.out.print("Address: ");
    String address = scanner.nextLine();

    String id = UUID.randomUUID().toString();
    Event event = new Event(id, eventName, venue, imagePath, dateTime, category, ticketPrice, address);

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
}

private static double promptForDouble(String prompt) {
    while (true) {
        System.out.print(prompt + ": ");
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid format. Please enter a numeric value.");
        }
    }
}

private static LocalDate promptForDate(String prompt) {
    while (true) {
        System.out.print(prompt + ": ");
        try {
            LocalDate date = LocalDate.parse(scanner.nextLine());
            if (date.isBefore(LocalDate.now())) {
                System.out.println("Date cannot be in the past.");
            } else {
                return date;
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        }
    }
}

private static LocalTime promptForTime(String prompt) {
    while (true) {
        System.out.print(prompt + ": ");
        try {
            return LocalTime.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid time format. Use HH:MM (24-hour).");
        }
    }
}

private static Venue chooseVenue(List<Venue> venues) {
    System.out.println("Available Venues:");
    for (int i = 0; i < venues.size(); i++) {
        System.out.println((i + 1) + ". " + venues.get(i).getName());
    }

    while (true) {
        System.out.print("Select venue by number: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < venues.size()) {
                return venues.get(index);
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
}

    private static void checkUpcomingEvents(User user) {
        LocalDateTime now = LocalDateTime.now();

        for (Post post : user.getPosts()) {
            if (post instanceof Event) {
                Event event = (Event) post;
                LocalDateTime eventDate = event.getEventDateTime();

                long days = java.time.Duration.between(now, eventDate).toDays();

                if (days >= 0 && days <= 3) {
                    String msg = "Event \"" + event.getEventName() + "\" Is in " + days + " days!";
                    boolean alreadyNotified = user.getNotifications().stream()
                        .anyMatch(n -> n.getMessage().equals(msg));

                    if (!alreadyNotified) {
                        user.addNotification(new Notification(msg));
                    }
                }
            }
        }
    }

    private static void reviewsMenu(User user) {
        while(true) {
            System.out.println("\n--- Reviews Menu ---");
            System.out.println("1. View My Reviews");
            System.out.println("2. Add Review");
            System.out.println("3. Edit Review");
            System.out.println("0. Back");

            String input = scanner.nextLine();

            switch(input) {
                case "1":
                    List<Review> reviews = user.getReviews();
                    if (reviews.isEmpty()) {
                        System.out.println("No reviews yet.");
                    } else {
                        for (Review r : reviews) {
                            System.out.println(r);
                        }
                    }
                    break;

                case "2":
                    addReviewForUser(user);
                    break;

                case "3":
                    editReviewForUser(user);
                    break;

                case "0":
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addReviewForUser(User user) {
        System.out.println("Select post to review:");
        List<Post> posts = user.getPosts();  // Ή λίστα από posts γενικότερα
        if (posts.isEmpty()) {
            System.out.println("No posts available.");
            return;
        }
        for (int i=0; i<posts.size(); i++) {
            System.out.println((i+1) + ". " + posts.get(i).getTitle());
        }
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine()) - 1;
        } catch(Exception e) {
            System.out.println("Invalid input.");
            return;
        }
        if (choice < 0 || choice >= posts.size()) {
            System.out.println("Invalid post selection.");
            return;
        }
        Post post = posts.get(choice);

        System.out.print("Enter rating (1-5): ");
        int rating;
        try {
            rating = Integer.parseInt(scanner.nextLine());
        } catch(Exception e) {
            System.out.println("Invalid rating.");
            return;
        }
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }

        System.out.print("Enter comment: ");
        String comment = scanner.nextLine();

        Review review = new Review(user.getReviews().size()+1, user, post, rating, comment);
        user.addReview(review);
        System.out.println("Review added successfully.");
    }

    private static void editReviewForUser(User user) {
        List<Review> reviews = user.getReviews();
        if (reviews.isEmpty()) {
            System.out.println("No reviews to edit.");
            return;
        }
        System.out.println("Select review to edit:");
        for (int i=0; i<reviews.size(); i++) {
            System.out.println((i+1) + ". " + reviews.get(i));
        }
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine()) - 1;
        } catch(Exception e) {
            System.out.println("Invalid input.");
            return;
        }
        if (choice < 0 || choice >= reviews.size()) {
            System.out.println("Invalid review selection.");
            return;
        }
        Review review = reviews.get(choice);

        System.out.print("Enter new rating (1-5): ");
        int rating;
        try {
            rating = Integer.parseInt(scanner.nextLine());
        } catch(Exception e) {
            System.out.println("Invalid rating.");
            return;
        }
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }

        System.out.print("Enter new comment: ");
        String comment = scanner.nextLine();

        System.out.print("Confirm overwrite? (yes/no): ");
        boolean confirm = scanner.nextLine().equalsIgnoreCase("yes");

        review.editReview(rating, comment, confirm);
    }

    private static void pointsMenu(User user) {
        while (true) {
            System.out.println("\n--- My Points ---");
            System.out.println("1. View Points Balance");
            System.out.println("2. View Points History");
            System.out.println("3. Redeem Points for Discount");
            System.out.println("0. Back");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    System.out.println("Available points: " + user.getPointsActivity().getTotalPoints());
                    break;
                case "2":
                    user.getPointsActivity().showTransactionsHistory();
                    break;
                case "3":
                getAvailableDiscounts(user);
                    break;
                case "0":
                    return;
                default: 
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void getAvailableDiscounts(User user) {
    Map<String, Integer> availableDiscounts = user.getPointsActivity().getAvailableDiscounts();

    if (availableDiscounts.isEmpty()) {
        System.out.println("\nNo discounts available. Earn more points!");
        return;
    }

    System.out.println("\nAvailable discounts:");
    int i = 1;
    List<String> discountNames = new ArrayList<>(availableDiscounts.keySet());

    for (String discount : discountNames) {
        int cost = availableDiscounts.get(discount);
        System.out.println(i + ". " + cost + " points - " + discount);
        i++;
    }

    System.out.println("Choose discount number (or 0 to cancel): ");
    String choice = scanner.nextLine();

    try {
        int index = Integer.parseInt(choice);
        if (index == 0) return;
        if (index < 1 || index > discountNames.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        String selectedDiscount = discountNames.get(index - 1);
        int pointsNeeded = availableDiscounts.get(selectedDiscount);

        if (user.getPointsActivity().redeemPoints(pointsNeeded, selectedDiscount)) {
            System.out.println("Discount applied successfully!");
        }

    } catch (NumberFormatException e) {
        System.out.println("Invalid input.");
    }

    }
    
    private static void myWrapped(User user) {
        Wrapped wrapped = Wrapped.loadProgress(user);

        if (wrapped != null) {
            if (wrapped.isCompleted()) {
                System.out.println("You've already finished your Wrapped!");

                System.out.print("Do you want to restart it? (yes/no): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("yes")) {
                    // Διαγραφή του αποθηκευμένου αρχείου
                    File file = new File("wrapped.ser");
                    if (file.exists()) {
                        file.delete();
                    }

                    // Δημιουργία νέου wrapped
                    wrapped = new Wrapped(user);
                    System.out.println("Starting a new Wrapped!");
                } else {
                    System.out.println("Okay! Come back anytime.");
                    return;
                }
            } else {
                System.out.println("Resuming your Wrapped from where you left off...");
            }
        } else {
            wrapped = new Wrapped(user);
            System.out.println("Starting your Wrapped!");
        }

        wrapped.generateWrapped(scanner);
    }

    private static void makeArt(User user) {
        ArtMaker.start(user); 
    }

    private static void cartMenu(User user) {
    while (true) {
        System.out.println("\n--- Shopping Cart ---");
        System.out.println("1. View Cart");
        System.out.println("2. Add Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Checkout");
        System.out.println("0. Back");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                user.getCart().showCart();
                break;

            case "2":
                System.out.print("Enter product name: ");
                String name = scanner.nextLine();

                System.out.print("Enter price: ");
                double price;
                try {
                    price = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price.");
                    break;
                }

                Product.Category.showCategories();
                System.out.print("Enter category: ");
                String categoryInput = scanner.nextLine();
                Product.Category category = Product.Category.fromString(categoryInput);

                if (category == null) {
                    System.out.println("Invalid category. Product not added.");
                    break;
                }

                System.out.print("Enter quantity: ");
                int qty;
                try {
                    qty = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity.");
                    break;
                }

                Product product = new Product(name, price, category);
                user.getCart().addItem(product, qty);
                System.out.println("Product added.");
                break;

            case "3":
                if (user.getCart().isEmpty()) {
                    System.out.println("Your cart is empty. Nothing to remove.");
                    break;
                }

                user.getCart().showCart();

                System.out.print("Enter product name to remove: ");
                String removeName = scanner.nextLine();
                boolean removed = user.getCart().removeItem(removeName);

                if (removed) {
                    System.out.println("Product removed.");
                } else {
                    System.out.println("No such product in your cart.");
                }
                break;

            case "4":
                user.getCart().checkout(user);
                break;

            case "0":
                return;

            default:
                System.out.println("Invalid option.");
        }
    }
}

private static void start2DDesign(User user) {
    Design design = new Design();
    System.out.print("Give a name for layout: ");
    String name = scanner.nextLine();

    System.out.print("Width (in slots): ");
    int width = Integer.parseInt(scanner.nextLine());

    System.out.print("Height (in slots): ");
    int height = Integer.parseInt(scanner.nextLine());

    design.createLayout(name, width, height);

    while (true) {
        System.out.println("\n--- 2D DESIGN MENU ---");
        System.out.println("1. Choose a wall(or floor)");
        System.out.println("2. View a wall(or floor)");
        System.out.println("3. Place an object");
        System.out.println("4. Remove an object");
        System.out.println("5. Undo");
        System.out.println("6. Redo");
        System.out.println("7. Save layout (without finalizing)");
        System.out.println("8. Finalize layout");
        System.out.println("0. Back");
        System.out.print("Option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                design.chooseWallCLI();
                break;
            case "2":
                design.displayCurrentWallCLI();
                break;
            case "3":
                design.placeObjectCLI();
                break;
            case "4":
                design.removeObjectCLI();
                break;
            case "5":
                design.undo();
                break;
            case "6":
                design.redo();
                break;
            case "7":
                design.saveLayout();
                break;
            case "8":
                design.finalizeLayout();
                 break;
            case "0":
                return;
            default:
                System.out.println("Invalid option.");
        }
    }
}

public static void findVenues(List<Venue> venues, Balance userBalance) {
    
    VenueSearchSystem searchSystem = new VenueSearchSystem(venues);

    System.out.println("\n--- Find Venues ---");
    
    System.out.print("Enter town (or leave empty): ");
    String townInput = scanner.nextLine();
    String town = townInput.isBlank() ? null : townInput;

    System.out.print("Enter min price (or leave empty): ");
    String minPriceInput = scanner.nextLine();
    Double minPrice = minPriceInput.isBlank() ? null : Double.parseDouble(minPriceInput);

    System.out.print("Enter max price (or leave empty): ");
    String maxPriceInput = scanner.nextLine();
    Double maxPrice = maxPriceInput.isBlank() ? null : Double.parseDouble(maxPriceInput);

    System.out.print("Enter max capacity (or leave empty): ");
    String capacityInput = scanner.nextLine();
    Integer maxCapacity = capacityInput.isBlank() ? null : Integer.parseInt(capacityInput);

    System.out.print("Enter event date (yyyy-mm-dd): ");
    String dateInput = scanner.nextLine();
    LocalDate eventDate = LocalDate.parse(dateInput);

    VenueFilter filter = new VenueFilter(town, minPrice, maxPrice, maxCapacity);
    List<Venue> results = searchSystem.search(filter, eventDate);

    if (results.isEmpty()) {
        System.out.println("No venues found! Try adjusting your filters.");
        return;
    }

    System.out.print("\nSort by price ascending? (yes/no): ");
    String sortAnswer = scanner.nextLine();
    results = searchSystem.sortByPrice(results, sortAnswer.equalsIgnoreCase("yes"));

    System.out.println("\nAvailable venues (sorted):");
    for (int i = 0; i < results.size(); i++) {
        System.out.println((i + 1) + ". " + results.get(i));
    }

    while (true) {
        System.out.print("\nSelect a venue (enter number): ");
        int selectedIndex;
        try {
            selectedIndex = Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            continue;
        }

        if (selectedIndex < 0 || selectedIndex >= results.size()) {
            System.out.println("Invalid selection.");
            continue;
        }

        Venue selectedVenue = results.get(selectedIndex);
        System.out.println("\n--- Venue Details ---");
        System.out.println("Name: " + selectedVenue.getName());
        System.out.println("Address: " + selectedVenue.getAddress());
        System.out.println("Contact: " + selectedVenue.getContactInfo());
        System.out.println("Available Dates: " + selectedVenue.getAvailableDates());
        System.out.println("Price: " + selectedVenue.getRentingPrice() + "$");
        System.out.println(userBalance);

        System.out.print("\nDo you want to proceed with booking? (yes/no): ");
        String proceed = scanner.nextLine();

        if (proceed.equalsIgnoreCase("yes")) {
            Booking booking = new Booking(selectedVenue, eventDate);
            if (booking.confirm(userBalance)) {
                System.out.println("Booking succeeded!");
                System.out.println(userBalance);
                return;
            } else {
                System.out.println("Booking failed. Check venue's availability or your balance.");
                return;
            }
        } else {
            System.out.println("Booking cancelled. Returning to search results...");
        }
    }
}

private static List<Venue> initializeVenues() {
    List<Venue> venues = new ArrayList<>();

    venues.add(new Venue(
        UUID.randomUUID().toString(),
        "Venue A",
        "Athens",
        "210-1234567",
        400.0,
        150,
        new ArrayList<>(Arrays.asList(
            LocalDate.of(2025, 6, 20),
            LocalDate.of(2025, 6, 25)
        ))
    ));

    venues.add(new Venue(
        UUID.randomUUID().toString(),
        "Venue B",
        "Thessaloniki",
        "2310-654321",
        300.0,
        100,
        new ArrayList<>(Arrays.asList(
            LocalDate.of(2025, 6, 20),
            LocalDate.of(2025, 6, 23)
        ))
    ));

    return venues;
}

private static final List<Post> ventureFeed = new ArrayList<>();

private static void ventureFeed(User user) {
    while (true) {
        System.out.println("\n--- Venture ---");
        System.out.println("1. -- Feed --");
        System.out.println("2. -- Search --");
        System.out.println("0. Back");
        System.out.print("Choose option: ");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                showAllPosts(user);
                break;
            case "2":
                searchPosts(user);
                break;
            case "0":
                return;
            default:
                System.out.println("Invalid option.");
        }
    }
}

private static void searchPosts(User user) {
    System.out.println("\n--- Search Options ---");
    System.out.println("1. Search by keywords");
    System.out.println("2. Search by filters (Category/Date&Time/Price)");
    System.out.println("0. Back");
    System.out.print("Choose option: ");
    String option = scanner.nextLine();

    String keyword = null;
    String category = null;
    SearchFilter filter = new SearchFilter(null, null, 0f, Float.MAX_VALUE);

    switch (option) {
        case "1":
            System.out.print("Enter keyword: ");
            keyword = scanner.nextLine();
            break;

        case "2":
            System.out.print("Enter category (or leave empty): ");
            category = scanner.nextLine();

            System.out.print("Filter by date from (yyyy-MM-dd HH:mm) (or leave empty): ");
            String dateFromStr = scanner.nextLine();
            LocalDateTime dateFrom = null;
            if (!dateFromStr.isEmpty()) {
                try {
                    dateFrom = LocalDateTime.parse(dateFromStr.replace(" ", "T"));
                } catch (Exception e) {
                    System.out.println("Invalid format. Ignoring date from.");
                }
            }

            System.out.print("Filter by date to (yyyy-MM-dd HH:mm) (or leave empty): ");
            String dateToStr = scanner.nextLine();
            LocalDateTime dateTo = null;
            if (!dateToStr.isEmpty()) {
                try {
                    dateTo = LocalDateTime.parse(dateToStr.replace(" ", "T"));
                } catch (Exception e) {
                    System.out.println("Invalid format. Ignoring date to.");
                }
            }

            System.out.print("Minimum price (or leave empty): ");
            String minPriceStr = scanner.nextLine();
            float minPrice = minPriceStr.isEmpty() ? 0f : Float.parseFloat(minPriceStr);

            System.out.print("Maximum price (or leave empty): ");
            String maxPriceStr = scanner.nextLine();
            float maxPrice = maxPriceStr.isEmpty() ? Float.MAX_VALUE : Float.parseFloat(maxPriceStr);

            filter = new SearchFilter(dateFrom, dateTo, minPrice, maxPrice);
            break;

        case "0":
            return;

        default:
            System.out.println("Invalid option. Returning to menu.");
            return;
    }

    Search search = new Search(keyword, category, filter);
    List<SearchResult> results = search.performSearch(ventureFeed);

    if (results.isEmpty()) {
        System.out.println("\nNo results found.");
        return;
    }

    System.out.println("\nSearch Results:");
    for (int i = 0; i < results.size(); i++) {
        int id = results.get(i).getId();
        for (Post post : ventureFeed) {
            if (post.hashCode() == id) {
                System.out.println((i + 1) + ". " + post.getTitle() + " [" + post.getCategory() + "]");
            }
        }
    }

    System.out.print("Select a post to view details (0 to cancel): ");
    String input = scanner.nextLine();
    try {
        int choice = Integer.parseInt(input);
        if (choice > 0 && choice <= results.size()) {
            int id = results.get(choice - 1).getId();
            for (Post post : ventureFeed) {
                if (post.hashCode() == id) {
                    post.preview();
                    System.out.print("View full details? (y/n): ");
                    if (scanner.nextLine().equalsIgnoreCase("y")) {
                        post.details();
                    }

                    if (post instanceof Painting) {
                        System.out.print("Do you want to add this to your wishlist? (yes/no): ");
                        if (scanner.nextLine().equalsIgnoreCase("yes")) {
                            user.addToWishlist((Painting) post);
                            System.out.println("Added to wishlist!");
                        }
                    }

                    break;
                }
            }
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input.");
    }
}

private static void showAllPosts(User user) {
    if (ventureFeed.isEmpty()) {
        System.out.println("No posts available yet.");
        return;
    }

    for (int i = 0; i < ventureFeed.size(); i++) {
        Post post = ventureFeed.get(i);
        System.out.println((i + 1) + ". " + post.getTitle() + " [" + post.getCategory() + "]");
    }

    System.out.print("Select a post to view details or 0 to go back: ");
    String input = scanner.nextLine();

    try {
        int choice = Integer.parseInt(input);
        if (choice == 0) return;

        if (choice >= 1 && choice <= ventureFeed.size()) {
            Post selectedPost = ventureFeed.get(choice - 1);
            selectedPost.preview();

            System.out.print("View full details? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                selectedPost.details();
            }

            if (selectedPost instanceof Item) {
                System.out.print("Do you want to add this to your wishlist? (yes/no): ");
                if (scanner.nextLine().equalsIgnoreCase("yes")) {
                    String type = selectedPost instanceof Painting ? "Painting" : "Event";
                    String id = selectedPost.getId();

                    if (!user.getWishlist().containsItem(id)) {
                        WishlistItem wishlistItem = new WishlistItem(id, type, selectedPost);
                        user.getWishlist().addItem(wishlistItem);
                        System.out.println("Added to wishlist!");
                    } else {
                        System.out.println("This post is already in your wishlist.");
                    }
                }
            }

        } else {
            System.out.println("Invalid choice.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Please enter a valid number.");
    }
}

}



