import java.util.*;

public class ArtMaker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> COLORS = List.of("R", "G", "B", "Y", "X", "W");
    private static final List<String> TOOLS = List.of("Brush", "Pencil", "Spray", "Eraser");
    private static final List<Template> TEMPLATES = List.of(
        new Template("Landscape"),
        new Template("Portrait"),
        new Template("Abstract"),
        new Template("Still Life"),
        new Template("Custom")
    );

    public static void start() {
        System.out.println("\n=== Make Art (Terminal Mode) ===");

        for (int i = 0; i < TEMPLATES.size(); i++) {
            System.out.println((i + 1) + ". " + TEMPLATES.get(i).getName());
        }
        System.out.print("Choose template (1-" + TEMPLATES.size() + "): ");
        int choice = Integer.parseInt(scanner.nextLine());
        Template selected = TEMPLATES.get(choice - 1);

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
                    System.out.println("Drawing saved.");
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
        for (int i = 0; i < TOOLS.size(); i++) {
            System.out.println((i + 1) + ". " + TOOLS.get(i));
        }
        System.out.print("Select tool (1-" + TOOLS.size() + "): ");
        int toolIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (toolIndex >= 0 && toolIndex < TOOLS.size()) {
            drawing.setCurrentTool(TOOLS.get(toolIndex));
        } else {
            System.out.println("Invalid tool selection.");
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
}