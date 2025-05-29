package backend;

import java.util.*;

public class ArtMaker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> COLORS = List.of("R", "G", "B", "Y", "X", "W");
    List<String> tools = Tool.getAvailableTools();
    private static final List<Template> TEMPLATES = List.of(
        new Template("Landscape"),
        new Template("Portrait"),
        new Template("Abstract"),
        new Template("Still Life"),
        new Template("Custom")
    );

    public static void start(User user) {
        System.out.println("\n=== Make Art (Terminal Mode) ===");

        System.out.println("1. Start New Drawing");
        System.out.println("2. View My Drawings");
        System.out.print("Choose option: ");
        String mainChoice = scanner.nextLine();

        if (mainChoice.equals("2")) {
            viewUserDrawings(user);
            return;
        }

        for (int i = 0; i < TEMPLATES.size(); i++) {
            System.out.println((i + 1) + ". " + TEMPLATES.get(i).getName());
        }

        Template selected = null;
        while (true) {
            System.out.print("Choose template (1-" + TEMPLATES.size() + "): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= TEMPLATES.size()) {
                    selected = TEMPLATES.get(choice - 1);
                    break;
                } else {
                    System.out.println("Invalid selection. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        Drawing drawing = new Drawing(selected);
        boolean active = true;

        while (active) {
            drawing.renderCanvas();
            System.out.println("\n--- Options ---");
            System.out.println("1. Select color");
            System.out.println("2. Select tool");
            System.out.println("3. Apply color");
            System.out.println("4. Undo last action");
            System.out.println("5. Preview");
            System.out.println("6. Save");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            switch (scanner.nextLine()) {
                case "1" -> selectColor(drawing);
                case "2" -> selectTool(drawing);
                case "3" -> applyColor(drawing);
                case "4" -> drawing.undoLastAction();
                case "5" -> drawing.preview();
                case "6" -> {
                    System.out.print("Enter title: ");
                    drawing.setTitle(scanner.nextLine());
                    user.addDrawing(drawing);
                    System.out.println("Drawing saved and added to your drawings.");
                }
                case "0" -> active = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void selectColor(Drawing drawing) {
        System.out.println("Available colors:");
        System.out.println("R - Red, G - Green, B - Blue, Y - Yellow, X - Black, W - White");
        System.out.print("Choose color symbol: ");
        String color = scanner.nextLine().toUpperCase();
        if (COLORS.contains(color)) {
            drawing.setCurrentColor(color);
        } else {
            System.out.println("Invalid color.");
        }
    }

    private static void selectTool(Drawing drawing) {
        System.out.println("Available tools:");
        for (int i = 0; i < Tool.getAvailableTools().size(); i++) {
            System.out.println((i + 1) + ". " + Tool.getAvailableTools().get(i));
        }
        System.out.print("Select tool (1-" + Tool.getAvailableTools().size() + "): ");
        try {
            int toolIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (toolIndex >= 0 && toolIndex < Tool.getAvailableTools().size()) {
                drawing.setCurrentTool(Tool.getAvailableTools().get(toolIndex));
            } else {
                System.out.println("Invalid tool selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }
    }

    private static void applyColor(Drawing drawing) {
        try {
            System.out.print("X (0-9): ");
            int x = Integer.parseInt(scanner.nextLine());
            System.out.print("Y (0-9): ");
            int y = Integer.parseInt(scanner.nextLine());
            drawing.applyColor(x, y);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private static void viewUserDrawings(User user) {
        List<Drawing> drawings = user.getDrawings();
        if (drawings.isEmpty()) {
            System.out.println("You have no drawings saved.");
            return;
        }
        for (int i = 0; i < drawings.size(); i++) {
            System.out.println((i + 1) + ". " + (drawings.get(i).getTitle() == null ? "[Untitled]" : drawings.get(i).getTitle()));
        }
        System.out.print("Select drawing to view (or 0 to go back): ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) return;
            if (choice >= 1 && choice <= drawings.size()) {
                drawings.get(choice - 1).preview();
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
}
