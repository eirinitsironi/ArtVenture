import java.util.ArrayList;
import java.util.List;

public class Drawing {
    private final String[][] canvas;
    private final List<String> actions;
    private String currentColor;
    private String currentTool;
    private String title;
    private final Template template;

    public Drawing(Template template) {
        this.canvas = new String[10][10];
        this.actions = new ArrayList<>();
        this.currentColor = " ";
        this.currentTool = "Brush";
        this.template = template;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                canvas[i][j] = " ";
            }
        }
    }

    public void setCurrentColor(String color) {
        this.currentColor = color;
    }

    public void setCurrentTool(String tool) {
        this.currentTool = tool;
    }

    public void applyColor(int x, int y) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
            canvas[y][x] = currentColor;
            actions.add("Applied " + colorName(currentColor) + " with " + currentTool + " at (" + x + "," + y + ")");
        } else {
            System.out.println("Invalid coordinates.");
        }
    }

    public void undoLastAction() {
        if (!actions.isEmpty()) {
            actions.remove(actions.size() - 1);
            // optional visual clearing not implemented here
        }
    }

    public void preview() {
        System.out.println("\n--- Preview ---");
        System.out.println("Template: " + template.getName());
        System.out.println("Title: " + (title != null ? title : "[Untitled]"));
        renderCanvas();
        System.out.println("\nActions:");
        actions.forEach(System.out::println);
    }

    public void renderCanvas() {
        System.out.println("\nCanvas:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print("[" + canvas[i][j] + "]");
            }
            System.out.println();
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String colorName(String c) {
        return switch (c) {
            case "R" -> "Red";
            case "G" -> "Green";
            case "B" -> "Blue";
            case "Y" -> "Yellow";
            case "X" -> "Black";
            case "W" -> "White";
            default -> "Unknown";
        };
    }
}
